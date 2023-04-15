package com.example.contact;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String street;
    private String city;
    private String zipCode;

    @ManyToMany(mappedBy="addresses")
    private Set<Contact> contacts;

    protected Address() {}

    public Address(String street, String city, String zipCode) {
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
        this.contacts = new HashSet<>();
    }

    public void addContact(Contact contact) {
        this.contacts.add(contact);
        contact.getAddresses().add(this);
    }

    public Long getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Set<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }
}
