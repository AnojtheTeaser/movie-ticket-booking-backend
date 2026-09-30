package lk.ijse.cmjd._115.movie_ticket_booking.controller;

import lk.ijse.cmjd._115.movie_ticket_booking.dao.UserDAO;
import lk.ijse.cmjd._115.movie_ticket_booking.entity.UserEntity;
import lk.ijse.cmjd._115.movie_ticket_booking.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserEntity user) {
        if (userDAO.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body("Error: Email is already in use!");
        }


        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.save(user);

        return ResponseEntity.ok("User registered successfully!");
    }


    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody UserEntity loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        String token = jwtUtils.generateToken(loginRequest.getEmail());

        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return ResponseEntity.ok(response);
    }
}