package com.example.smartparking.dto;

import com.example.smartparking.entity.SubscriptionType;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class CreateSubscriptionRequest {

    @NotBlank
    /**
     * Abonelik tanımlanacak araç plakası.
     */
    private String plate;

    @NotNull
    /**
     * Abonelik tipi (NONE, STANDARD, VIP).
     */
    private SubscriptionType type;

    @NotNull
    @DecimalMin(value = "0.0")
    @DecimalMax(value = "1.0")
    /**
     * İndirim oranı (0.0 ile 1.0 arasında).
     */
    private BigDecimal discountRate;

    /**
     * Plaka değerini döndürür.
     */
    public String getPlate() {
        return plate;
    }

    /**
     * Plaka değerini ayarlar.
     */
    public void setPlate(String plate) {
        this.plate = plate;
    }

    /**
     * Abonelik tipini döndürür.
     */
    public SubscriptionType getType() {
        return type;
    }

    /**
     * Abonelik tipini ayarlar.
     */
    public void setType(SubscriptionType type) {
        this.type = type;
    }

    /**
     * İndirim oranını döndürür.
     */
    public BigDecimal getDiscountRate() {
        return discountRate;
    }

    /**
     * İndirim oranını ayarlar.
     */
    public void setDiscountRate(BigDecimal discountRate) {
        this.discountRate = discountRate;
    }
}

