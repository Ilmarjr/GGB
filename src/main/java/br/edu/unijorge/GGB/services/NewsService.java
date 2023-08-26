package br.edu.unijorge.GGB.services;

import br.edu.unijorge.GGB.entitys.NewsEntity;
import br.edu.unijorge.GGB.entitys.Tag;
import br.edu.unijorge.GGB.exceptions.StorageException;
import br.edu.unijorge.GGB.repositories.NewsRepository;
import br.edu.unijorge.GGB.repositories.TagRepository;
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
    @Autowired
    private TagRepository tagRepository;
    @Value("${image.baseDir}")
    private String imageDirectory;

    @Override
    public List<NewsEntity> findAllNews() {
        return newsRepository.findAllByOrderByDateDesc();
    }

    @Override
    public NewsEntity findById(Long id) {
        return newsRepository.findById(id).orElse(null);
    }

    @Override
    public NewsEntity createNews(NewsEntity newsEntity) {
        var tags = createOrUpdateTags(newsEntity.getTags());
        newsEntity.setTags(tags);
        return newsRepository.saveAndFlush(newsEntity);
    }

    @Override
    public NewsEntity createNews(NewsEntity newsEntity, MultipartFile file) throws IOException {
        newsEntity.setMainPicture(storeFileIntoImagesDirectory(file));
        var tags = createOrUpdateTags(newsEntity.getTags());
        newsEntity.setTags(tags);
        return newsRepository.saveAndFlush(newsEntity);
    }

    @Override
    public NewsEntity updateNews(NewsEntity newsEntity) {
        var tags = createOrUpdateTags(newsEntity.getTags());
        newsEntity.setTags(tags);
        return newsRepository.saveAndFlush(newsEntity);
    }

    @Override
    public NewsEntity updateNews(NewsEntity newsEntity, MultipartFile file) throws IOException {
         newsEntity.setMainPicture(storeFileIntoImagesDirectory(file));
        var tags = createOrUpdateTags(newsEntity.getTags());
        newsEntity.setTags(tags);
         return newsRepository.saveAndFlush(newsEntity);
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
    private List<Tag> createOrUpdateTags(List<Tag> tags){
        for (Tag tag: tags) {
            var optTag = tagRepository.findByTagIgnoreCase(tag.getTag());
            if(optTag.isPresent())
                //tags.add(tags.indexOf(tag),optTag.get());
                tag = optTag.get();
            else tag = tagRepository.saveAndFlush(tag);
        }
        return tags;
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
