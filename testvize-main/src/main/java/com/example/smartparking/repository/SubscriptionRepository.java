package com.example.smartparking.repository;

import com.example.smartparking.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    /**
     * Verilen plakaya ait aboneliği döndürür.
     */
    Optional<Subscription> findByPlate(String plate);
}

