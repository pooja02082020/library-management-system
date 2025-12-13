package com.example.lbms.controller;
 
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
 
import com.example.lbms.repository.UserRepository;
import com.example.lbms.security.JwtUtils;
import com.example.lbms.model.*;
 
@RestController
@RequestMapping("/api/auth")
public class AuthController {
 
    private final AuthenticationManager authManager;
    private final UserRepository userRepo;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
 
    public AuthController(
            AuthenticationManager am,
            UserRepository ur,
            BCryptPasswordEncoder pe,
            JwtUtils ju
    ) {
        this.authManager = am;
        this.userRepo = ur;
        this.passwordEncoder = pe;
        this.jwtUtils = ju;
    }
 
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest req) {
        if (userRepo.existsByUsername(req.getUsername())) {
            return ResponseEntity.badRequest().body("Username exists");
        }
 
        User u = new User();
        u.setUsername(req.getUsername());
        u.setEmail(req.getEmail());
        u.setPassword(passwordEncoder.encode(req.getPassword()));
        u.getRoles().add("ROLE_USER");
 
        userRepo.save(u);
        return ResponseEntity.ok("User registered");
    }
 
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
 
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword())
        );
 
        UserDetails ud = (UserDetails) auth.getPrincipal();
        String token = jwtUtils.generateToken(ud);
 
        return ResponseEntity.ok(new JwtResponse(token));
    }
}