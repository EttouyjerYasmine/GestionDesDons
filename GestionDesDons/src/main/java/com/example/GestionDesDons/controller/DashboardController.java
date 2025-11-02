package com.example.GestionDesDons.controller;

import com.example.GestionDesDons.service.DonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class DashboardController {

    @Autowired
    private DonService donService;

    @GetMapping
    public String home() {
        return "redirect:/dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        BigDecimal total = donService.getTotalMontants();
        model.addAttribute("totalDons", total);
        return "dashboard/index";
    }

    @GetMapping("/api/stats/total")
    @ResponseBody
    public Map<String, Object> total() {
        Map<String, Object> res = new HashMap<>();
        res.put("total", donService.getTotalMontants());
        return res;
    }

    @GetMapping("/api/stats/par-campagne")
    @ResponseBody
    public ResponseEntity<?> byCampagne() {
        List<Object[]> rows = donService.getMontantsParCampagne();
        Map<String, Object> res = new HashMap<>();
        res.put("labels", rows.stream().map(r -> (String) r[0]).toArray());
        res.put("data", rows.stream().map(r -> ((java.math.BigDecimal) r[1])).toArray());
        return ResponseEntity.ok(res);
    }

    @GetMapping("/api/stats/par-mois")
    @ResponseBody
    public ResponseEntity<?> byMonth() {
        List<Object[]> rows = donService.getMontantsParMois();
        Map<String, Object> res = new HashMap<>();
        res.put("labels", rows.stream().map(r -> (String) r[0]).toArray());
        res.put("data", rows.stream().map(r -> ((java.math.BigDecimal) r[1])).toArray());
        return ResponseEntity.ok(res);
    }

    @GetMapping("/api/stats/par-moyen")
    @ResponseBody
    public ResponseEntity<?> byMoyen() {
        List<Object[]> rows = donService.getMontantsParMoyen();
        Map<String, Object> res = new HashMap<>();
        res.put("labels", rows.stream().map(r -> (String) r[0]).toArray());
        res.put("data", rows.stream().map(r -> ((java.math.BigDecimal) r[1])).toArray());
        return ResponseEntity.ok(res);
    }
}
