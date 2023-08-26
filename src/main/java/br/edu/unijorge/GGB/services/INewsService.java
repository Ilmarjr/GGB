package br.edu.unijorge.GGB.services;

import br.edu.unijorge.GGB.entitys.NewsEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface INewsService {
    List<NewsEntity> findAllNews();
    NewsEntity findById(Long id);
    NewsEntity createNews(NewsEntity newsEntity);
    NewsEntity createNews(NewsEntity newsEntity, MultipartFile image) throws IOException;
    NewsEntity updateNews(NewsEntity newsEntity);
    NewsEntity updateNews(NewsEntity newsEntity, MultipartFile image) throws IOException;
    Boolean deleteNews(Long id);
}
