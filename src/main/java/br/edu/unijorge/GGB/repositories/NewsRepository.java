package br.edu.unijorge.GGB.repositories;

import br.edu.unijorge.GGB.entitys.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<NewsEntity,Long> {
    List<NewsEntity> findAllByOrderByDateDesc();
}
