package com.example.GestionDesDons.service;

import com.example.GestionDesDons.entity.Campagne;
import com.example.GestionDesDons.entity.Don;
import com.example.GestionDesDons.entity.Donateur;
import com.example.GestionDesDons.repository.DonRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class DonService {

        private final DonRepository donRepository;

        public DonService(DonRepository donRepository) {
            this.donRepository = donRepository;
        }

        // Lister tous les dons
        public List<Don> getAllDons() {
            return donRepository.findAll();
        }

        // Trouver un don par ID
        public Optional<Don> getDonById(Long id) {
            return donRepository.findById(id);
        }

    // Supprimer un don
        public void deleteDon(Long id) {
            donRepository.deleteById(id);
        }

        // Trouver les dons d'une campagne
        public List<Don> getDonsByCampagne(Campagne campagne) {
            return donRepository.findByCampagne(campagne);
        }

        // Trouver les dons d'un donateur
        public List<Don> getDonsByDonateur(Donateur donateur) {
            return donRepository.findByDonateur(donateur);
        }

    public Don saveDon(Don don) {
        return donRepository.save(don);
    }

    // Statistiques
    public BigDecimal getTotalMontants() {
        return donRepository.sumMontants();
    }

    public List<Object[]> getMontantsParCampagne() {
        return donRepository.sumByCampagne();
    }

    public List<Object[]> getMontantsParMois() {
        return donRepository.sumByMonth();
    }

    public List<Object[]> getMontantsParMoyen() {
        return donRepository.sumByMoyen();
    }
}
