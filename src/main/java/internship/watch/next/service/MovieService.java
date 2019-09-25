package internship.watch.next.service;

import internship.watch.next.helper.Helper;
import internship.watch.next.model.Movie;
import internship.watch.next.repository.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MovieService {
    private final Helper helper;
    private final MovieRepository movieRepository;

    public Movie addMovie(String title, String trailerUrl, String originalSourceUrl, String coverUrl, LocalDate releaseDate) {
        if (helper.hasAccess()) {
            String imdbId = originalSourceUrl.substring(originalSourceUrl.lastIndexOf("/") + 1);
            Movie movie = movieRepository.findByImdbId(imdbId);
            if (movie == null) {
                movie = new Movie(title, trailerUrl, originalSourceUrl, coverUrl, releaseDate, imdbId);
                movieRepository.save(movie);
            }
            return movie;
        } else {
            throw new RuntimeException("You do not have access.");
        }
    }

    public Optional<Movie> getMovieById(Integer id) {
        return movieRepository.findById(id);
    }

    public boolean deleteMovie(Integer id) {
        if (helper.hasAccess()) {
            Optional<Movie> movie = movieRepository.findById(id);
            if (movie.isPresent()) {
                movieRepository.delete(movie.get());
                return true;
            }
            return false;
        } else {
            throw new RuntimeException("You do not have access.");
        }
    }
}
