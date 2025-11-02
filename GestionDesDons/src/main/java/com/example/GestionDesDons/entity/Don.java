package com.example.GestionDesDons.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
@Entity
public class Don {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal montant;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateDon;
    private String moyen;

    public Don(Long id, BigDecimal montant, LocalDate dateDon, String moyen) {
        this.id = id;
        this.montant = montant;
        this.dateDon = dateDon;
        this.moyen = moyen;
    }

    public Don() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public LocalDate getDateDon() {
        return dateDon;
    }

    public void setDateDon(LocalDate dateDon) {
        this.dateDon = dateDon;
    }

    public String getMoyen() {
        return moyen;
    }

    public void setMoyen(String moyen) {
        this.moyen = moyen;
    }
    // Plusieurs dons appartiennent à une seule campagne
    @ManyToOne
    @JoinColumn(name = "campagne_id")
    private Campagne campagne;

    // Plusieurs dons appartiennent à un seul donateur
    @ManyToOne
    @JoinColumn(name = "donateur_id")
    private Donateur donateur;

    public Campagne getCampagne() {
        return campagne;
    }

    public void setCampagne(Campagne campagne) {
        this.campagne = campagne;
    }

    public Donateur getDonateur() {
        return donateur;
    }

    public void setDonateur(Donateur donateur) {
        this.donateur = donateur;
    }
}
