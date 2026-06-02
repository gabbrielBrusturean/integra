package integra.backend.user;

import integra.backend.user.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> getAll() {
        return repository.findAll();
    }

    public Optional<User> getById(Long id) {
        return repository.findById(id);
    }

    public User create(User user) {
        ensureEmailIsAvailable(user.getEmail());
        return repository.save(user);
    }

    public Optional<User> update(Long id, User updatedUser) {
        return repository.findById(id).map(existing -> {
            if (!existing.getEmail().equals(updatedUser.getEmail())) {
                ensureEmailIsAvailable(updatedUser.getEmail());
            }
            existing.setFirstName(updatedUser.getFirstName());
            existing.setLastName(updatedUser.getLastName());
            existing.setEmail(updatedUser.getEmail());
            return repository.save(existing);
        });
    }

    public boolean delete(Long id) {
        if (!repository.existsById(id)) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }

    private void ensureEmailIsAvailable(String email) {
        if (repository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email already registered");
        }
    }
}
