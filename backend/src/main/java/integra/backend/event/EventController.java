package integra.backend.event;
import integra.backend.event.model.RegisteredVolunteerDto;
import integra.backend.event.model.EventResponseDto;
import integra.backend.event.model.EventRequestDto;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events")
@CrossOrigin(origins = "http://localhost:4200")
public class EventController {
    private final EventService service;
    private final EventMapper mapper;

    public EventController(EventService service, EventMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public List<EventResponseDto> getAll() {
        return service.getAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public EventResponseDto getById(@PathVariable("id") Long id) {
        return mapper.toDto(service.getById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventResponseDto create(@Valid @RequestBody EventRequestDto dto) {
        return mapper.toDto(service.create(mapper.toEntity(dto)));
    }

    @PutMapping("/{id}")
    public EventResponseDto update(@PathVariable("id") Long id, @Valid @RequestBody EventRequestDto dto) {
        return mapper.toDto(service.update(id, mapper.toEntity(dto)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        service.deleteById(id);
    }

    @GetMapping("/{eventId}/volunteers")
    public ResponseEntity<List<RegisteredVolunteerDto>> getVolunteers(
            @PathVariable("eventId") Long eventId,
            @RequestParam(value = "search", required = false) String search) {
        
        List<RegisteredVolunteerDto> volunteers = service.getRegisteredVolunteers(eventId, search);
        return ResponseEntity.ok(volunteers);
    }
}
