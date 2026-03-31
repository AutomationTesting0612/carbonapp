package com.carbon.service;

import org.springframework.stereotype.Service;

@Service
public class CarbonCalculatorService {

    public double calculate(double electricity, double petrol, double waste) {

        double electricityCarbon = electricity * 0.92;
        double petrolCarbon = petrol * 2.31;
        double wasteCarbon = waste * 1.5;

        return electricityCarbon + petrolCarbon + wasteCarbon;
    }
}
