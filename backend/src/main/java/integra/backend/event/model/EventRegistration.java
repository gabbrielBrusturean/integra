package integra.backend.event.model;

import integra.backend.user.model.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "event_registrations", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"event_id", "user_id"})
})
public class EventRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "registered_at", nullable = false)
    private LocalDateTime registeredAt;

    public EventRegistration() {
    }

    public EventRegistration(Event event, User user) {
        this.event = event;
        this.user = user;
    }

    @PrePersist
    void prePersist() {
        if (registeredAt == null) {
            registeredAt = LocalDateTime.now();
        }
    }
}
