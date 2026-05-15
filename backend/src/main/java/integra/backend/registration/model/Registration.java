package integra.backend.registration.model;

import integra.backend.event.model.Event;
import integra.backend.user.model.User;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "registrations")
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @Column(name = "registeredat", nullable = false, updatable = false)
    private LocalDateTime registeredAt;

    @Column(name = "updatedat")
    private LocalDateTime updatedAt;

    @PrePersist
    void initialize() {
        this.registeredAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        if (registeredAt == null) {
            registeredAt = LocalDateTime.now();
        }
    }

    @PreUpdate
    void updateTimestamp() {
        this.updatedAt = LocalDateTime.now(); 
    }

    private String status = "PENDING";

    public Registration() {}

    public Long getId() { 
        return id; 
    }

    public void setId(Long id) { 
        this.id = id; 
    }

    public User getUser() { 
        return user; 
    }

    public void setUser(User user) {
        this.user = user; 
    }

    public Event getEvent() { 
        return event; 
    }

    public void setEvent(Event event) { 
        this.event = event; 
    }

    public LocalDateTime getRegisteredAt() { 
        return registeredAt; 
    }

    public void setRegisteredAt(LocalDateTime registeredAt) { 
        this.registeredAt = registeredAt; 
    }

    public LocalDateTime getUpdatedAt() {
         return updatedAt; 
    }

    public void setUpdatedAt(LocalDateTime updatedAt) { 
        this.updatedAt = updatedAt;
    }

    public String getStatus() { 
        return status; 
    }

    public void setStatus(String status) { 
        this.status = status; 
    }
}