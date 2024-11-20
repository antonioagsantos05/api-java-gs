package com.globalsolution2.fiap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globalsolution2.fiap.model.AuthenticationDTO;
import com.globalsolution2.fiap.model.LoginResponseDTO;
import com.globalsolution2.fiap.model.RegisterDTO;
import com.globalsolution2.fiap.model.UsuarioModel;
import com.globalsolution2.fiap.repository.UsuarioRepository;
import com.globalsolution2.fiap.security.TokenService;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private UsuarioRepository repository;
    
    @Autowired
    private TokenService tokenService;
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Validated AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        
        var token = tokenService.generateToken((UsuarioModel) auth.getPrincipal());
        
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
    
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Validated RegisterDTO data) {
        if(this.repository.findByNmLogin(data.login()) != null) return ResponseEntity.badRequest().build();
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        UsuarioModel newUser = new UsuarioModel(
            data.nmUsuario(),
            data.login(), 
            encryptedPassword, 
            data.nmEmail(), 
            data.role()
        );
        this.repository.save(newUser);
        
        return ResponseEntity.ok().build();
    }
}
