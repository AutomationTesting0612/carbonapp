package com.carbon.controller;

import com.carbon.entity.EnergyUsage;
import com.carbon.entity.PredictionResponse;
import com.carbon.repository.EnergyRepository;
import com.carbon.service.CarbonCalculatorService;
import com.carbon.service.MlClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class DashboardController {

    private final EnergyRepository repository;
    private final CarbonCalculatorService calculator;
    private final MlClient mlClient;

    @GetMapping("/")
    public String dashboard(Model model) {
        model.addAttribute("energy", new EnergyUsage());
        return "dashboard";
    }

    @PostMapping("/submit")
    public String submit(@ModelAttribute EnergyUsage energy, Model model) {

        double carbon = calculator.calculate(
                energy.getElectricityKwh(),
                energy.getPetrolLiters(),
                energy.getWasteKg()
        );


        PredictionResponse prediction = mlClient.predict(energy.getElectricityKwh(), energy.getPetrolLiters(), energy.getWasteKg());

        energy.setCarbonFootprint(carbon);
        energy.setPredictedCarbon(prediction.getPredictedCarbon());
        energy.setRecommendedElectricity(prediction.getRecommendedElectricity());
        energy.setRecommendedPetrol(prediction.getRecommendedPetrol());
        energy.setCreatedAt(LocalDateTime.now());

        repository.save(energy);

        model.addAttribute("result", energy);

        return "result";
    }
}
