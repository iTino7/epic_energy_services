package epic_energy_services.bw2.services;

import epic_energy_services.bw2.entities.User;
import epic_energy_services.bw2.exception.UnauthorizedException;
import epic_energy_services.bw2.payloads.NewUserDTO;
import epic_energy_services.bw2.tools.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTTools jwtTools;

    public String generateToken(NewUserDTO payload) {
        User user = userService.findByEmail(payload.email());
        if (passwordEncoder.matches(payload.password(), user.getPassword())) {
            return jwtTools.createToken(user);
        } else {
            throw new UnauthorizedException("Credenziali sbagliate");
        }
    }
}
