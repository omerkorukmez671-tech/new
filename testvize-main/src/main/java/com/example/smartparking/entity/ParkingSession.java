package com.example.smartparking.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "parking_sessions")
public class ParkingSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * Park oturumunun benzersiz kimliği.
     */
    private Long id;

    @Column(nullable = false, length = 20)
    /**
     * Park eden aracın plakası.
     */
    private String plate;

    @Column(name = "entry_time", nullable = false)
    /**
     * Aracın giriş zamanı.
     */
    private LocalDateTime entryTime;

    @Column(name = "exit_time")
    /**
     * Aracın çıkış zamanı (devam eden oturumlar için null olabilir).
     */
    private LocalDateTime exitTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    /**
     * Park oturumunun durumu (ACTIVE, COMPLETED, CANCELLED).
     */
    private ParkingStatus status;

    @Column(precision = 10, scale = 2)
    /**
     * Oturum için hesaplanan toplam ücret.
     */
    private BigDecimal fee;

    @Enumerated(EnumType.STRING)
    @Column(name = "subscription_type", nullable = false, length = 20)
    /**
     * Oturum başladığında araca ait abonelik tipi.
     */
    private SubscriptionType subscriptionType;

    /**
     * Varsayılan yapıcı metot.
     */
    public ParkingSession() {
    }

    /**
     * Oturum kimliğini döndürür.
     */
    public Long getId() {
        return id;
    }

    /**
     * Oturum kimliğini ayarlar.
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
     * Giriş zamanını döndürür.
     */
    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    /**
     * Giriş zamanını ayarlar.
     */
    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    /**
     * Çıkış zamanını döndürür.
     */
    public LocalDateTime getExitTime() {
        return exitTime;
    }

    /**
     * Çıkış zamanını ayarlar.
     */
    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    /**
     * Oturum durumunu döndürür.
     */
    public ParkingStatus getStatus() {
        return status;
    }

    /**
     * Oturum durumunu ayarlar.
     */
    public void setStatus(ParkingStatus status) {
        this.status = status;
    }

    /**
     * Ücreti döndürür.
     */
    public BigDecimal getFee() {
        return fee;
    }

    /**
     * Ücreti ayarlar.
     */
    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    /**
     * Abonelik tipini döndürür.
     */
    public SubscriptionType getSubscriptionType() {
        return subscriptionType;
    }

    /**
     * Abonelik tipini ayarlar.
     */
    public void setSubscriptionType(SubscriptionType subscriptionType) {
        this.subscriptionType = subscriptionType;
    }
}

