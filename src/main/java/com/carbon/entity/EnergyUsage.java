package com.carbon.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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

    @NotNull(message="electricity is required")
    @Positive
    private double electricityKwh;
    @NotNull(message = "petrol is required")
    @Positive
    private double petrolLiters;
    @NotNull(message = "wastage is required")
    @Positive
    private double wasteKg;

    private double carbonFootprint;
    private double predictedCarbon;
    private double recommendedElectricity;
    private double recommendedPetrol;

    private LocalDateTime createdAt;
}
