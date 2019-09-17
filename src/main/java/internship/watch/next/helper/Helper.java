package internship.watch.next.helper;

import internship.watch.next.model.Role;
import internship.watch.next.model.Users;
import internship.watch.next.repository.RoleRepository;
import internship.watch.next.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

//Helps Spring Boot to find the class
@Component
@AllArgsConstructor
public class Helper {

    private final RoleRepository roleRepository;
    private final UsersRepository usersRepository;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @PostConstruct
    public void createRoles() {
        if (roleRepository.count() == 0) {
            Role adminRole = new Role("Admin", true);
            Role userRole = new Role("User", false);
            roleRepository.save(adminRole);
            roleRepository.save(userRole);
        }
        createDefaultUser();
    }

    public void createDefaultUser() {
        if (usersRepository.count() == 0) {
            Role defaultUserRole = roleRepository.findByName("Admin");
            Users defaultUser = new Users("Cristi", "cristi@cristi.ro",
                    bCryptPasswordEncoder().encode("parolaCristi"), defaultUserRole);
            usersRepository.save(defaultUser);
        }
    }
}
