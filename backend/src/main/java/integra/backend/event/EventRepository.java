package integra.backend.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.List;

import integra.backend.event.model.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
    @Query("select e from Event e where e.startAt >= :from and e.startAt < :to and (e.reminderSent = false or e.reminderSent is null)")
    List<Event> findByStartAtGreaterThanEqualAndStartAtLessThanAndReminderSentFalse(@Param("from") LocalDateTime from,
            @Param("to") LocalDateTime to);

}
