package internship.watch.next.repository;

import internship.watch.next.model.Movie;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Integer> {
        Movie findByImdbId(String id);
        List<Movie> findByReleaseDateBetween(LocalDate from, LocalDate to, Pageable pageable);
}
