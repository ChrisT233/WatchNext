package internship.watch.next.helper;

import internship.watch.next.model.Role;
import internship.watch.next.model.Users;
import internship.watch.next.repository.RoleRepository;
import internship.watch.next.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

//Helps Spring Boot to find the class
@Component
@AllArgsConstructor
public class Helper {

    private final RoleRepository roleRepository;
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;


    @PostConstruct //runs the function on app start
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
                    passwordEncoder.encode("parolaCristi"), defaultUserRole);
            usersRepository.save(defaultUser);
        }
    }

    public boolean hasAccess() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users user = usersRepository.findByUsername(principal.toString());
        return user.getRole().getName().equals("Admin");
    }
}
