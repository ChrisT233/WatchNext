package internship.watch.next.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserDto {
    private String email;
    private String password;
    private String confirmPassword;
    private String username;
}
