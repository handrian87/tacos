package t.spring.tacos.repo;

import t.spring.tacos.model.Taco;

public interface TacoRepository {
    Taco save(Taco design);
}
