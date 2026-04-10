package com.carbon.controller;

import ch.qos.logback.classic.spi.IThrowableProxy;
import com.carbon.entity.EnergyUsage;
import com.carbon.entity.PredictionResponse;
import com.carbon.exception.BadRequestException;
import com.carbon.exception.RecordAlreadyExist;
import com.carbon.repository.EnergyRepository;
import com.carbon.service.CarbonCalculatorService;
import com.carbon.service.MlClient;
import com.carbon.util.Constants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class DashboardControllerBackend {

    private final EnergyRepository repository;
    private final CarbonCalculatorService calculator;
    private final MlClient mlClient;

//    @GetMapping("/")
//    public String dashboard(Model model) {
//        model.addAttribute("energy", new EnergyUsage());
//        return "dashboard";
//    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public EnergyUsage createNew(@Valid @RequestBody EnergyUsage energy) throws Exception {

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

        if(repository.existsByElectricityKwh(energy.getElectricityKwh())) {
            throw new RecordAlreadyExist(Constants.conflictMessage);
        }

        if(energy.getElectricityKwh() < energy.getPetrolLiters()) {
            throw new BadRequestException(Constants.badRequestMessage);
        }

        repository.save(energy);

        return energy;
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public EnergyUsage update(@Valid @RequestBody EnergyUsage energy) throws Exception {

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

if(repository.existsByElectricityKwh(energy.getElectricityKwh())) {
    throw new Exception("Electricty ALready Defined");
}
        repository.save(energy);

        return energy;
    }

    @GetMapping("/get/{id}")
    public EnergyUsage get(@PathVariable Long id) {

        var entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prediction not found with id: " + id));

        return new EnergyUsage(
                entity.getId(),
                entity.getElectricityKwh(),
                entity.getWasteKg(),
                entity.getPetrolLiters(),
                entity.getCarbonFootprint(),
                entity.getPredictedCarbon(),
                entity.getRecommendedElectricity(),
                entity.getRecommendedPetrol(),
                entity.getCreatedAt()
        );
    }
    @GetMapping("/getParam")
    public EnergyUsage getParam(@RequestParam Long id) {

        var entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prediction not found with id: " + id));



        return new EnergyUsage(
                entity.getId(),
                entity.getElectricityKwh(),
                entity.getWasteKg(),
                entity.getPetrolLiters(),
                entity.getCarbonFootprint(),
                entity.getPredictedCarbon(),
                entity.getRecommendedElectricity(),
                entity.getRecommendedPetrol(),
                entity.getCreatedAt()
        );
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {

        if (!repository.existsById(id)) {
            throw new RuntimeException("Record not found with id: " + id);
        }

        repository.deleteById(id);
        return "Record deleted successfully with id: " + id;
    }

}
