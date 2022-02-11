package t.spring.tacos.repo;

import org.springframework.data.repository.CrudRepository;
import t.spring.tacos.model.IngredientWithJPA;

public interface IngredientRepoWithJPA extends CrudRepository<IngredientWithJPA, String> {
}
