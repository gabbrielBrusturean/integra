package integra.backend.reminder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import integra.backend.event.EventRepository;
import integra.backend.event.model.Event;
import integra.backend.exception.ResourceNotFoundException;
import integra.backend.registration.RegistrationRepository;
import integra.backend.registration.model.Registration;
import integra.backend.user.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EventReminderService {
    private static final Logger log = LoggerFactory.getLogger(EventReminderService.class);

    private final EventRepository eventRepository;
    private final RegistrationRepository registrationRepository;
    private final ReminderEmailService emailService;

    public EventReminderService(EventRepository eventRepository, RegistrationRepository registrationRepository,
            ReminderEmailService emailService) {
        this.eventRepository = eventRepository;
        this.registrationRepository = registrationRepository;
        this.emailService = emailService;
    }

    public int sendScheduledReminders() {
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        LocalDateTime from = tomorrow.atStartOfDay();
        LocalDateTime to = tomorrow.plusDays(1).atStartOfDay();

        List<Event> events =
                eventRepository.findByStartAtGreaterThanEqualAndStartAtLessThanAndReminderSentFalse(from, to);
        int totalSent = 0;

        for (Event event : events) {
            ReminderDispatchResult result = sendRemindersForEvent(event.getId());
            totalSent += result.sentCount();
        }

        log.info("Scheduled reminder run finished. events={}, sent={}", events.size(), totalSent);
        return totalSent;
    }

    public ReminderDispatchResult sendRemindersForEvent(Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event with id " + eventId + " was not found"));

        if (Boolean.TRUE.equals(event.getReminderSent())) {
            return new ReminderDispatchResult(event.getId(), event.getTitle(), 0, 0, 0, true, false);
        }

        List<Registration> registrations = registrationRepository.findByEventId(eventId);
        Map<String, User> uniqueRecipients = new LinkedHashMap<>();

        for (Registration registration : registrations) {
            User user = registration.getUser();
            if (user != null && user.getEmail() != null && !user.getEmail().isBlank()) {
                uniqueRecipients.putIfAbsent(user.getEmail().toLowerCase(), user);
            }
        }

        if (uniqueRecipients.isEmpty()) {
            log.info("No registered volunteers found for event {} ({})", event.getId(), event.getTitle());
            return new ReminderDispatchResult(event.getId(), event.getTitle(), 0, 0, 0, false, true);
        }

        int sent = 0;
        int failed = 0;
        for (User recipient : uniqueRecipients.values()) {
            try {
                emailService.sendReminder(recipient, event);
                sent++;
            } catch (Exception exception) {
                failed++;
                log.warn("Failed to send reminder for event {} to {}", event.getId(), recipient.getEmail(), exception);
            }
        }

        event.setReminderSent(true);
        eventRepository.save(event);

        log.info("Reminder emails processed for event {}: recipients={}, sent={}, failed={}", event.getId(),
                uniqueRecipients.size(), sent, failed);

        return new ReminderDispatchResult(event.getId(), event.getTitle(), uniqueRecipients.size(), sent, failed, false,
                false);
    }
}
