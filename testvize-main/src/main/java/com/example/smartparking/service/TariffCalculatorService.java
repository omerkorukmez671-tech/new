package com.example.smartparking.service;

import com.example.smartparking.entity.Subscription;
import com.example.smartparking.entity.SubscriptionType;
import com.example.smartparking.entity.Tariff;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class TariffCalculatorService {

    /**
     * Verilen giriş ve çıkış zamanı, tarife ve varsa abonelik bilgisine göre park ücretini hesaplar.
     *
     * @param entry         Araç giriş zamanı.
     * @param exit          Araç çıkış zamanı.
     * @param tariff        Hesaplamada kullanılacak tarife.
     * @param subscription  Varsa plakaya ait abonelik bilgisi.
     * @return Hesaplanan park ücreti.
     */
    public BigDecimal calculateFee(
            LocalDateTime entry,
            LocalDateTime exit,
            Tariff tariff,
            @Nullable Subscription subscription
    ) {
        if (entry == null || exit == null || tariff == null) {
            throw new IllegalArgumentException("Entry, exit and tariff must not be null");
        }

        long seconds = Duration.between(entry, exit).getSeconds();
        if (seconds <= 0) {
            return BigDecimal.ZERO;
        }
        long minutes = (seconds + 59) / 60; // ceil to next full minute

        if (minutes <= tariff.getFreeMinutes()) {
            return BigDecimal.ZERO;
        }

        BigDecimal fee;
        if (minutes <= tariff.getFirstBlockMinutes()) {
            fee = tariff.getFirstBlockFee();
        } else {
            fee = tariff.getFirstBlockFee();
            long remainingMinutes = minutes - tariff.getFirstBlockMinutes();
            long blocks = (remainingMinutes + tariff.getNextBlockMinutes() - 1L) / tariff.getNextBlockMinutes();
            BigDecimal additional = tariff.getNextBlockFee().multiply(BigDecimal.valueOf(blocks));
            fee = fee.add(additional);
        }

        if (fee.compareTo(tariff.getMaxFee()) > 0) {
            fee = tariff.getMaxFee();
        }

        if (subscription != null) {
            BigDecimal discountRate = subscription.getDiscountRate() != null
                    ? subscription.getDiscountRate()
                    : BigDecimal.ZERO;
            BigDecimal multiplier = BigDecimal.ONE.subtract(discountRate);
            fee = fee.multiply(multiplier).setScale(2, RoundingMode.HALF_UP);

            if (subscription.getType() == SubscriptionType.VIP) {
                BigDecimal vipMax = new BigDecimal("80.00");
                if (fee.compareTo(vipMax) > 0) {
                    fee = vipMax;
                }
            }
        }

        if (fee.compareTo(BigDecimal.ZERO) < 0) {
            fee = BigDecimal.ZERO;
        }

        return fee.setScale(2, RoundingMode.HALF_UP);
    }
}

