package com.example.smartparking.service;

import com.example.smartparking.entity.ParkingSession;
import com.example.smartparking.entity.ParkingStatus;
import com.example.smartparking.entity.Subscription;
import com.example.smartparking.entity.SubscriptionType;
import com.example.smartparking.entity.Tariff;
import com.example.smartparking.repository.ParkingSessionRepository;
import com.example.smartparking.repository.TariffRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

/**
 * Soru 3: ParkingService.startSession ve endSession için birim testleri.
 */
@ExtendWith(MockitoExtension.class)
class ParkingServiceTest {

    @Mock
    private ParkingSessionRepository parkingSessionRepository;

    @Mock
    private CapacityService capacityService;

    @Mock
    private SubscriptionService subscriptionService;

    @Mock
    private TariffRepository tariffRepository;

    @Mock
    private TariffCalculatorService tariffCalculatorService;

    private ParkingService parkingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        parkingService = new ParkingService(
                parkingSessionRepository,
                capacityService,
                subscriptionService,
                tariffRepository,
                tariffCalculatorService
        );
    }

    /**
     * Soru 3 - Test 1:
     * Aynı plakaya ikinci kez giriş yapılmaya çalışıldığında,
     * aktif oturum olduğu için IllegalStateException fırlatıldığını doğrular.
     */
    @Test
    void startSession_whenActiveSessionExists_shouldThrowIllegalStateException() {
        // Buraya yazılacak
        
    }

    /**
     * Soru 3 - Test 2:
     * Hiç aktif oturumu olmayan bir plakada çıkış denendiğinde
     * NoSuchElementException fırlatıldığını doğrular.
     */
    @Test
    void endSession_whenNoActiveSession_shouldThrowNoSuchElementException() {
        // Buraya yazılacak
    }

    
}

