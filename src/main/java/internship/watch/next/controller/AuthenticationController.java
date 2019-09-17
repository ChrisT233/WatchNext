package internship.watch.next.controller;

import internship.watch.next.dto.ResetPasswordDto;
import internship.watch.next.dto.UserDto;
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

    //todo
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<?> signUp(@RequestBody UserDto userDto) {
        return ResponseEntity.ok().build(); //strictly for web requests
    }

    //todo
    @RequestMapping(value = "/reset-password", method = RequestMethod.POST)
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordDto resetPasswordDto) {
        return ResponseEntity.ok().build();
    }
}

