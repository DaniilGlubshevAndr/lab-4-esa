package ssau.esa.repo;

import org.springframework.data.repository.CrudRepository;
import ssau.esa.model.Person;

public interface PersonRepo extends CrudRepository<Person, Integer> {
}
