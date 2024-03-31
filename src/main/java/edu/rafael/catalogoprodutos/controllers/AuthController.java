package edu.rafael.catalogoprodutos.controllers;

import edu.rafael.catalogoprodutos.dto.EmailDto;
import edu.rafael.catalogoprodutos.dto.NewPasswordDto;
import edu.rafael.catalogoprodutos.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "/recover-token")
    public ResponseEntity<Void> createRecoverToken(@Valid @RequestBody EmailDto body){
        authService.createRecoverToken(body);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/new-password")
    public ResponseEntity<Void> saveNewPassword(@Valid @RequestBody NewPasswordDto body){
        authService.saveNewPassword(body);
        return ResponseEntity.noContent().build();
    }
}
