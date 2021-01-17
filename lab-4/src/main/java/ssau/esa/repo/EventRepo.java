package ssau.esa.repo;

import org.springframework.data.repository.CrudRepository;
import ssau.esa.model.Event;

public interface EventRepo extends CrudRepository<Event, Integer> {
}
