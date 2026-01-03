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

/**
 * Authentication and Authorization Controller.
 * Handles user signup, login, JWT token generation,
 * and role management (USER / ADMIN).
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    // Authentication manager used to authenticate login requests
    private final AuthenticationManager authManager;

    // Repository for User entity database operations
    private final UserRepository userRepo;

    // Password encoder for hashing passwords securely
    private final BCryptPasswordEncoder passwordEncoder;

    // Utility class for generating JWT tokens
    private final JwtUtils jwtUtils;

    // Constructor-based dependency injection
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

    /**
     * Register a new user (Signup)
     * URL: POST /api/auth/signup
     *
     * @param req SignupRequest containing username, email, and password
     * @return success or error message
     */
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest req) {

        // Check if username already exists
        if (userRepo.existsByUsername(req.getUsername())) {
            return ResponseEntity.badRequest().body("Username exists");
        }

        // Create new User entity
        User u = new User();
        u.setUsername(req.getUsername());
        u.setEmail(req.getEmail());

        // Encrypt password before saving
        u.setPassword(passwordEncoder.encode(req.getPassword()));

        // Assign default role (USER)
        u.setRole(1); // 1 = USER

        // Save user to database
        userRepo.save(u);

        return ResponseEntity.ok("User registered");
    }

    /**
     * Authenticate user and generate JWT token (Login)
     * URL: POST /api/auth/login
     *
     * @param req LoginRequest containing username and password
     * @return JWT token if authentication succeeds
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {

        // Authenticate user credentials
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        req.getUsername(),
                        req.getPassword()
                )
        );

        // Retrieve authenticated user details
        UserDetails ud = (UserDetails) auth.getPrincipal();

        // Generate JWT token
        String token = jwtUtils.generateToken(ud);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    /**
     * Promote a user to ADMIN role
     * URL: PUT /api/auth/make-admin/{id}
     *
     * @param id User ID
     * @return confirmation message
     */
    @PutMapping("/make-admin/{id}")
    public String makeAdmin(@PathVariable Integer id) {

        // Fetch user or throw exception if not found
        User user = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Update role to ADMIN
        user.setRole(0); // 0 = ADMIN

        // Save updated user
        userRepo.save(user);

        return "User is now ADMIN";
    }
}
