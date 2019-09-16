package internship.watch.next.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "MoviesCategories")
@Entity
@NoArgsConstructor
@Getter
@Setter
public class MovieCategory extends BaseEntity {

    @ManyToOne(targetEntity = Category.class)
    @JoinColumn(name = "category", referencedColumnName = "id")
    public Category category;

    @ManyToOne(targetEntity = Movie.class)
    @JoinColumn(name = "movie", referencedColumnName = "id")
    public Movie movie;
}
