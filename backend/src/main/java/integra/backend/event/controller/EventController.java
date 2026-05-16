package integra.backend.event.controller;
import integra.backend.event.service.IEventService;
import integra.backend.event.model.RegisteredVolunteerDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/events")
@CrossOrigin(origins = "http://localhost:4200") 
public class EventController {

    private final IEventService eventService;

    public EventController(IEventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/{eventId}/volunteers")
    public ResponseEntity<List<RegisteredVolunteerDto>> getVolunteers(
            @PathVariable("eventId") Long eventId,
            @RequestParam(value = "search", required = false) String search) {
        
        List<RegisteredVolunteerDto> volunteers = eventService.getRegisteredVolunteers(eventId, search);
        return ResponseEntity.ok(volunteers);
    }
}
