package epic_energy_services.bw2.services;

import epic_energy_services.bw2.entities.Ruolo;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RuoloRepository ruoloRepository;
    @Autowired
    private PasswordEncoder bcrypt;

    //UTENTE APPENA CREATO HA RUOLO USER
    public User save(NewUserDTO payload) {
        this.userRepository.findByEmail(payload.email()).ifPresent(utenti -> {
            throw new BadRequestException("L'email " + utenti.getEmail() +  " è già in uso");
        });
        User newUtente = new User(payload.username(), payload.email(), bcrypt.encode(payload.password()), payload.firstName(), payload.lastName(), payload.avatar());
        newUtente.setAvatar("https://ui-avatars.com/api/?name=" + payload.firstName() + "+" + payload.lastName());

        Ruolo ruoloUser = ruoloRepository.findByNomeRuolo("USER")
                .orElseThrow(() -> new RuntimeException("Ruolo USER non trovato"));
        newUtente.getRuoli().add(ruoloUser);

        return this.userRepository.save(newUtente);
    }


    public User findById(Long userId) {
        return this.userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User con id " + userId + " non trovato."));
    }

    public User findByIdAndUpdate(Long userId, NewUserDTO payload) {

        User found = this.findById(userId);


        if (!found.getEmail().equals(payload.email()))
            this.userRepository.findByEmail(payload.email()).ifPresent(user -> {
                throw new BadRequestException("L'email " + user.getEmail() + " è già in uso!");
            });

        found.setUsername(payload.username());
        found.setNome(payload.firstName());
        found.setCognome(payload.lastName());
        found.setEmail(payload.email());
        found.setPassword( bcrypt.encode(payload.password()));
        found.setAvatar("https://ui-avatars.com/api/?name=" + payload.firstName() + "+" + payload.lastName());

        User modifiedUser = this.userRepository.save(found);

        log.info("L'utente con id " + found.getId() + " è stato modificato!");

        return modifiedUser;
    }

    public void findByIdAndDelete(Long userId) {
        User found = this.findById(userId);
        this.userRepository.delete(found);
    }

    public Page<User> findAll(int pageNumber, int pageSize, String sortBy) {
        if (pageSize > 50) pageSize = 50;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());
        return this.userRepository.findAll(pageable);
    }

    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("L'utente con l'email " + email + " non è stato trovato!"));
    }

    public void assegnaRuoloAUser(Long userId, String nomeRuolo) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utente non trovato"));

        Ruolo ruolo = ruoloRepository.findByNomeRuolo(nomeRuolo)
                .orElseThrow(() -> new RuntimeException("Ruolo non trovato"));


        user.getRuoli().add(ruolo);

        userRepository.save(user);
    }

}
