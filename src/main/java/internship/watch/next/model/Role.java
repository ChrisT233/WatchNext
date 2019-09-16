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

@Table(name = "Role")
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Role extends BaseEntity {

    @Column
    public String name;

    @Column
    public Boolean isAdmin;

    @OneToMany(mappedBy = "role")
    public List<Users> users = new ArrayList<>();

    public Role(String name, Boolean isAdmin) {
        this.name = name;
        this.isAdmin = isAdmin;
    }
}
