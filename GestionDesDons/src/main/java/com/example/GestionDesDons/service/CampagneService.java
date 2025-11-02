package com.example.GestionDesDons.service;

import com.example.GestionDesDons.entity.Campagne;
import com.example.GestionDesDons.repository.CampagneRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CampagneService {

    private final CampagneRepository campagneRepository;

    // Injection du repository
    public CampagneService(CampagneRepository campagneRepository) {
        this.campagneRepository = campagneRepository;
    }

    // Méthode pour récupérer toutes les campagnes
    public List<Campagne> getAllCampagnes() {
        return campagneRepository.findAll();
    }

    // Méthode pour récupérer une campagne par ID
    public Optional<Campagne> getCampagneById(Long id) {
        return campagneRepository.findById(id);
    }

    // Méthode pour ajouter ou modifier une campagne
    public Campagne saveCampagne(Campagne campagne) {
        return campagneRepository.save(campagne);
    }

    // Méthode pour supprimer une campagne
    public void deleteCampagne(Long id) {
        campagneRepository.deleteById(id);
    }

    // Trouver par titre
    public Campagne getByTitre(String titre) {
        if (titre == null || titre.isBlank()) return null;
        return campagneRepository.findByTitre(titre.trim());
    }

    // Trouver ou créer par titre
    public Campagne findOrCreateByTitre(String titre) {
        if (titre == null || titre.isBlank()) return null;
        Campagne c = campagneRepository.findByTitre(titre.trim());
        if (c != null) return c;
        Campagne n = new Campagne();
        n.setTitre(titre.trim());
        return campagneRepository.save(n);
    }
}