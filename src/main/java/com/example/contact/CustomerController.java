package com.example.contact;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;
    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="action", required=false, defaultValue="listContact") String action, Model model) {
        model.addAttribute("name", action);
        return "greeting";
    }

    @GetMapping("/nouveau")
    public String greetingForm(Model model) {
        model.addAttribute("greeting", new Customer());
        return "greeting";
    }

    @PostMapping("/nouveau")
    public String greetingSubmit(@ModelAttribute Customer customer, Model model) {
        model.addAttribute("greeting", customer);
        return "greeting";
    }

    @ModelAttribute("contactList")
    public Iterable<Customer> getContactList(){
        return customerRepository.findAll();
    }

    private long id;
    private String content;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}