package com.example.contact;

import jakarta.persistence.*;

@Entity
public class Email {

    @Id
    private String address;

    @ManyToOne
    private Contact contact;

    protected Email() {}

    public Email(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public Contact getContact() {
        return contact;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
