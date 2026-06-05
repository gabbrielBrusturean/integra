package integra.backend.event;
import integra.backend.event.model.RegisteredVolunteerDto;
import integra.backend.event.model.Event;
import integra.backend.exception.ResourceNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import integra.backend.registration.RegistrationRepository; 
import integra.backend.registration.model.Registration;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private final RegistrationRepository registrationRepository;

    public EventService(EventRepository eventRepository, RegistrationRepository registrationRepository) {
        this.eventRepository = eventRepository;
        this.registrationRepository = registrationRepository;
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
        List<Registration> registrations = registrationRepository.findByEventId(eventId);

        List<RegisteredVolunteerDto> volunteers = registrations.stream()
            .map(reg -> new RegisteredVolunteerDto(
                reg.getUser().getId(),
                reg.getUser().getFirstName(),
                reg.getUser().getLastName(),
                reg.getUser().getEmail()
            ))
            .collect(Collectors.toList());

        if (search == null || search.trim().length() < 3) {
            return volunteers;
        }

        String query = search.trim().replaceAll("\\s+", " ").toLowerCase();
        return volunteers.stream()
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
