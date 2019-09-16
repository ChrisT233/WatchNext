package internship.watch.next.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "Users")
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Users extends BaseEntity {

    @Column
    public String name;

    @Column
    public String email;

    @Column
    public String password_hash;

    @ManyToOne(targetEntity = Role.class)
    @JoinColumn(name = "role", referencedColumnName = "id")
    public Role role;

    public Users(String name, String email, String password_hash, Role role) {
        this.name = name;
        this.email = email;
        this.password_hash = password_hash;
        this.role = role;
    }
}
