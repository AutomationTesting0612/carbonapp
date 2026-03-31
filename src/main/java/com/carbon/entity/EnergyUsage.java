package com.carbon.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnergyUsage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double electricityKwh;
    private double petrolLiters;
    private double wasteKg;

    private double carbonFootprint;
    private double predictedCarbon;
    private double recommendedElectricity;
    private double recommendedPetrol;

    private LocalDateTime createdAt;
}
