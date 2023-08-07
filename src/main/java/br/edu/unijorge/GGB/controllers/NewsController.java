package br.edu.unijorge.GGB.controllers;

import br.edu.unijorge.GGB.entitys.News;
import br.edu.unijorge.GGB.services.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

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
    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<News> createNews(@RequestPart News news, @RequestPart Optional<MultipartFile> file) throws IOException {
        News createdNews;
        if(file.isPresent()){
            MultipartFile image = file.get();
            if (image.isEmpty() || image.getOriginalFilename() == null || image.getOriginalFilename().isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"the File must contain a valid file name");
            }
            createdNews = newsService.createNews(news, image);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdNews);
        }
          createdNews = newsService.createNews(news);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdNews);
    }
    @PutMapping
    public ResponseEntity<News> updateNews(@RequestBody News news, Optional<MultipartFile> file) throws IOException {
        News updatedNews;
        if(file.isPresent()){
            MultipartFile image = file.get();
            if (image.isEmpty() || image.getOriginalFilename() == null || image.getOriginalFilename().isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"The File must contain a valid file name");
            }
            updatedNews = newsService.updateNews(news, image);
            return ResponseEntity.status(HttpStatus.OK).body(updatedNews);
        }
        updatedNews = newsService.updateNews(news);
        return ResponseEntity.status(HttpStatus.OK).body(updatedNews);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<News> deleteNews(@PathVariable Long id){
        Boolean result = newsService.deleteNews(id);
        return (result)?ResponseEntity.noContent().build():ResponseEntity.notFound().build();
    }
}
