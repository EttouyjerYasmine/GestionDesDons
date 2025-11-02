package com.example.GestionDesDons.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Campagne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "titte")
    private String titre;
    private BigDecimal objectif;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate debut;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fin;

    public Campagne(Long id, String titre, BigDecimal objectif, LocalDate debut, LocalDate fin) {
        this.id = id;
        this.titre = titre;
        this.objectif = objectif;
        this.debut = debut;
        this.fin = fin;
    }

    public Campagne() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public BigDecimal getObjectif() {
        return objectif;
    }

    public void setObjectif(BigDecimal objectif) {
        this.objectif = objectif;
    }

    public LocalDate getDebut() {
        return debut;
    }

    public void setDebut(LocalDate debut) {
        this.debut = debut;
    }

    public LocalDate getFin() {
        return fin;
    }

    public void setFin(LocalDate fin) {
        this.fin = fin;
    }
    @OneToMany(mappedBy = "campagne", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Don> dons;
}
