package com.example.smartparking;

import com.example.smartparking.entity.Tariff;
import com.example.smartparking.repository.TariffRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
@ConfigurationPropertiesScan("com.example.smartparking")
public class SmartParkingApplication {

    /**
     * Uygulamayı başlatan ana metot.
     */
    public static void main(String[] args) {
        SpringApplication.run(SmartParkingApplication.class, args);
    }

    /**
     * Uygulama ilk çalıştığında varsayılan tarifeyi oluşturan başlangıç koşucusu.
     * Eğer veritabanında hiç tarife yoksa bir adet varsayılan tarife ekler.
     */
    @Bean
    CommandLineRunner initDefaultTariff(TariffRepository tariffRepository) {
        return args -> {
            if (tariffRepository.count() == 0) {
                Tariff tariff = new Tariff();
                tariff.setName("Default Tariff");
                tariff.setFreeMinutes(30);
                tariff.setFirstBlockMinutes(60);
                tariff.setFirstBlockFee(new BigDecimal("20.00"));
                tariff.setNextBlockMinutes(60);
                tariff.setNextBlockFee(new BigDecimal("10.00"));
                tariff.setMaxFee(new BigDecimal("100.00"));
                tariffRepository.save(tariff);
            }
        };
    }
}
