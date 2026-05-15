package integra.backend.registration.model;

import java.time.LocalDateTime;

public class RegistrationDto {
    private Long id;
    private Long userId;
    private Long eventId;
    private LocalDateTime registeredAt;
    private LocalDateTime updatedAt;
    private String status;

    public RegistrationDto() {}

    public RegistrationDto(Long id, Long userId, Long eventId, LocalDateTime registeredAt, LocalDateTime updatedAt, String status) {
        this.id = id;
        this.userId = userId;
        this.eventId = eventId;
        this.registeredAt = registeredAt;
        this.updatedAt = updatedAt;
        this.status = status;
    }

    public Long getId() { 
        return id; 
    }

    public void setId(Long id) { 
        this.id = id; 
    }

    public Long getUserId() { 
        return userId; 
    }

    public void setUserId(Long userId) { 
        this.userId = userId; 
    }

    public Long getEventId() { 
        return eventId; 
    }

    public void setEventId(Long eventId) { 
        this.eventId = eventId; 
    }

    public LocalDateTime getRegisteredAt() { 
        return registeredAt; 
    }

    public void setRegisteredAt(LocalDateTime registeredAt) { 
        this.registeredAt = registeredAt; 
    }

    public String getStatus() { 
        return status; 
    }

    public void setStatus(String status) { 
        this.status = status; 
    }

    public LocalDateTime getUpdatedAt() { 
        return updatedAt; 
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) { 
        this.updatedAt = updatedAt; 
    }
}