package epic_energy_services.bw2.runners;

import epic_energy_services.bw2.entities.Ruolo;
import epic_energy_services.bw2.entities.User;
import epic_energy_services.bw2.repositories.RuoloRepository;
import epic_energy_services.bw2.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminRunner implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RuoloRepository ruoloRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

//        Ruolo adminRole = ruoloRepository.findByNomeRuolo("ADMIN")
//                .orElseGet(() -> {
//                    Ruolo ruolo = new Ruolo();
//                    ruolo.setNomeRuolo("ADMIN");
//                    return ruoloRepository.save(ruolo);
//                });
//
//
//        boolean adminExists = userRepository.findAll().stream()
//                .anyMatch(user -> user.getRuoli().stream()
//                        .anyMatch(r -> r.getNomeRuolo().equals("ADMIN")));
//
//        if (!adminExists) {
//            User admin = new User();
//            admin.setUsername("admin");
//            admin.setEmail("admin@example.com");
//            admin.setPassword(passwordEncoder.encode("adminpassword"));
//            admin.setNome("Sergio");
//            admin.setCognome("Maselli");
//            admin.getRuoli().add(adminRole);
//
//            userRepository.save(admin);
//
//            System.out.println("Admin di default creato: username=admin, password=adminpassword");
//        }
    }
}

