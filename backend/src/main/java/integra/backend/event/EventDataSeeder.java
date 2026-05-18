package integra.backend.event;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import integra.backend.event.model.Event;

@Component
@Profile("dev")
public class EventDataSeeder implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(EventDataSeeder.class);

    private EventRepository eventRepository;

    EventDataSeeder(@Autowired EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        List<Event> sampleEvents = createSampleEvents();

        eventRepository.saveAll(sampleEvents);
        logger.info("Successfully seeded {} sample events", sampleEvents.size());
    }

    private List<Event> createSampleEvents() {
        return java.util.List.of(new Event(null, // ID will be auto-generated
                "Java Developer Conference 2026",
                "Annual conference for Java developers. Learn about the latest frameworks,"
                        + " best practices, and industry trends. Sessions include Spring Boot 3.3, Virtual Threads, and Spring AI.",
                "Berlin Convention Center, Berlin", LocalDateTime.of(2026, 6, 15, 9, 0),
                LocalDateTime.of(2026, 6, 16, 17, 0), null, // createdAt will be
                                                            // set by
                                                            // @PrePersist
                500),
                new Event(null, "Frontend Masterclass - Angular Workshop",
                        "Hands-on workshop for modern Angular development. Topics include standalone"
                                + "components, signals, change detection, and Angular 18 features.",
                        "Tech Hub Munich, Munich", LocalDateTime.of(2026, 6, 22, 10, 0),
                        LocalDateTime.of(2026, 6, 22, 16, 0), null, 50),
                new Event(null, "Cloud Architecture & DevOps Summit",
                        "Deep dive into cloud-native architectures, Kubernetes, Docker,"
                                + " and CI/CD pipelines. Real-world case studies from leading tech companies.",
                        "Innovation Park Hamburg, Hamburg", LocalDateTime.of(2026, 7, 5, 8, 30),
                        LocalDateTime.of(2026, 7, 6, 18, 0), null, 300),
                new Event(null, "Webinar: Spring Security Best Practices",
                        "Online webinar covering OAuth2, JWT, and securing REST APIs with Spring Security 6. Open to all experience levels.",
                        "Online (Zoom)", LocalDateTime.of(2026, 5, 28, 15, 0), LocalDateTime.of(2026, 5, 28, 16, 30),
                        null, 1000));
    }
}
