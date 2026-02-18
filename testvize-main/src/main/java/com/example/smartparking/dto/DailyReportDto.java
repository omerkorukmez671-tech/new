package com.example.smartparking.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DailyReportDto {

    /**
     * Raporun ait olduğu gün.
     */
    private LocalDate date;
    /**
     * O gün tamamlanan oturum sayısı.
     */
    private long completedSessionCount;
    /**
     * O gün elde edilen toplam gelir.
     */
    private BigDecimal totalRevenue;
    /**
     * Oturumların ortalama süreleri (dakika cinsinden).
     */
    private long averageDurationMinutes;

    /**
     * Varsayılan yapıcı metot.
     */
    public DailyReportDto() {
    }

    /**
     * Tüm alanları alan yapıcı metot.
     */
    public DailyReportDto(LocalDate date, long completedSessionCount, BigDecimal totalRevenue, long averageDurationMinutes) {
        this.date = date;
        this.completedSessionCount = completedSessionCount;
        this.totalRevenue = totalRevenue;
        this.averageDurationMinutes = averageDurationMinutes;
    }

    /**
     * Rapor tarihini döndürür.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Rapor tarihini ayarlar.
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Tamamlanan oturum sayısını döndürür.
     */
    public long getCompletedSessionCount() {
        return completedSessionCount;
    }

    /**
     * Tamamlanan oturum sayısını ayarlar.
     */
    public void setCompletedSessionCount(long completedSessionCount) {
        this.completedSessionCount = completedSessionCount;
    }

    /**
     * Toplam geliri döndürür.
     */
    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    /**
     * Toplam geliri ayarlar.
     */
    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    /**
     * Ortalama süreyi (dakika) döndürür.
     */
    public long getAverageDurationMinutes() {
        return averageDurationMinutes;
    }

    /**
     * Ortalama süreyi (dakika) ayarlar.
     */
    public void setAverageDurationMinutes(long averageDurationMinutes) {
        this.averageDurationMinutes = averageDurationMinutes;
    }
}

