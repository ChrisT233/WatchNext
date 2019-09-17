package internship.watch.next.service;

import internship.watch.next.model.Role;
import internship.watch.next.model.Users;
import internship.watch.next.repository.RoleRepository;
import internship.watch.next.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.Random;

//in order for SB to be able to find the service / and to know what kind of class it is
@Service
//in order for SB to auto-initialize the class' attributes
@AllArgsConstructor
public class AuthenticationService {

    private final UsersRepository usersRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public void signup(String email, String password, String confirmPassword, String username) {
        if (usersRepository.findByEmail(email) == null) {
            if (password.equals(confirmPassword)) {
                String encodedPassword = passwordEncoder.encode(password);
                Role userRole = roleRepository.findByName("User");
                Users createdUser = new Users(username, email, encodedPassword, userRole);
                usersRepository.save(createdUser);
            } else {
                throw new RuntimeException("Passwords do not match.");
            }
        } else {
            throw new RuntimeException("Email already in use.");
        }
    }

    private String generateNewPassword() {
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedPassword = new String(array, Charset.forName("UTF-8"));
        return passwordEncoder.encode(generatedPassword);
    }

    public void resetPassword(String email) {
        Users resetUser = usersRepository.findByEmail(email);
        if (resetUser != null) {
            String generatedPassword = generateNewPassword();
            resetUser.setPassword_hash(generatedPassword);
        } else {
            throw new RuntimeException("An user with this email does not exist.");
        }
    }
}
