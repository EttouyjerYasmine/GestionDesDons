package com.example.GestionDesDons.service;

import com.example.GestionDesDons.entity.Donateur;
import com.example.GestionDesDons.repository.DonateurRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DonateurService {

    private final DonateurRepository donateurRepository;

    public DonateurService(DonateurRepository donateurRepository) {
        this.donateurRepository = donateurRepository;
    }

    // Lister tous les donateurs
    public List<Donateur> getAllDonateurs() {
        return donateurRepository.findAll();
    }

    // Trouver un donateur par ID
    public Optional<Donateur> getDonateurById(Long id) {
        return donateurRepository.findById(id);
    }

    // Ajouter / modifier un donateur
    public Donateur saveDonateur(Donateur donateur) {
        return donateurRepository.save(donateur);
    }

    // Supprimer un donateur
    public void deleteDonateur(Long id) {
        donateurRepository.deleteById(id);
    }

    // Trouver un donateur par email
    public Donateur getDonateurByEmail(String email) {
        return donateurRepository.findByEmail((email));
    }

    // Trouver un donateur par nom
    public Donateur getByNom(String nom) {
        if (nom == null || nom.isBlank()) return null;
        return donateurRepository.findByNom(nom.trim());
    }

    // Trouver ou créer un donateur par nom
    public Donateur findOrCreateByNom(String nom) {
        if (nom == null || nom.isBlank()) return null;
        Donateur d = donateurRepository.findByNom(nom.trim());
        if (d != null) return d;
        Donateur n = new Donateur();
        n.setNom(nom.trim());
        return donateurRepository.save(n);
    }
}