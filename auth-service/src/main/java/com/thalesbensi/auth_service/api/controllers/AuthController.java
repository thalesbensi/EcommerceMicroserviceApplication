package com.thalesbensi.auth_service.api.controllers;

import com.thalesbensi.auth_service.api.controllers.dtos.AuthenticationDTO;
import com.thalesbensi.auth_service.api.controllers.dtos.RegisterDTO;
import com.thalesbensi.auth_service.domain.models.user.UserModel;
import com.thalesbensi.auth_service.domain.repositories.UserRepository;
import com.thalesbensi.auth_service.domain.services.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("auth")
public class AuthController {

     private AuthenticationManager authenticationManager;
     private UserRepository userRepository;
     private TokenService tokenService;

     public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, TokenService tokenService) {
         this.tokenService = tokenService;
         this.userRepository = userRepository;
         this.authenticationManager = authenticationManager;
     }


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationDTO authenticationDTO) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(authenticationDTO.login(), authenticationDTO.password());
        var auth = this.authenticationManager.authenticate(authenticationToken);

        var token = this.tokenService.generateToken(String.valueOf((UserModel)auth.getPrincipal()));

        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterDTO registerDTO) {
        if(this.userRepository.findByLogin(registerDTO.login()) != null) {
            return ResponseEntity.badRequest().body("User already exists");
        }

        String encodedPassword = new BCryptPasswordEncoder().encode(registerDTO.password());
        var userModel = new UserModel(registerDTO.login(), encodedPassword, registerDTO.role());
        this.userRepository.save(userModel);

        return ResponseEntity.ok().build();
    }
}
