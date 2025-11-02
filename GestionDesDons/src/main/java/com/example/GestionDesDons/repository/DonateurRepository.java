package com.example.GestionDesDons.repository;

import com.example.GestionDesDons.entity.Donateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonateurRepository extends JpaRepository<Donateur,Long> {
    Donateur findByEmail(String email);
    Donateur findByNom(String nom);
}
