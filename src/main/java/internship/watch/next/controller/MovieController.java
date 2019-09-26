package internship.watch.next.controller;


import internship.watch.next.dto.MovieDto;
import internship.watch.next.model.Movie;
import internship.watch.next.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("/api")
public class MovieController {
    private final MovieService movieService;

    @RequestMapping(value = "/movie", method = RequestMethod.POST)
    public ResponseEntity<MovieDto> addMovie(@RequestBody MovieDto movieDto) {
        Movie movie = movieService.addMovie(
                movieDto.getTitle(),
                movieDto.getTrailerUrl(),
                movieDto.getOriginalSourceUrl(),
                movieDto.getCoverUrl(),
                movieDto.getReleaseDate());
        return ResponseEntity.ok().body(new MovieDto(
                movie.getTitle(),
                movie.getTrailerUrl(),
                movie.getOriginalSourceUrl(),
                movie.getCoverUrl(),
                movie.getReleaseDate()));
    }

    @RequestMapping(value = "/movie/{id}", method = RequestMethod.GET)
    public ResponseEntity<MovieDto> getMovieById(@PathVariable Integer id) {
        Optional<Movie> movie = movieService.getMovieById(id);
        if (movie.isPresent()) {
            return ResponseEntity.ok().body(new MovieDto(
                    movie.get().getTitle(),
                    movie.get().getTrailerUrl(),
                    movie.get().getOriginalSourceUrl(),
                    movie.get().getCoverUrl(),
                    movie.get().getReleaseDate()));
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/movie/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteMovie(@PathVariable Integer id) {
        if (movieService.deleteMovie(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/movie/query", method = RequestMethod.GET)
    public ResponseEntity<List<MovieDto>> getMoviesByTimestamp(@RequestParam String from, @RequestParam String to,
                                                               @RequestParam Integer limit, @RequestParam Integer skip) {
        LocalDate fromDate = LocalDate.parse(from);
        LocalDate toDate = LocalDate.parse(to);

        List<Movie> movieList = movieService.getMoviesByTimestamp(fromDate, toDate, limit, skip);
        List<MovieDto> movieDtoList = movieList
                .stream()
                .map(movie -> new MovieDto(
                        movie.getTitle(),
                        movie.getTrailerUrl(),
                        movie.getOriginalSourceUrl(),
                        movie.getCoverUrl(),
                        movie.getReleaseDate()))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(movieDtoList);
    }
}

