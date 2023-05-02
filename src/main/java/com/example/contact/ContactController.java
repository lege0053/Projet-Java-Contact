package com.example.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @GetMapping("/contact")
    public String contact(@RequestParam(name="action", required=false, defaultValue="listContact") String action, Model model) {
        model.addAttribute("name", action);
        return "contact";
    }

    @GetMapping("/nouveau")
    public String contactForm(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact";
    }

    @PostMapping("/nouveau")
    public String contactSubmit(@RequestParam("lastName") String lastName, @RequestParam("firstName") String firstName, RedirectAttributes redirectAttributes) {
        Contact newContact = new Contact();
        newContact.setLastName(lastName);
        newContact.setFirstName(firstName);
        contactRepository.save(newContact);

        // Ajouter un flash attribute pour indiquer que le message de succès doit être affiché
        redirectAttributes.addFlashAttribute("addedContact", true);

        // Rediriger sur la page contact.html
        return "redirect:/contact";
    }

    @GetMapping("/supprimer/{id}")
    public String deleteContact(@PathVariable("id") Long id, Model model,RedirectAttributes redirectAttributes) {
        contactRepository.deleteById(id);

        // Ajouter un flash attribute pour indiquer que le message de succès doit être affiché
        redirectAttributes.addFlashAttribute("deletedContact", true);

        model.addAttribute("message", "Le contact a été supprimé avec succès !");
        return "redirect:/contact";
    }

    @GetMapping("/modifier/{id}")
    public String editContactForm(@PathVariable("id") Long id, Model model) {
        Optional<Contact> contactOptional = contactRepository.findById(id);
        if (contactOptional.isPresent()) {
            model.addAttribute("contact", contactOptional.get());
            return "editContact";
        } else {
            model.addAttribute("message", "Le contact que vous cherchez n'existe pas !");
            return "redirect:/contact";
        }
    }

    @PostMapping("/modifier/{id}")
    public String updateContact(@PathVariable("id") Long id, @ModelAttribute Contact contact, RedirectAttributes redirectAttributes) {
        Contact existingContact = contactRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid contact Id:" + id));
        existingContact.setLastName(contact.getLastName());
        existingContact.setFirstName(contact.getFirstName());
        contactRepository.save(existingContact);

        // Ajouter un flash attribute pour indiquer que le message de succès doit être affiché
        redirectAttributes.addFlashAttribute("updatedContact", true);

        // Rediriger sur la page contact.html
        return "redirect:/contact";
    }

    @ModelAttribute("contactList")
    public Iterable<Contact> getContactList(){
        return contactRepository.findAll();
    }

}