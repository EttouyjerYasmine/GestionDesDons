package com.example.GestionDesDons.controller;

import com.example.GestionDesDons.entity.Campagne;
import com.example.GestionDesDons.service.CampagneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/campagnes")
public class CampagneController {

    @Autowired
    private CampagneService campagneService;

    @GetMapping
    public String listCampagnes(Model model) {
        List<Campagne> campagnes = campagneService.getAllCampagnes();
        model.addAttribute("campagnes", campagnes);
        return "campagnes/list"; // => templates/campagnes/list.html
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("campagne", new Campagne());
        return "campagnes/form"; // => templates/campagnes/form.html
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Campagne c = campagneService.getCampagneById(id).orElse(null);
        if (c == null) return "redirect:/campagnes";
        model.addAttribute("campagne", c);
        return "campagnes/form";
    }

    @PostMapping("/save")
    public String saveCampagne(@ModelAttribute Campagne campagne) {
        campagneService.saveCampagne(campagne);
        return "redirect:/campagnes";
    }

    @GetMapping("/delete/{id}")
    public String deleteCampagne(@PathVariable Long id) {
        campagneService.deleteCampagne(id);
        return "redirect:/campagnes";
    }
}