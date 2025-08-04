package epic_energy_services.bw2.services;

import epic_energy_services.bw2.entities.User;
import epic_energy_services.bw2.exception.BadRequestException;
import epic_energy_services.bw2.exception.NotFoundException;
import epic_energy_services.bw2.payloads.NewUserDTO;
import epic_energy_services.bw2.repositories.RuoloRepository;
import epic_energy_services.bw2.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RuoloRepository ruoloRepository;
    @Autowired
    private PasswordEncoder bcrypt;

    public User save(NewUserDTO payload) {
        this.userRepository.findByEmail(payload.email()).ifPresent(utenti -> {
            throw new BadRequestException("L'email " + utenti.getEmail() +  "è già in uso");
        });
        User newUtente = new User(payload.username(), payload.email(), bcrypt.encode(payload.password()), payload.firstName(), payload.lastName(), payload.avatar(), payload.ruoli());
        newUtente.setAvatar("https://ui-avatars.com/api/?name=" + payload.firstName() + "+" + payload.lastName());
        return newUtente;
    }

    public User findById(Long userId) {
        return this.userRepository.findById(userId).orElseThrow(() -> new NotFoundException(userId));
    }

    public User findByIdAndUpdate(Long userId, NewUserDTO payload) {

        User found = this.findById(userId);


        if (!found.getEmail().equals(payload.email()))
            this.userRepository.findByEmail(payload.email()).ifPresent(user -> {
                throw new BadRequestException("L'email " + user.getEmail() + " è già in uso!");
            });

        found.setNome(payload.firstName());
        found.setCognome(payload.lastName());
        found.setEmail(payload.email());
        found.setPassword(payload.password());
        found.setAvatar("https://ui-avatars.com/api/?name=" + payload.firstName() + "+" + payload.lastName());

        User modifiedUser = this.userRepository.save(found);

        log.info("L'utente con id " + found.getId() + " è stato modificato!");

        return modifiedUser;
    }

    public void findByIdAndDelete(Long userId) {
        User found = this.findById(userId);
        this.userRepository.delete(found);
    }

    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("L'utente con l'email " + email + " non è stato trovato!"));
    }


}
