package com.example.contact;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/customer")
    public String customer(@RequestParam(name="action", required=false, defaultValue="listContact") String action, Model model) {
        model.addAttribute("name", action);
        return "customer";
    }

    @GetMapping("/nouveau")
    public String customerForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer";
    }

    @PostMapping("/nouveau")
    public String customerSubmit(@RequestParam("lastName") String lastName, @RequestParam("firstName") String firstName, RedirectAttributes redirectAttributes) {
        Customer newCustomer = new Customer();
        newCustomer.setLastName(lastName);
        newCustomer.setFirstName(firstName);
        customerRepository.save(newCustomer);

        // Ajouter un flash attribute pour indiquer que le message de succès doit être affiché
        redirectAttributes.addFlashAttribute("addedContact", true);

        // Rediriger sur la page customer.html
        return "redirect:/customer";
    }

    @GetMapping("/supprimer/{id}")
    public String deleteCustomer(@PathVariable("id") Long id, Model model) {
        customerRepository.deleteById(id);
        model.addAttribute("message", "Le contact a été supprimé avec succès !");
        return "redirect:/customer";
    }


    @ModelAttribute("contactList")
    public Iterable<Customer> getContactList(){
        return customerRepository.findAll();
    }
}