package com.example.smartparking.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "subscriptions", uniqueConstraints = {
        @UniqueConstraint(name = "uk_subscription_plate", columnNames = "plate")
})
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * Aboneliğin benzersiz kimliği.
     */
    private Long id;

    @Column(nullable = false, length = 20)
    /**
     * Araca ait plaka bilgisi (benzersiz).
     */
    private String plate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    /**
     * Abonelik tipi (NONE, STANDARD, VIP).
     */
    private SubscriptionType type;

    @Column(nullable = false, precision = 5, scale = 2)
    /**
     * İndirim oranı (0.10 = %10 indirim gibi).
     */
    private BigDecimal discountRate;

    /**
     * Varsayılan yapıcı metot.
     */
    public Subscription() {
    }

    /**
     * Abonelik kimliğini döndürür.
     */
    public Long getId() {
        return id;
    }

    /**
     * Abonelik kimliğini ayarlar.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Plaka bilgisini döndürür.
     */
    public String getPlate() {
        return plate;
    }

    /**
     * Plaka bilgisini ayarlar.
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

