package br.edu.unijorge.GGB.controllers;

import br.edu.unijorge.GGB.DTOs.NewsDTO;
import br.edu.unijorge.GGB.entitys.NewsEntity;
import br.edu.unijorge.GGB.services.INewsService;
import org.modelmapper.ModelMapper;
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
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/v1/news")
public class NewsController {
    @Autowired
    private INewsService newsService;
    @Autowired
    private ModelMapper modelMapper;
    @GetMapping
    public ResponseEntity<List<NewsDTO>> getNews(){
        var newsEntity = newsService.findAllNews();
        var news = mapList(newsEntity, NewsDTO.class);
        return ResponseEntity.ok(news);
        
    }
    @GetMapping("/{id}")
    public ResponseEntity<NewsDTO> getNewsById(@PathVariable Long id){
        var newsEntity = newsService.findById(id);
        if(newsEntity == null)
            return ResponseEntity.notFound().build();
        var news = modelMapper.map(newsEntity,NewsDTO.class);
        return ResponseEntity.ok(news);

    }
    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<NewsDTO> createNews(@RequestPart NewsDTO news, @RequestPart Optional<MultipartFile> file) throws IOException {
        NewsEntity newsEntity = modelMapper.map(news,NewsEntity.class);
        NewsEntity createdNewsEntity;
        if(file.isPresent()){
            MultipartFile image = file.get();
            if (image.isEmpty() || image.getOriginalFilename() == null || image.getOriginalFilename().isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"the File must contain a valid file name");
            }
            createdNewsEntity = newsService.createNews(newsEntity, image);
            var mappedNews = modelMapper.map(createdNewsEntity,NewsDTO.class);
            return ResponseEntity.status(HttpStatus.CREATED).body(mappedNews);
        }
        createdNewsEntity = newsService.createNews(newsEntity);
        var mappedNews = modelMapper.map(createdNewsEntity,NewsDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(mappedNews);
    }
    @PutMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<NewsDTO> updateNews(@RequestPart NewsDTO news,@RequestPart Optional<MultipartFile> file) throws IOException {
        NewsEntity newsEntity = modelMapper.map(news,NewsEntity.class);
        NewsEntity updatedNewsEntity;
        if(file.isPresent()){
            MultipartFile image = file.get();
            if (image.isEmpty() || image.getOriginalFilename() == null || image.getOriginalFilename().isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"The File must contain a valid file name");
            }
            updatedNewsEntity = newsService.updateNews(newsEntity, image);
            var mappedNews = modelMapper.map(updatedNewsEntity,NewsDTO.class);
            return ResponseEntity.status(HttpStatus.CREATED).body(mappedNews);
        }
        updatedNewsEntity = newsService.updateNews(newsEntity);
        var mappedNews = modelMapper.map(updatedNewsEntity,NewsDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(mappedNews);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<NewsDTO> deleteNews(@PathVariable Long id){
        Boolean result = newsService.deleteNews(id);
        return (result)?ResponseEntity.noContent().build():ResponseEntity.notFound().build();
    }

    // Método cujo o objetivo é mapear uma lista genérica Source para o tipo da
    // class target enviada como parâmetro
    private <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }
}
