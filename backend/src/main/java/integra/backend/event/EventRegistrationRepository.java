package integra.backend.event;

import integra.backend.event.model.EventRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRegistrationRepository extends JpaRepository<EventRegistration, Long> {
    Optional<EventRegistration> findByEventIdAndUserId(Long eventId, Long userId);
    boolean existsByEventIdAndUserId(Long eventId, Long userId);
    List<EventRegistration> findByEventId(Long eventId);
}
