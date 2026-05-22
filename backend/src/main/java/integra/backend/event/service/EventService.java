package integra.backend.event.service;
import integra.backend.event.model.RegisteredVolunteerDto;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EventService implements IEventService {

    @Override
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
