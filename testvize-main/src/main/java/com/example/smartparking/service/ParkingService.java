package com.example.smartparking.service;

import com.example.smartparking.entity.ParkingSession;
import com.example.smartparking.entity.ParkingStatus;
import com.example.smartparking.entity.Subscription;
import com.example.smartparking.entity.SubscriptionType;
import com.example.smartparking.entity.Tariff;
import com.example.smartparking.repository.ParkingSessionRepository;
import com.example.smartparking.repository.TariffRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ParkingService {

    private final ParkingSessionRepository parkingSessionRepository;
    private final CapacityService capacityService;
    private final SubscriptionService subscriptionService;
    private final TariffRepository tariffRepository;
    private final TariffCalculatorService tariffCalculatorService;

    /**
     * ParkingService'i gerekli bağımlılıklarla oluşturur.
     */
    public ParkingService(
            ParkingSessionRepository parkingSessionRepository,
            CapacityService capacityService,
            SubscriptionService subscriptionService,
            TariffRepository tariffRepository,
            TariffCalculatorService tariffCalculatorService
    ) {
        this.parkingSessionRepository = parkingSessionRepository;
        this.capacityService = capacityService;
        this.subscriptionService = subscriptionService;
        this.tariffRepository = tariffRepository;
        this.tariffCalculatorService = tariffCalculatorService;
    }

    /**
     * Verilen plaka için yeni bir park oturumu başlatır.
     *
     * @param plate     Araç plakası.
     * @param entryTime Giriş zamanı, null ise şu anki zaman kullanılır.
     * @return Oluşturulan aktif park oturumu.
     */
    @Transactional
    public ParkingSession startSession(String plate, LocalDateTime entryTime) {
        if (entryTime == null) {
            entryTime = LocalDateTime.now();
        }

        if (capacityService.isFull()) {
            throw new IllegalStateException("Parking is full");
        }

        parkingSessionRepository.findByPlateAndStatus(plate, ParkingStatus.ACTIVE)
                .ifPresent(existing -> {
                    throw new IllegalStateException("Active session already exists for plate");
                });

        SubscriptionType subscriptionType = subscriptionService.findByPlate(plate)
                .map(Subscription::getType)
                .orElse(SubscriptionType.NONE);

        ParkingSession session = new ParkingSession();
        session.setPlate(plate);
        session.setEntryTime(entryTime);
        session.setExitTime(null);
        session.setStatus(ParkingStatus.ACTIVE);
        session.setSubscriptionType(subscriptionType);
        session.setFee(null);

        return parkingSessionRepository.save(session);
    }

    /**
     * Verilen plaka için aktif park oturumunu sonlandırır ve ücreti hesaplar.
     *
     * @param plate    Araç plakası.
     * @param exitTime Çıkış zamanı, null ise şu anki zaman kullanılır.
     * @return Tamamlanan park oturumu.
     */
    @Transactional
    public ParkingSession endSession(String plate, LocalDateTime exitTime) {
        if (exitTime == null) {
            exitTime = LocalDateTime.now();
        }

        ParkingSession session = parkingSessionRepository.findByPlateAndStatus(plate, ParkingStatus.ACTIVE)
                .orElseThrow(() -> new NoSuchElementException("No active session for plate"));

        if (exitTime.isBefore(session.getEntryTime())) {
            throw new IllegalArgumentException("Exit time cannot be before entry time");
        }

        Tariff tariff = tariffRepository.findAll().stream()
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No active tariff configured"));

        Subscription subscription = subscriptionService.findByPlate(plate).orElse(null);

        BigDecimal fee = tariffCalculatorService.calculateFee(
                session.getEntryTime(),
                exitTime,
                tariff,
                subscription
        );

        session.setExitTime(exitTime);
        session.setStatus(ParkingStatus.COMPLETED);
        session.setFee(fee);

        return parkingSessionRepository.save(session);
    }

    /**
     * Tüm aktif park oturumlarını listeler.
     *
     * @return Aktif park oturumu listesi.
     */
    @Transactional(readOnly = true)
    public List<ParkingSession> listActiveSessions() {
        return parkingSessionRepository.findAllByStatus(ParkingStatus.ACTIVE);
    }

    /**
     * Tüm tamamlanmış park oturumlarını listeler.
     *
     * @return Tamamlanmış park oturumu listesi.
     */
    @Transactional(readOnly = true)
    public List<ParkingSession> listCompletedSessions() {
        return parkingSessionRepository.findAllByStatus(ParkingStatus.COMPLETED);
    }
}

