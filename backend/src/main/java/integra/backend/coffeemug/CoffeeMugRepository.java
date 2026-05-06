package integra.backend.coffeemug;

import integra.backend.coffeemug.model.CoffeeMug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeeMugRepository extends JpaRepository<CoffeeMug, Long> {
}
