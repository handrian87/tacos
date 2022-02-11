package t.spring.tacos.repo;

import org.springframework.data.repository.CrudRepository;
import t.spring.tacos.model.OrderWithJPA;

public interface OrderRepoWithJPA extends CrudRepository<OrderWithJPA, Long> {
}
