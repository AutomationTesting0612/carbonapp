package com.carbon.controller;

import com.carbon.service.CarbonPredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ml")
public class MlController {

    @Autowired
    private CarbonPredictionService service;

    @GetMapping("/predict")
    public double predict(@RequestParam double electricity) {
        return service.predict(electricity);
    }
}
