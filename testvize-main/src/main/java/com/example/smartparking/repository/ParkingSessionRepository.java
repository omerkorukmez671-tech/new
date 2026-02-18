package com.example.smartparking.repository;

import com.example.smartparking.entity.ParkingSession;
import com.example.smartparking.entity.ParkingStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ParkingSessionRepository extends JpaRepository<ParkingSession, Long> {

    /**
     * Verilen plaka ve durum için tek bir park oturumu arar.
     */
    Optional<ParkingSession> findByPlateAndStatus(String plate, ParkingStatus status);

    /**
     * Verilen duruma sahip tüm park oturumlarını döndürür.
     */
    List<ParkingSession> findAllByStatus(ParkingStatus status);

    /**
     * Verilen duruma sahip park oturumu sayısını döndürür.
     */
    long countByStatus(ParkingStatus status);

    /**
     * Verilen durum ve tarih aralığına göre park oturumlarını listeler.
     */
    List<ParkingSession> findAllByStatusAndExitTimeBetween(
            ParkingStatus status,
            LocalDateTime start,
            LocalDateTime end
    );
}

