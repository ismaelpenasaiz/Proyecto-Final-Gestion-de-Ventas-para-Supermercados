package com.example.Gestion.de.Ventas.para.Supermercados.auth;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.Gestion.de.Ventas.para.Supermercados.entities.Usuario;
import com.example.Gestion.de.Ventas.para.Supermercados.repositories.UsuarioRepository;
import com.example.Gestion.de.Ventas.para.Supermercados.security.JwtService;

@Service
public class AuthService {

    private final UsuarioRepository repo;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;

    public AuthService(UsuarioRepository repo,
                       PasswordEncoder encoder,
                       JwtService jwtService) {
        this.repo = repo;
        this.encoder = encoder;
        this.jwtService = jwtService;
    }

    public AuthResponse register(RegisterRequest req) {
        Usuario u = new Usuario();
        u.setUsername(req.username());
        u.setPassword(encoder.encode(req.password()));

        repo.save(u);

        String token = jwtService.generateToken(u.getUsername());
        return new AuthResponse(token);
    }

    public AuthResponse login(LoginRequest req) {
        Usuario u = repo.findByUsername(req.username())
                .orElseThrow();

        if (!encoder.matches(req.password(), u.getPassword()))
            throw new RuntimeException("Credenciales inválidas");

        String token = jwtService.generateToken(u.getUsername());
        return new AuthResponse(token);
    }
}
