package integra.backend.event;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import integra.backend.event.model.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findAllByOwnerId(Long ownerId);
}