package com.example.smartparking.service;

import com.example.smartparking.entity.Subscription;
import com.example.smartparking.entity.SubscriptionType;
import com.example.smartparking.repository.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    /**
     * Abonelik servisini, abonelik deposu ile oluşturur.
     */
    public SubscriptionService(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    /**
     * Verilen plakaya ait aboneliği arar.
     *
     * @param plate Aranan araç plakası.
     * @return Abonelik varsa Optional içinde döner, yoksa boş döner.
     */
    public Optional<Subscription> findByPlate(String plate) {
        return subscriptionRepository.findByPlate(plate);
    }

    /**
     * Verilen plaka için abonelik oluşturur veya mevcut aboneliği günceller.
     *
     * @param plate         Araç plakası.
     * @param type          Abonelik tipi.
     * @param discountRate  İndirim oranı.
     * @return Oluşturulan veya güncellenen abonelik.
     */
    public Subscription createOrUpdateSubscription(String plate, SubscriptionType type, BigDecimal discountRate) {
        Subscription subscription = subscriptionRepository.findByPlate(plate)
                .orElseGet(Subscription::new);

        subscription.setPlate(plate);
        subscription.setType(type);
        subscription.setDiscountRate(discountRate);

        return subscriptionRepository.save(subscription);
    }
}

