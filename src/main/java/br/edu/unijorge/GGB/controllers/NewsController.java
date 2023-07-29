package br.edu.unijorge.GGB.controllers;

import br.edu.unijorge.GGB.entitys.News;
import br.edu.unijorge.GGB.services.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/v1/news")
public class NewsController {
    @Autowired
    private INewsService newsService;
    @GetMapping
    public ResponseEntity<List<News>> getNews(){
        var news = newsService.findAllNews();
        return ResponseEntity.ok(news);
        
    }
    @GetMapping("/{id}")
    public ResponseEntity<News> getNewsById(@PathVariable Long id){
        var news = newsService.findById(id);
        if(news == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(news);

    }
    @PostMapping
    public ResponseEntity<News> createNews(@RequestBody News news){
        var newsEntity = newsService.createNews(news);
        return ResponseEntity.ok(newsEntity);
    }
    @PutMapping
    public ResponseEntity<News> updateNews(@RequestBody News news){
        var newsEntity = newsService.updateNews(news);
        return ResponseEntity.ok(newsEntity);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<News> deleteNews(@PathVariable Long id){
        Boolean result = newsService.deleteNews(id);
        return (result)?ResponseEntity.noContent().build():ResponseEntity.notFound().build();
    }
}
