package epic_energy_services.bw2.services;

import epic_energy_services.bw2.entities.Utenti;
import epic_energy_services.bw2.exception.BadRequestException;
import epic_energy_services.bw2.exception.NotFoundException;
import epic_energy_services.bw2.payloads.NewUtentiDTO;
import epic_energy_services.bw2.repositories.RuoloRepository;
import epic_energy_services.bw2.repositories.UtentiRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UtentiService {

    @Autowired
    private UtentiRepository utentiRepository;
    @Autowired
    private RuoloRepository ruoloRepository;
    @Autowired
    private PasswordEncoder bcrypt;

    public Utenti save(NewUtentiDTO payload) {
        this.utentiRepository.findByEmail(payload.email()).ifPresent(utenti -> {
            throw new BadRequestException("L'email " + utenti.getEmail() +  "è già in uso");
        });
        Utenti newUtente = new Utenti(payload.username(), payload.email(), bcrypt.encode(payload.password()), payload.firstName(), payload.lastName(), payload.avatar(), payload.ruoli());
        newUtente.setAvatar("https://ui-avatars.com/api/?name=" + payload.firstName() + "+" + payload.lastName());
        return newUtente;
    }

    public Utenti findById(Long userId) {
        return this.utentiRepository.findById(userId).orElseThrow(() -> new NotFoundException(userId));
    }

    public Utenti findByIdAndUpdate(Long userId, NewUtentiDTO payload) {

        Utenti found = this.findById(userId);


        if (!found.getEmail().equals(payload.email()))
            this.utentiRepository.findByEmail(payload.email()).ifPresent(user -> {
                throw new BadRequestException("L'email " + user.getEmail() + " è già in uso!");
            });

        found.setNome(payload.firstName());
        found.setCognome(payload.lastName());
        found.setEmail(payload.email());
        found.setPassword(payload.password());
        found.setAvatar("https://ui-avatars.com/api/?name=" + payload.firstName() + "+" + payload.lastName());

        Utenti modifiedUser = this.utentiRepository.save(found);

        log.info("L'utente con id " + found.getId() + " è stato modificato!");

        return modifiedUser;
    }

    public void findByIdAndDelete(Long userId) {
        Utenti found = this.findById(userId);
        this.utentiRepository.delete(found);
    }

    public Utenti findByEmail(String email) {
        return this.utentiRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("L'utente con l'email " + email + " non è stato trovato!"));
    }


}
