package integra.backend.registration.model;

import java.time.LocalDateTime;

public class UserRegistrationResponseDto {
    private Long eventId;
    private String title;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String location;
    private LocalDateTime registeredAt;
    private String status;

    public UserRegistrationResponseDto(Long eventId, String title, LocalDateTime startAt, LocalDateTime endAt, String location, LocalDateTime registeredAt, String status) {
        this.eventId = eventId;
        this.title = title;
        this.startAt = startAt;
        this.endAt = endAt;
        this.location = location;
        this.registeredAt = registeredAt;
        this.status = status;
    }

    public Long getEventId() { return eventId; }
    public void setEventId(Long eventId) { this.eventId = eventId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public LocalDateTime getStartAt() { return startAt; }
    public void setStartAt(LocalDateTime startAt) { this.startAt = startAt; }

    public LocalDateTime getEndAt() { return endAt; }
    public void setEndAt(LocalDateTime endAt) { this.endAt = endAt; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public LocalDateTime getRegisteredAt() { return registeredAt; }
    public void setRegisteredAt(LocalDateTime registeredAt) { this.registeredAt = registeredAt; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}