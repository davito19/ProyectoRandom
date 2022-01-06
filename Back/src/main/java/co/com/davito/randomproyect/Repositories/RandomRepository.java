package co.com.davito.randomproyect.Repositories;

import co.com.davito.randomproyect.collections.Random;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface RandomRepository extends ReactiveCrudRepository<Random, String> {
}
