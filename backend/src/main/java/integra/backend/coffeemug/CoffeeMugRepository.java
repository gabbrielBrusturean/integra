package integra.backend.coffeemug;

import integra.backend.coffeemug.model.CoffeeMug;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeMugRepository extends JpaRepository<CoffeeMug, Long> {
}
