package integra.backend.coffeemug.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "coffee_mugs")
public class CoffeeMug {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String color;
    private int capacityMl;
    private boolean clean;

    public CoffeeMug(Long id, String color, int capacityMl, boolean clean) {
        this.id = id;
        this.color = color;
        this.capacityMl = capacityMl;
        this.clean = clean;
    }

    public CoffeeMug() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public int getCapacityMl() { return capacityMl; }
    public void setCapacityMl(int capacityMl) { this.capacityMl = capacityMl; }
    public boolean isClean() { return clean; }
    public void setClean(boolean clean) { this.clean = clean; }
}
