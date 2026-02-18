package com.example.smartparking.service;

import com.example.smartparking.config.ParkingConfigProperties;
import com.example.smartparking.entity.ParkingStatus;
import com.example.smartparking.repository.ParkingSessionRepository;
import org.springframework.stereotype.Service;

@Service
public class CapacityService {

    private final ParkingSessionRepository parkingSessionRepository;
    private final ParkingConfigProperties parkingConfigProperties;

    /**
     * Kapasite servisini, oturum deposu ve yapılandırma özellikleri ile oluşturur.
     */
    public CapacityService(ParkingSessionRepository parkingSessionRepository,
                           ParkingConfigProperties parkingConfigProperties) {
        this.parkingSessionRepository = parkingSessionRepository;
        this.parkingConfigProperties = parkingConfigProperties;
    }

    /**
     * Aktif park oturumu sayısının maksimum kapasiteye ulaşıp ulaşmadığını kontrol eder.
     *
     * @return Otopark doluysa true, aksi halde false.
     */
    public boolean isFull() {
        long activeCount = parkingSessionRepository.countByStatus(ParkingStatus.ACTIVE);
        return activeCount >= parkingConfigProperties.getMaxCapacity();
    }
}

