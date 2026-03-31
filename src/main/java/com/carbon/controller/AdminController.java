package com.carbon.controller;

import com.carbon.repository.EnergyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final EnergyRepository repository;

    @GetMapping("/admin")
    public String adminDashboard(Model model) {

        model.addAttribute("allData", repository.findAll());

        return "admin-dashboard";
    }
}
