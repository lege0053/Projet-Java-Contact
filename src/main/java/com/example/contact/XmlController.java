package com.example.contact;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/xml")
public class XmlController {

    @Autowired
    private ContactRepository contactRepository;

    @GetMapping(params = "action=listContacts", produces = MediaType.APPLICATION_XML_VALUE)
    public Iterable<Contact> listContacts() {
        return contactRepository.findAll();
    }

    @GetMapping(params = {"action=getContact", "id"}, produces = MediaType.APPLICATION_XML_VALUE)
    public Contact getContactById(Long id) {
        return contactRepository.findById(id).orElse(null);
    }
/*
    @DeleteMapping(params = {"action=delContact", "id"}, produces = MediaType.APPLICATION_XML_VALUE)
    public void deleteContactById(@RequestParam Long id) {
        contactRepository.deleteById(id);
    }*/
}