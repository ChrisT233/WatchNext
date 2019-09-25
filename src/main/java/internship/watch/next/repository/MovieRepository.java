package internship.watch.next.repository;

import internship.watch.next.model.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Integer> {
        Movie findByImdbId(String id);
}
