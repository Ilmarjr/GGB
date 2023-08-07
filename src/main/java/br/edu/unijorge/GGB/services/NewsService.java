package br.edu.unijorge.GGB.services;

import br.edu.unijorge.GGB.entitys.News;
import br.edu.unijorge.GGB.repositories.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Service
public class NewsService implements INewsService{
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
    public News createNews(News news, MultipartFile image) {
        return null;
    }

    @Override
    public News updateNews(News news) {
        return newsRepository.saveAndFlush(news);
    }

    @Override
    public News updateNews(News news, MultipartFile image) {
        return null;
    }

    @Override
    public Boolean deleteNews(Long id) {
        var news = newsRepository.findById(id);
        if(news.isPresent()){
            newsRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
