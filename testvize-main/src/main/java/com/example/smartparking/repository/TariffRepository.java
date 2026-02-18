package com.example.smartparking.repository;

import com.example.smartparking.entity.Tariff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TariffRepository extends JpaRepository<Tariff, Long> {

    // Tarife varlıkları için temel CRUD işlemlerini sağlayan JPA repository arayüzü.
}

