package epic_energy_services.bw2.controllers;

import epic_energy_services.bw2.entities.User;
import epic_energy_services.bw2.payloads.NewUserDTO;
import epic_energy_services.bw2.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<User> findAll(@RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "10") int size,
                              @RequestParam(defaultValue = "id") String sortBy
    ) {
        return this.userService.findAll(page, size, sortBy);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public User getUserById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public User createUser(@RequestBody @Valid NewUserDTO newUserDTO) {
        return userService.save(newUserDTO);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public User updateUser(@PathVariable Long id, @RequestBody @Valid NewUserDTO newUserDTO) {
        return userService.findByIdAndUpdate(id, newUserDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(@PathVariable Long id) {
        userService.findByIdAndDelete(id);
    }

    // ENPOINT ME

    @GetMapping("/me")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public User getProfile(@AuthenticationPrincipal User currentUser) {
        return currentUser;
    }

    @PutMapping("/me")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public User updateOwnProfile(@AuthenticationPrincipal User currentUser, @RequestBody @Valid NewUserDTO newUserDTO) {
        return userService.findByIdAndUpdate(currentUser.getId(), newUserDTO);
    }


    @DeleteMapping("/me")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public void deleteMe(@AuthenticationPrincipal User currentUser) {
        userService.findByIdAndDelete(currentUser.getId());
    }


}
