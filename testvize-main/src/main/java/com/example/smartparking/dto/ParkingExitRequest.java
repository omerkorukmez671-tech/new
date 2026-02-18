package com.example.smartparking.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public class ParkingExitRequest {

    @NotBlank
    /**
     * Parktan çıkmak isteyen aracın plakası.
     */
    private String plate;

    /**
     * Araç için özel bir çıkış zamanı (boş bırakılırsa sunucu saati kullanılır).
     */
    private LocalDateTime exitTime;

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
}

