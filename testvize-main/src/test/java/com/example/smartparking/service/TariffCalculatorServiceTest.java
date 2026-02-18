package com.example.smartparking.service;

import com.example.smartparking.entity.Subscription;
import com.example.smartparking.entity.SubscriptionType;
import com.example.smartparking.entity.Tariff;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Soru 1 ve Soru 2: TariffCalculatorService.calculateFee için birim testleri.
 */
class TariffCalculatorServiceTest {

    private final TariffCalculatorService tariffCalculatorService = new TariffCalculatorService();

    /**
     * Soru 1 - Test 1:
     * Kısa süreli (20 dk) bir oturumda ücretin ücretsiz (0 TL) olduğunu doğrular.
     */
    @Test
    void calculateFee_shouldReturnZeroForShortSessionWithinFreeMinutes() {
        // Buraya yazılacak
    }

    /**
     * Soru 1 - Test 2:
     * 75 dakikalık bir oturumda ücretin tarife kurallarına göre 30.00 TL olduğunu doğrular.
     */
    @Test
    void calculateFee_shouldApplyFirstAndNextBlocksForLongerSession() {
        // Buraya yazılacak
    }

    /**
     * Soru 2 - Test 1:
     * STANDARD abonelikte %10 indirim uygulanmasını, aynı süre için önce normal,
     * sonra indirimli ücretin doğru hesaplandığını doğrular.
     */
    @Test
    void calculateFee_shouldApplyStandardSubscriptionDiscount() {
        // Buraya yazılacak
       
    }

    /**
     * Soru 2 - Test 2:
     * VIP abonelikte çok uzun bir oturumda ücretin VIP için belirlenen üst sınırı (80.00 TL)
     * aşmadığını doğrular.
     */
    @Test
    void calculateFee_shouldNotExceedVipMaxFeeForVeryLongSession() {
        // Buraya yazılacak
        
    }
}

