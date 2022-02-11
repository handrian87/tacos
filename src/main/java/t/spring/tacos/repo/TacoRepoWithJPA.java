package t.spring.tacos.repo;

import org.springframework.data.repository.CrudRepository;
import t.spring.tacos.model.TacoWithJPA;

public interface TacoRepoWithJPA extends CrudRepository<TacoWithJPA, Long> {

}
