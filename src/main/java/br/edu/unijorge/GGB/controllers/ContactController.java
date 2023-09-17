package br.edu.unijorge.GGB.controllers;

import br.edu.unijorge.GGB.DTOs.NewsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.unijorge.GGB.entitys.Contact;
import br.edu.unijorge.GGB.services.ContactService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/v1/contact")
public class ContactController {
  
    @Autowired
    private ContactService contactService;

    @PostMapping
    public ResponseEntity<Contact> createContact(@RequestBody Contact contact){
        Contact createdContact = contactService.createContact(contact);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdContact);
    }

    @GetMapping
    public ResponseEntity<List<Contact>> getContact(){
        var Contact = contactService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(Contact);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable Long id){
        var Contact = contactService.findById(id);
        if(Contact == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(Contact);
    }

}
