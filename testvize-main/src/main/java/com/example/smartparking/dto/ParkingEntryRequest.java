package com.example.smartparking.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public class ParkingEntryRequest {

    @NotBlank
    /**
     * Park etmek isteyen aracın plakası.
     */
    private String plate;

    /**
     * Araç için özel bir giriş zamanı (boş bırakılırsa sunucu saati kullanılır).
     */
    private LocalDateTime entryTime;

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
}

