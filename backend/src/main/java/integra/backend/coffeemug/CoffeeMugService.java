package integra.backend.coffeemug;

import integra.backend.coffeemug.model.CoffeeMug;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class CoffeeMugService {
    private final CoffeeMugRepository repository;

    public CoffeeMugService(CoffeeMugRepository repository) {
        this.repository = repository;
    }

    public List<CoffeeMug> getAll() {
        return repository.findAll();
    }

    public Optional<CoffeeMug> getById(Long id) {
        return repository.findById(id);
    }

    public CoffeeMug create(CoffeeMug mug) {
        return repository.save(mug);
    }
}
