package internship.watch.next.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Table(name = "Categories")
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Category extends BaseEntity {

    @Column(nullable = false, unique = true)
    public String name;

    @OneToMany(mappedBy = "category")
    public List<MovieCategory> movieCategories = new ArrayList<>();

    public Category(String name) {
        this.name = name;
    }
}
