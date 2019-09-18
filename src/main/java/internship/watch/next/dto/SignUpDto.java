package internship.watch.next.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SignUpDto {
    private String email;
    private String password;
    private String confirmPassword;
    private String username;
}
