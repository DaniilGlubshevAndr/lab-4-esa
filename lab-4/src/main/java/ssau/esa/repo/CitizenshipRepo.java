package ssau.esa.repo;

import org.springframework.data.repository.CrudRepository;
import ssau.esa.model.Citizenship;

public interface CitizenshipRepo extends CrudRepository<Citizenship, Integer> {
}

