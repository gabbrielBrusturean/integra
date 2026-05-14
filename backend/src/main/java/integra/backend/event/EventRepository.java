package integra.backend.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import integra.backend.event.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

}
