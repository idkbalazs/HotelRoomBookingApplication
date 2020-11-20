package hu.elte.RoomBookingApp.Controllers;


import java.util.Optional;

import hu.elte.RoomBookingApp.Entities.User;
import hu.elte.RoomBookingApp.Repositories.UserRepository;
import hu.elte.RoomBookingApp.security.AuthenticatedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticatedUser authenticatedUser;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        Optional<User> oUser = userRepository.findByUsername(user.getUsername());
        if (oUser.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(User.Role.ROLE_USER);
        return ResponseEntity.ok(userRepository.save(user));
    }

    @PostMapping("/login")
    public ResponseEntity login() {
        return ResponseEntity.ok(authenticatedUser.getUser());
    }

    @GetMapping("")
    public ResponseEntity<Iterable<User>> getAll() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}