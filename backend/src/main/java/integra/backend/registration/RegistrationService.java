package integra.backend.registration;

import integra.backend.registration.model.Registration;
import integra.backend.user.UserRepository;
import integra.backend.event.EventRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RegistrationService {
    private final RegistrationRepository repository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    public RegistrationService(RegistrationRepository repository, UserRepository userRepository, EventRepository eventRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    public List<Registration> getAll() { 
        return repository.findAll(); 
    }

    public Optional<Registration> getById(Long id) { 
        return repository.findById(id); 
    }

    public List<Registration> getByUserId(Long userId) { 
        return repository.findByUserId(userId); 
    }

    public List<Registration> getByEventId(Long eventId) { 
        return repository.findByEventId(eventId); 
    }

    public Optional<Registration> getByEventAndUser(Long eventId, Long userId) { 
        return repository.findByEventIdAndUserId(eventId, userId); 
    }

    //POST
    public Registration create(Long userId, Long eventId, Registration registration) {
        registration.setUser(userRepository.findById(userId).orElseThrow());
        registration.setEvent(eventRepository.findById(eventId).orElseThrow());
        return repository.save(registration);
    }

    //PUT
    public Optional<Registration> updateStatus(Long id, String newStatus) {
        return repository.findById(id).map(registration -> {
            registration.setStatus(newStatus);
            return repository.save(registration);
        });
    }

    //DEL
    public boolean delete(Long id) {
        if (!repository.existsById(id)) return false;
        repository.deleteById(id);
        return true;
    }
}