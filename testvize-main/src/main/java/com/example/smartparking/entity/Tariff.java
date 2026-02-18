package com.example.smartparking.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "tariffs")
public class Tariff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * Tarifenin benzersiz kimliği.
     */
    private Long id;

    @Column(nullable = false, length = 100)
    /**
     * Tarifenin görünen adı.
     */
    private String name;

    @Column(nullable = false)
    /**
     * Ücretsiz park edilebilen dakika sayısı.
     */
    private int freeMinutes;

    @Column(nullable = false)
    /**
     * İlk ücretlendirme bloğunun dakika süresi.
     */
    private int firstBlockMinutes;

    @Column(nullable = false, precision = 10, scale = 2)
    /**
     * İlk blok için alınan ücret.
     */
    private BigDecimal firstBlockFee;

    @Column(nullable = false)
    /**
     * Sonraki blokların dakika süresi.
     */
    private int nextBlockMinutes;

    @Column(nullable = false, precision = 10, scale = 2)
    /**
     * Her bir ek blok için alınan ücret.
     */
    private BigDecimal nextBlockFee;

    @Column(nullable = false, precision = 10, scale = 2)
    /**
     * Toplam ücret için üst sınır (maksimum ücret).
     */
    private BigDecimal maxFee;

    /**
     * Varsayılan yapıcı metot.
     */
    public Tariff() {
    }

    /**
     * Tarifenin kimliğini döndürür.
     */
    public Long getId() {
        return id;
    }

    /**
     * Tarifenin kimliğini ayarlar.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Tarifenin adını döndürür.
     */
    public String getName() {
        return name;
    }

    /**
     * Tarifenin adını ayarlar.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Ücretsiz dakika sayısını döndürür.
     */
    public int getFreeMinutes() {
        return freeMinutes;
    }

    /**
     * Ücretsiz dakika sayısını ayarlar.
     */
    public void setFreeMinutes(int freeMinutes) {
        this.freeMinutes = freeMinutes;
    }

    /**
     * İlk blok dakika süresini döndürür.
     */
    public int getFirstBlockMinutes() {
        return firstBlockMinutes;
    }

    /**
     * İlk blok dakika süresini ayarlar.
     */
    public void setFirstBlockMinutes(int firstBlockMinutes) {
        this.firstBlockMinutes = firstBlockMinutes;
    }

    /**
     * İlk blok ücretini döndürür.
     */
    public BigDecimal getFirstBlockFee() {
        return firstBlockFee;
    }

    /**
     * İlk blok ücretini ayarlar.
     */
    public void setFirstBlockFee(BigDecimal firstBlockFee) {
        this.firstBlockFee = firstBlockFee;
    }

    /**
     * Sonraki blok dakika süresini döndürür.
     */
    public int getNextBlockMinutes() {
        return nextBlockMinutes;
    }

    /**
     * Sonraki blok dakika süresini ayarlar.
     */
    public void setNextBlockMinutes(int nextBlockMinutes) {
        this.nextBlockMinutes = nextBlockMinutes;
    }

    /**
     * Sonraki blok ücretini döndürür.
     */
    public BigDecimal getNextBlockFee() {
        return nextBlockFee;
    }

    /**
     * Sonraki blok ücretini ayarlar.
     */
    public void setNextBlockFee(BigDecimal nextBlockFee) {
        this.nextBlockFee = nextBlockFee;
    }

    /**
     * Maksimum ücreti döndürür.
     */
    public BigDecimal getMaxFee() {
        return maxFee;
    }

    /**
     * Maksimum ücreti ayarlar.
     */
    public void setMaxFee(BigDecimal maxFee) {
        this.maxFee = maxFee;
    }
}

