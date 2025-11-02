package com.example.GestionDesDons.controller;

import com.example.GestionDesDons.entity.Donateur;
import com.example.GestionDesDons.service.DonateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/donateurs")
public class DonateurController {
        @Autowired
        private DonateurService donateurService;

        @GetMapping({"", "/"})
        public String listDonateurs(Model model) {
            List<Donateur> donateurs = donateurService.getAllDonateurs();
            model.addAttribute("donateurs", donateurs);
            return "donateurs/list"; // => templates/donateurs/list.html
        }

        @GetMapping("/new")
        public String showAddForm(Model model) {
            model.addAttribute("donateur", new Donateur());
            return "donateurs/form"; // => templates/donateurs/form.html
        }

        @GetMapping("/edit/{id}")
        public String showEditForm(@PathVariable Long id, Model model) {
            Donateur d = donateurService.getDonateurById(id).orElse(null);
            if (d == null) return "redirect:/donateurs";
            model.addAttribute("donateur", d);
            return "donateurs/form";
        }

        @PostMapping("/save")
        public String saveDonateur(@ModelAttribute Donateur donateur) {
            donateurService.saveDonateur(donateur);
            return "redirect:/donateurs";
        }

        @GetMapping("/delete/{id}")
        public String deleteDonateur(@PathVariable Long id) {
            donateurService.deleteDonateur(id);
            return "redirect:/donateurs";
        }
}
