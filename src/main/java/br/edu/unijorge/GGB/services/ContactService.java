package br.edu.unijorge.GGB.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.unijorge.GGB.entitys.Contact;
import br.edu.unijorge.GGB.repositories.ContactRepository;

@Service
public class ContactService {

  @Autowired
  private ContactRepository contactRepository;

    public Contact createContact(Contact contact) {
        var contactEntity = contactRepository.findByEmailIgnoreCase(contact.getEmail());
        if(contactEntity.isPresent())
            return contact;
        return contactRepository.saveAndFlush(contact);
    }
}
