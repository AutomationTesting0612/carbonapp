package com.carbon.repository;

import com.carbon.entity.EnergyUsage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnergyRepository extends JpaRepository<EnergyUsage, Long> {
}
