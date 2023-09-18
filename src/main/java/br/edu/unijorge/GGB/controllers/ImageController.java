package br.edu.unijorge.GGB.controllers;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/v1/image")
public class ImageController {
    @Value("${image.baseDir}")
    private String imageDir;
    @GetMapping(value = "/{name}",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public @ResponseBody byte[] getImageWithMediaType(@PathVariable String name) throws IOException {
        File images = Paths.get(imageDir).toFile();
        try{
            for (File image : images.listFiles()) {
                if (image.getName().contains(name)) {
                    InputStream in = FileUtils.openInputStream(image);
                    return IOUtils.toByteArray(in);
                }
            }
        }catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
