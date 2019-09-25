package internship.watch.next.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@NoArgsConstructor

public class MovieDto {
    private String title;
    private String trailerUrl;
    private String originalSourceUrl;
    private String coverUrl;
    private LocalDate releaseDate;
}
