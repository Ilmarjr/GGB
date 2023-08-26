package br.edu.unijorge.GGB.repositories;

import br.edu.unijorge.GGB.entitys.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag,Long> {
    Optional<Tag> findByTagIgnoreCase(String tag);
}
