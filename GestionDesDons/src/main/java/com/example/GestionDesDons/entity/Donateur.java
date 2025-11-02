package com.example.GestionDesDons.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Donateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String email;
    private String type;

    public Donateur(Long id, String nom, String email, String type) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.type = type;
    }

    public Donateur() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    @OneToMany(mappedBy = "donateur",cascade = CascadeType.ALL,orphanRemoval = true)
    private List <Don> dons;
}
