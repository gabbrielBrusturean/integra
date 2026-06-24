package integra.backend.registration;

import integra.backend.registration.model.RegistrationDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/registrations")
public class RegistrationController {
    private final RegistrationService service;
    private final RegistrationMapper mapper;

    public RegistrationController(RegistrationService service, RegistrationMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<RegistrationDto> getAll() {
        return service.getAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RegistrationDto> getById(@PathVariable Long id) {
        return service.getById(id).map(r -> ResponseEntity.ok(mapper.toDto(r)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAnyRole('VOLUNTEER', 'ADMIN')")
    public List<RegistrationDto> getByUserId(@PathVariable Long userId) {
        return service.getByUserId(userId).stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @GetMapping("/event/{eventId}")
    @PreAuthorize("hasAnyRole('VOLUNTEER', 'CAMPAIGN_MANAGER', 'ADMIN')")
    public List<RegistrationDto> getByEventId(@PathVariable Long eventId) {
        return service.getByEventId(eventId).stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @GetMapping("/event/{eventId}/user/{userId}")
    @PreAuthorize("hasAnyRole('VOLUNTEER', 'ADMIN')")
    public ResponseEntity<RegistrationDto> getByEventAndUser(@PathVariable Long eventId, @PathVariable Long userId) {
        return service.getByEventAndUser(eventId, userId).map(r -> ResponseEntity.ok(mapper.toDto(r)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST
    @PostMapping
    @PreAuthorize("hasAnyRole('VOLUNTEER', 'ADMIN')")
    public ResponseEntity<RegistrationDto> create(@RequestBody RegistrationDto dto) {
        return ResponseEntity.status(201)
                .body(mapper.toDto(service.create(dto.getUserId(), dto.getEventId(), mapper.toEntity(dto))));
    }

    // PUT
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('VOLUNTEER', 'ADMIN')")
    public ResponseEntity<RegistrationDto> updateStatus(@PathVariable Long id, @RequestBody RegistrationDto dto) {
        return service.updateStatus(id, dto.getStatus()).map(r -> ResponseEntity.ok(mapper.toDto(r)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DEL
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('VOLUNTEER', 'ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return service.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
