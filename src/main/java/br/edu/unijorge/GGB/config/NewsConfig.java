package br.edu.unijorge.GGB.config;

import br.edu.unijorge.GGB.DTOs.NewsDTO;
import br.edu.unijorge.GGB.entitys.NewsEntity;
import br.edu.unijorge.GGB.entitys.Tag;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class NewsConfig {
    private final ModelMapper modelMapper = new ModelMapper();

    public NewsConfig() {
        mapNewsDTOtoNewsEntity();
        mapNewsEntityToNewsDTO();
    }

    private void mapNewsDTOtoNewsEntity() {
        Converter<NewsDTO, List<Tag>> tagFieldConverter = context -> {
            var src = context.getSource();
            List<Tag> tags = new ArrayList<>();
            for (String description : src.getTags()) {
                Tag tag = new Tag(description);
                tags.add(tag);
            }
            return tags;
        };

        this.modelMapper.typeMap(NewsDTO.class, NewsEntity.class).addMappings(mapper -> {
            mapper.using(tagFieldConverter).map(claim -> claim, NewsEntity::setTags);
        });
    }
    private void mapNewsEntityToNewsDTO() {
        Converter<NewsEntity, List<String>> tagFieldConverter = context -> {
            var src = context.getSource();
            List<String> tags = new ArrayList<>();
            for (Tag tag : src.getTags()) {
                tags.add(tag.getTag());
            }
            return tags;
        };

        this.modelMapper.typeMap(NewsEntity.class, NewsDTO.class).addMappings(mapper -> {
            mapper.using(tagFieldConverter).map(claim -> claim, NewsDTO::setTags);
        });
    }
    @Bean
    public ModelMapper getModelMapper() {
        return this.modelMapper;
    }
}
