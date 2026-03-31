package com.carbon.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnergyUsage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private double electricityKwh;
    @NotNull
    private double petrolLiters;
    @NotNull
    private double wasteKg;

    private double carbonFootprint;
    private double predictedCarbon;
    private double recommendedElectricity;
    private double recommendedPetrol;

    private LocalDateTime createdAt;
}
