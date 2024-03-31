package edu.rafael.catalogoprodutos.services;

import edu.rafael.catalogoprodutos.dto.EmailDto;
import edu.rafael.catalogoprodutos.dto.NewPasswordDto;
import edu.rafael.catalogoprodutos.entities.PasswordRecover;
import edu.rafael.catalogoprodutos.entities.User;
import edu.rafael.catalogoprodutos.repositories.PasswordRecoverRepository;
import edu.rafael.catalogoprodutos.repositories.UserRepository;
import edu.rafael.catalogoprodutos.services.exceptions.EntitiesNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class AuthService {
    @Value("${email.password-recover.token.minutes}")
    private Long tokenMinutes;
    @Value("${email.password-recover.uri}")
    private String recoverUri;

    @Autowired
    private PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final PasswordRecoverRepository passwordRecoverRepository;
    private final EmailService emailService;

    public AuthService(UserRepository userRepository, PasswordRecoverRepository passwordRecoverRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.passwordRecoverRepository = passwordRecoverRepository;
        this.emailService = emailService;
    }

    @Transactional
    public void createRecoverToken(EmailDto body) {
        User user = userRepository.findByEmail(body.getEmail());
        if (user == null){
            throw new EntitiesNotFoundException("Email não encontrado");
        }
        String token = UUID.randomUUID().toString();
        PasswordRecover passwordRecover = new PasswordRecover();
        passwordRecover.setEmail(body.getEmail());
        passwordRecover.setToken(token);
        passwordRecover.setExpiration(Instant.now().plusSeconds(tokenMinutes*60L));
        passwordRecoverRepository.save(passwordRecover);
        String text = "Acesse o link para redefinir uma nova senha\n\n"+recoverUri+token+". Validade de "+tokenMinutes+" minutos";
        emailService.sendEmail(body.getEmail(), "Recuperação de senha", text);
    }

    @Transactional
    public void saveNewPassword(NewPasswordDto body) {
        List<PasswordRecover> result = passwordRecoverRepository.searchValidTokens(body.getToken(), Instant.now());
        if (result.size() == 0){
            throw new EntitiesNotFoundException("Token inválido");
        }
        User user = userRepository.findByEmail(result.get(0).getEmail());
        user.setPassword(passwordEncoder.encode(body.getPassword()));
        userRepository.save(user);
    }

    protected User authenticated() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Jwt jwtPrincipal = (Jwt) authentication.getPrincipal();
            String username = jwtPrincipal.getClaim("username");
            return userRepository.findByEmail(username);
        }
        catch (Exception e) {
            throw new UsernameNotFoundException("Invalid user");
        }
    }
}
