package com.example.smartparking.controller;

import com.example.smartparking.dto.ParkingEntryRequest;
import com.example.smartparking.dto.ParkingExitRequest;
import com.example.smartparking.entity.ParkingSession;
import com.example.smartparking.service.ParkingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parking")
public class ParkingController {

    private final ParkingService parkingService;

    /**
     * ParkingController'ı ParkingService ile oluşturur.
     */
    public ParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    /**
     * Yeni bir park oturumu başlatır.
     *
     * @param request Giriş isteği (plaka ve opsiyonel giriş zamanı).
     * @return Oluşturulan park oturumu ile 201 CREATED cevabı.
     */
    @PostMapping("/entry")
    public ResponseEntity<ParkingSession> startSession(@Valid @RequestBody ParkingEntryRequest request) {
        ParkingSession session = parkingService.startSession(request.getPlate(), request.getEntryTime());
        return ResponseEntity.status(HttpStatus.CREATED).body(session);
    }

    /**
     * Mevcut aktif park oturumunu sonlandırır.
     *
     * @param request Çıkış isteği (plaka ve opsiyonel çıkış zamanı).
     * @return Tamamlanan park oturumu ile 200 OK cevabı.
     */
    @PostMapping("/exit")
    public ResponseEntity<ParkingSession> endSession(@Valid @RequestBody ParkingExitRequest request) {
        ParkingSession session = parkingService.endSession(request.getPlate(), request.getExitTime());
        return ResponseEntity.ok(session);
    }

    /**
     * Tüm aktif park oturumlarını döndürür.
     */
    @GetMapping("/active")
    public List<ParkingSession> getActiveSessions() {
        return parkingService.listActiveSessions();
    }

    /**
     * Tüm tamamlanmış park oturumlarını döndürür.
     */
    @GetMapping("/completed")
    public List<ParkingSession> getCompletedSessions() {
        return parkingService.listCompletedSessions();
    }
}

