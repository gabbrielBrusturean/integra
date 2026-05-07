package integra.backend.event;

import org.springframework.data.jpa.repository.JpaRepository;

import integra.backend.event.model.Event;

public interface EventRepository extends JpaRepository<Event, Long> {

}
