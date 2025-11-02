package com.example.GestionDesDons.repository;

import com.example.GestionDesDons.entity.Campagne;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampagneRepository extends JpaRepository<Campagne, Long> {
    Campagne findByTitre(String titre);
}
