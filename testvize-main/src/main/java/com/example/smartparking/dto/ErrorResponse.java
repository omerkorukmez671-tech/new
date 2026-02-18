package com.example.smartparking.dto;

import java.time.LocalDateTime;

public class ErrorResponse {

    /**
     * Hatanın oluştuğu zaman damgası.
     */
    private LocalDateTime timestamp;
    /**
     * HTTP durum kodu.
     */
    private int status;
    /**
     * HTTP hata başlığı (örn. BAD_REQUEST).
     */
    private String error;
    /**
     * Detaylı hata mesajı.
     */
    private String message;
    /**
     * Hatanın oluştuğu istek yolu.
     */
    private String path;

    /**
     * Varsayılan yapıcı metot.
     */
    public ErrorResponse() {
    }

    /**
     * Tüm alanları alan yapıcı metot.
     */
    public ErrorResponse(LocalDateTime timestamp, int status, String error, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    /**
     * Zaman damgasını döndürür.
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Zaman damgasını ayarlar.
     */
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * HTTP durum kodunu döndürür.
     */
    public int getStatus() {
        return status;
    }

    /**
     * HTTP durum kodunu ayarlar.
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Hata başlığını döndürür.
     */
    public String getError() {
        return error;
    }

    /**
     * Hata başlığını ayarlar.
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * Hata mesajını döndürür.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Hata mesajını ayarlar.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * İstek yolunu döndürür.
     */
    public String getPath() {
        return path;
    }

    /**
     * İstek yolunu ayarlar.
     */
    public void setPath(String path) {
        this.path = path;
    }
}

