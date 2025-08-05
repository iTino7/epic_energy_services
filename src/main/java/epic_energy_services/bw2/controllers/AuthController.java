package epic_energy_services.bw2.controllers;

import epic_energy_services.bw2.entities.User;
import epic_energy_services.bw2.exception.ValidationException;
import epic_energy_services.bw2.payloads.LoginRespDTO;
import epic_energy_services.bw2.payloads.NewUserDTO;
import epic_energy_services.bw2.payloads.UserLoginDTO;
import epic_energy_services.bw2.payloads.UserRespDTO;
import epic_energy_services.bw2.services.AuthService;
import epic_energy_services.bw2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserRespDTO register(@RequestBody @Validated NewUserDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new ValidationException(validation.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList());
        } else {
        User newUser = this.userService.save(body);
        return new UserRespDTO(newUser.getId());
        }
    }

    @PostMapping("/login")
    public LoginRespDTO login(@RequestBody UserLoginDTO payload) {
        return new LoginRespDTO(authService.generateToken(payload));
    }

}
