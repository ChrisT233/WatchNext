package internship.watch.next.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Table(name = "Users")
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Users extends BaseEntity implements UserDetails {

    @Column
    public String username;

    @Column
    public String email;

    @Column
    public String password_hash;

    @ManyToOne(targetEntity = Role.class)
    @JoinColumn(name = "role", referencedColumnName = "id")
    public Role role;

    public Users(String username, String email, String password_hash, Role role) {
        this.username = username;
        this.email = email;
        this.password_hash = password_hash;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("user"));
    }

    @Override
    public String getPassword() {
        return password_hash;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
