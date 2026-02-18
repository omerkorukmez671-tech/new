package com.example.smartparking.controller;

import com.example.smartparking.dto.DailyReportDto;
import com.example.smartparking.entity.ParkingSession;
import com.example.smartparking.entity.ParkingStatus;
import com.example.smartparking.repository.ParkingSessionRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ParkingSessionRepository parkingSessionRepository;

    /**
     * ReportController'ı ParkingSessionRepository ile oluşturur.
     */
    public ReportController(ParkingSessionRepository parkingSessionRepository) {
        this.parkingSessionRepository = parkingSessionRepository;
    }

    /**
     * Verilen tarihe ait günlük raporu döndürür.
     * Gün içindeki tamamlanmış oturum sayısını, toplam geliri ve ortalama süreyi hesaplar.
     *
     * @param date Rapor istenen gün (YYYY-MM-DD).
     * @return İlgili güne ait günlük rapor DTO'su.
     */
    @GetMapping("/daily")
    public DailyReportDto getDailyReport(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.plusDays(1).atStartOfDay().minusNanos(1);

        List<ParkingSession> sessions = parkingSessionRepository
                .findAllByStatusAndExitTimeBetween(ParkingStatus.COMPLETED, start, end);

        long count = sessions.size();

        BigDecimal totalRevenue = sessions.stream()
                .map(s -> s.getFee() != null ? s.getFee() : BigDecimal.ZERO)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        long averageMinutes = 0;
        if (!sessions.isEmpty()) {
            double avg = sessions.stream()
                    .filter(s -> s.getEntryTime() != null && s.getExitTime() != null)
                    .mapToLong(s -> Duration.between(s.getEntryTime(), s.getExitTime()).toMinutes())
                    .average()
                    .orElse(0.0);
            averageMinutes = (long) avg;
        }

        return new DailyReportDto(date, count, totalRevenue, averageMinutes);
    }
}

