package com.carbon.repository;

import com.carbon.entity.EnergyUsage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.Array;
import java.util.List;

public interface EnergyRepository extends JpaRepository<EnergyUsage, Long> {

    boolean existsByElectricityKwh(double electricityKwh);
}
