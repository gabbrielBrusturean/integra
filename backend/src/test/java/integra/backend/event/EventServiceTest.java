package integra.backend.event;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import integra.backend.exception.DuplicateResourceException;
import integra.backend.exception.ResourceNotFoundException;
import integra.backend.registration.RegistrationService;
import integra.backend.registration.model.Registration;
import integra.backend.user.UserRepository;

class EventServiceTest {

    @Mock
    private EventRepository eventRepository;

    @Mock
    private RegistrationService registrationService;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private EventService eventService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerVolunteer_Success() {
        Long eventId = 1L;
        Long volunteerId = 2L;

        when(registrationService.create(eq(volunteerId), eq(eventId), any(Registration.class)))
                .thenReturn(new Registration());

        eventService.registerVolunteer(eventId, volunteerId);

        verify(registrationService, times(1)).create(eq(volunteerId), eq(eventId), any(Registration.class));
    }

    @Test
    void registerVolunteer_EventNotFound() {
        Long eventId = 1L;
        Long volunteerId = 2L;

        when(registrationService.create(eq(volunteerId), eq(eventId), any(Registration.class)))
                .thenThrow(new ResourceNotFoundException("Event with id " + eventId + " was not found"));

        assertThrows(ResourceNotFoundException.class, () -> eventService.registerVolunteer(eventId, volunteerId));
    }

    @Test
    void registerVolunteer_UserNotFound() {
        Long eventId = 1L;
        Long volunteerId = 2L;

        when(registrationService.create(eq(volunteerId), eq(eventId), any(Registration.class)))
                .thenThrow(new ResourceNotFoundException("User with id " + volunteerId + " was not found"));

        assertThrows(ResourceNotFoundException.class, () -> eventService.registerVolunteer(eventId, volunteerId));
    }

    @Test
    void registerVolunteer_DuplicateRegistration() {
        Long eventId = 1L;
        Long volunteerId = 2L;

        when(registrationService.create(eq(volunteerId), eq(eventId), any(Registration.class)))
                .thenThrow(new DuplicateResourceException("Volunteer is already registered for this event"));

        assertThrows(DuplicateResourceException.class, () -> eventService.registerVolunteer(eventId, volunteerId));
    }
    
    @Test
    void registerVolunteer_NullIds() {
        when(registrationService.create(eq(null), eq(1L), any(Registration.class)))
                .thenThrow(new IllegalArgumentException("User ID and Event ID must not be null"));
                
        assertThrows(IllegalArgumentException.class, () -> eventService.registerVolunteer(1L, null));
    }
}
