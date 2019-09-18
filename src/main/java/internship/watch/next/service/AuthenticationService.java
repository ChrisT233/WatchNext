package internship.watch.next.service;

import internship.watch.next.model.Role;
import internship.watch.next.model.Users;
import internship.watch.next.repository.RoleRepository;
import internship.watch.next.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

//in order for SB to be able to find the service / and to know what kind of class it is
@Service
//in order for SB to auto-initialize the class' attributes
@AllArgsConstructor
public class AuthenticationService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    private final MailService mailService;

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
        RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder().withinRange(33, 127)
                .build();
        return pwdGenerator.generate(10);
    }

    public void resetPassword(String email) {
        Users resetUser = usersRepository.findByEmail(email);
        if (resetUser != null) {
            String generatedPassword = generateNewPassword();
            String encodedPassword = passwordEncoder.encode(generatedPassword);
            resetUser.setPassword_hash(encodedPassword);
            mailService.sendEmail(email, "Password Reset",
                    "This is your new password: " + generatedPassword);
        } else {
            throw new RuntimeException("An user with this email does not exist.");
        }
    }
}
