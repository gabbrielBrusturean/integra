package integra.backend.event;

import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

import integra.backend.event.model.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByStartAtGreaterThanEqualAndStartAtLessThanAndReminderSentFalse(LocalDateTime from,
            LocalDateTime to);

}
