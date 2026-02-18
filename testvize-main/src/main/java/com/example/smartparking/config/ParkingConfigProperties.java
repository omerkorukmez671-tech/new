package com.example.smartparking.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "parking")
public class ParkingConfigProperties {

    /**
     * Aynı anda izin verilen maksimum aktif park oturumu sayısı.
     */
    private int maxCapacity = 100;

    /**
     * Maksimum kapasiteyi döndürür.
     */
    public int getMaxCapacity() {
        return maxCapacity;
    }

    /**
     * Maksimum kapasiteyi ayarlar.
     */
    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }
}
