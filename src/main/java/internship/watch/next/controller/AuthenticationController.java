package internship.watch.next.controller;

import internship.watch.next.dto.ResetPasswordDto;
import internship.watch.next.dto.SignUpDto;
import internship.watch.next.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<?> signUp(@RequestBody SignUpDto signUpDto) {
        authenticationService.signup(
                signUpDto.getEmail(),
                signUpDto.getPassword(),
                signUpDto.getConfirmPassword(),
                signUpDto.getUsername()
        );
        return ResponseEntity.ok().build(); //strictly for web requests
    }

    @RequestMapping(value = "/reset-password", method = RequestMethod.POST)
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordDto resetPasswordDto) {
        authenticationService.resetPassword(
                resetPasswordDto.getEmail()
        );
        return ResponseEntity.ok().build();
    }
}

