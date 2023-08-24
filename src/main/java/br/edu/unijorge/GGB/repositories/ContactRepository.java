package br.edu.unijorge.GGB.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unijorge.GGB.entitys.Contact;

import java.util.Optional;


public interface ContactRepository extends JpaRepository<Contact,Long> {
    Optional<Contact> findByEmailIgnoreCase(String email);
}



