package br.edu.unijorge.GGB.services;

import br.edu.unijorge.GGB.entitys.News;
import br.edu.unijorge.GGB.exceptions.StorageException;
import br.edu.unijorge.GGB.repositories.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;

@Service
public class NewsService implements INewsService {
    @Autowired
    private NewsRepository newsRepository;
    @Value("${image.baseDir}")
    private String imageDirectory;

    @Override
    public List<News> findAllNews() {
        return newsRepository.findAllByOrderByDateDesc();
    }

    @Override
    public News findById(Long id) {
        return newsRepository.findById(id).orElse(null);
    }

    @Override
    public News createNews(News news) {
        return newsRepository.saveAndFlush(news);
    }

    @Override
    public News createNews(News news, MultipartFile file) throws IOException {
        news.setMainPicture(storeFileIntoImagesDirectory(file));
        return newsRepository.saveAndFlush(news);
    }

    @Override
    public News updateNews(News news) {
        return newsRepository.saveAndFlush(news);
    }

    @Override
    public News updateNews(News news, MultipartFile file) throws IOException {
         news.setMainPicture(storeFileIntoImagesDirectory(file));
         return newsRepository.saveAndFlush(news);
    }

    @Override
    public Boolean deleteNews(Long id) {
        var news = newsRepository.findById(id);
        if (news.isPresent()) {
            newsRepository.deleteById(id);
            return true;
        }
        return false;
    }
    private String storeFileIntoImagesDirectory(MultipartFile file) throws IOException {
        try {
            Path destinationFile = Paths.get(imageDirectory).resolve(Paths.get(Objects.requireNonNull(file.getOriginalFilename()))).normalize().toAbsolutePath();
            InputStream inputStream = file.getInputStream();
            Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            return destinationFile.toString();
        }catch (IOException ex){
            throw new StorageException("Failed to store file.", ex);
        }

    }
}
