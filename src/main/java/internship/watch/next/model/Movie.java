package internship.watch.next.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Table(name = "Movies")
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Movie extends BaseEntity {

    @Column(nullable = false)
    public String title;

    @Column(nullable = false)
    @URL // validation of the URL
    public String trailerUrl;

    @Column(nullable = false)
    @URL
    public String originalSourceUrl;

    @Column(nullable = false)
    @URL
    public String coverUrl;

    @Column(nullable = false)
    public String imdbId;

    @Column
    public Float imdbScore;

    @Column
    public String description;

    @Column(nullable = false)
    public LocalDate releaseDate;

    @OneToMany(mappedBy = "movie")
    public List<MovieCategory> movieCategories = new ArrayList<>();

    public Movie (String title, String trailerUrl, String originalSourceUrl, String coverUrl, LocalDate releaseDate, String imdbId) {
        this.title = title;
        this.trailerUrl = trailerUrl;
        this.originalSourceUrl = originalSourceUrl;
        this.coverUrl = coverUrl;
        this.releaseDate = releaseDate;
        this.imdbId = imdbId;
    }
}
