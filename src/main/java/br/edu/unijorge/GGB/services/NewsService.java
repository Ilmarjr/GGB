package br.edu.unijorge.GGB.services;

import br.edu.unijorge.GGB.entitys.News;
import br.edu.unijorge.GGB.repositories.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NewsService implements INewsService{
    @Autowired
    private NewsRepository newsRepository;
    @Override
    public List<News> findAllNews() {
        return newsRepository.findAll();
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
    public News updateNews(News news) {
        return newsRepository.saveAndFlush(news);
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
