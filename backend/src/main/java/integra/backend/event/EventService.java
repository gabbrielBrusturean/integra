package integra.backend.event;
import integra.backend.event.model.RegisteredVolunteerDto;
import integra.backend.event.model.Event;
import integra.backend.exception.ResourceNotFoundException;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class EventService {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getAll() {
        return eventRepository.findAll();
    }

    public Event getById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event with id " + id + " was not found"));
    }

    public Event create(Event event) {
        return eventRepository.save(event);
    }

    public void deleteById(Long id) {
        Event existing = getById(id);
        eventRepository.delete(existing);
    }

    public Event update(Long id, Event updated) {
        Event existing = getById(id);
        existing.setTitle(updated.getTitle());
        existing.setDescription(updated.getDescription());
        existing.setLocation(updated.getLocation());
        existing.setStartAt(updated.getStartAt());
        existing.setEndAt(updated.getEndAt());
        existing.setMaxParticipants(updated.getMaxParticipants());
        return eventRepository.save(existing);
    }

    
    public List<RegisteredVolunteerDto> getRegisteredVolunteers(Long eventId, String search) {
        List<RegisteredVolunteerDto> mockData = List.of(
            new RegisteredVolunteerDto(1L, "Ion", "Popescu", "ion.p@email.com"),
            new RegisteredVolunteerDto(2L, "Maria", "Ionescu", "maria.i@email.com"),
            new RegisteredVolunteerDto(3L, "Andrei", "Vasile", "andrei.v@test.ro"),
            new RegisteredVolunteerDto(4L, "Elena", "Dumitru", "elena.d@email.com"),
            new RegisteredVolunteerDto(5L, "Ioana", "Radu", "ioana.r@example.com"),
            new RegisteredVolunteerDto(6L, "Cristian", "Stan", "c.stan@tech.ro"),
            new RegisteredVolunteerDto(7L, "Simona", "Pop", "simona.p@email.com"),
            new RegisteredVolunteerDto(8L, "Mihai", "Popa", "mihai.p@test.com"),
            new RegisteredVolunteerDto(9L, "Ana", "Blandiana", "ana.b@lit.ro"),
            new RegisteredVolunteerDto(10L, "George", "Enescu", "george.e@music.ro"),
            new RegisteredVolunteerDto(11L, "Victor", "Breb", "victor.b@tech.com"),
            new RegisteredVolunteerDto(12L, "Laura", "Codruța", "laura.c@justice.ro"),
            new RegisteredVolunteerDto(14L, "Victoria", "Georgescu", "victoria.geo@gmail.ro"),
            new RegisteredVolunteerDto(16L, "Marius", "Avram", "marius.avram@gmail.com")


        );

        List<RegisteredVolunteerDto> eventSpecificList = mockData.stream()
        .filter(v -> (eventId % 2 == 0) ? (v.id() % 2 == 0) : (v.id() % 2 != 0))
        .toList();

        if (search == null || search.trim().length() < 3) {
            return eventSpecificList;
        }

        String query = search.trim().replaceAll("\\s+", " ").toLowerCase();
        return eventSpecificList.stream()
            .filter(v -> {
            String firstName = v.firstName().toLowerCase();
            String lastName = v.lastName().toLowerCase();
            String fullName = firstName + " " + lastName;
            String email = v.email().toLowerCase();

            boolean matchName = firstName.contains(query) || lastName.contains(query);
            boolean matchFullName = fullName.contains(query);
            boolean matchEmail = email.contains(query);

            return matchName || matchFullName || matchEmail;
        })
        .toList();
    }

}
