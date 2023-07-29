package br.edu.unijorge.GGB.services;

import br.edu.unijorge.GGB.entitys.News;

import java.util.List;

public interface INewsService {
    List<News> findAllNews();
    News findById(Long id);
    News createNews(News news);
    News updateNews(News news);
    Boolean deleteNews(Long id);
}
