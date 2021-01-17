package ssau.esa.repo;

import org.springframework.data.repository.CrudRepository;
import ssau.esa.model.Email;

public interface EmailRepo extends CrudRepository<Email, Integer> {
}
