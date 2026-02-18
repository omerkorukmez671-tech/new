package com.example.smartparking.controller;

import com.example.smartparking.dto.CreateSubscriptionRequest;
import com.example.smartparking.entity.Subscription;
import com.example.smartparking.service.SubscriptionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    /**
     * SubscriptionController'ı abonelik servisi ile oluşturur.
     */
    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    /**
     * Yeni bir abonelik oluşturur veya mevcut aboneliği günceller.
     *
     * @param request Abonelik oluşturma/güncelleme isteği.
     * @return Oluşturulan veya güncellenen abonelik.
     */
    @PostMapping
    public ResponseEntity<Subscription> createOrUpdate(@Valid @RequestBody CreateSubscriptionRequest request) {
        Subscription subscription = subscriptionService.createOrUpdateSubscription(
                request.getPlate(),
                request.getType(),
                request.getDiscountRate()
        );
        return ResponseEntity.ok(subscription);
    }

    /**
     * Verilen plakaya ait aboneliği döndürür.
     *
     * @param plate Sorgulanan plaka.
     * @return Abonelik bulunursa 200, bulunamazsa 404 döndürür.
     */
    @GetMapping("/{plate}")
    public ResponseEntity<Subscription> getByPlate(@PathVariable String plate) {
        return subscriptionService.findByPlate(plate)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

