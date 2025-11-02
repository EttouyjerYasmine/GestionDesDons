package com.example.GestionDesDons.controller;

import com.example.GestionDesDons.entity.Don;
import com.example.GestionDesDons.service.CampagneService;
import com.example.GestionDesDons.service.DonService;
import com.example.GestionDesDons.service.DonateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/dons")
public class DonController {
    @Autowired
    private DonService donService;

    @Autowired
    private DonateurService donateurService;

    @Autowired
    private CampagneService campagneService;

    // 🟢 Afficher la liste des dons
    @GetMapping({"", "/"})
    public String listDons(Model model) {
        List<Don> dons = donService.getAllDons();
        model.addAttribute("dons", dons);
        return "dons/list"; // => templates/dons/list.html
    }

    // 🟢 Formulaire d’ajout d’un don
    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("don", new Don());
        return "dons/form"; // => templates/dons/form.html
    }

    // 🟡 Formulaire de modification d'un don
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Don don = donService.getDonById(id).orElse(null);
        if (don == null) {
            return "redirect:/dons";
        }
        model.addAttribute("don", don);
        return "dons/form";
    }

    // 🟢 Enregistrer un don
    @PostMapping("/save")
    public String saveDon(@ModelAttribute Don don,
                          @RequestParam(name = "campagneTitre", required = false) String campagneTitre,
                          @RequestParam(name = "donateurNom", required = false) String donateurNom,
                          Model model) {
        // Associer Campagne et Donateur à partir de la saisie texte (trouver ou créer)
        if (campagneTitre != null) {
            var camp = campagneService.findOrCreateByTitre(campagneTitre);
            don.setCampagne(camp);
        }
        if (donateurNom != null) {
            var donat = donateurService.findOrCreateByNom(donateurNom);
            don.setDonateur(donat);
        }
        donService.saveDon(don);
        return "redirect:/dons";
    }

    // 🟢 Supprimer un don
    @GetMapping("/delete/{id}")
    public String deleteDon(@PathVariable Long id) {
        donService.deleteDon(id);
        return "redirect:/dons";
    }
}

