package com.example.smartparking.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


/**
 * Soru 4: ParkingController için entegrasyon testi.
 * MockMvc ile abonelik oluşturma, giriş, çıkış ve tamamlanan oturumların listelenmesi uçtan uca test edilir.
 */
@SpringBootTest
@AutoConfigureMockMvc
class ParkingControllerIT {

    @Autowired
    private MockMvc mockMvc;

    /**
     * STANDARD abonelik oluşturulup aynı plaka ile otoparka giriş/çıkış yapıldıktan sonra
     * HTTP durum kodları ve dönen JSON alanlarının beklenen değerleri içerdiğini
     * ve tamamlanan oturumların listesinde bu kaydın yer aldığını doğrular.
     */
    @Test
    void fullParkingFlow_withSubscription_shouldWorkAsExpected() throws Exception {
        // Soru 4: Entegre test senaryosu buraya yazılacak
        
    }
}

