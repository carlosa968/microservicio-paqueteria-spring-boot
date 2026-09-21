package org.example.paqueteria.auth.Service;

import lombok.RequiredArgsConstructor;
import org.example.paqueteria.cliente.Entity.Cliente;
import org.example.paqueteria.cliente.Repository.ClienteRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final ClienteRepository clienteRepository;
    private final PasswordEncoder passwordEncoder; // Inyectamos el encriptador

    public Cliente autenticar(String email, String passwordPlano) {
        return clienteRepository.findByEmail(email)
                .filter(cliente -> passwordEncoder.matches(passwordPlano, cliente.getPassword())) // Compara el hash de forma segura
                .orElseThrow(() -> new RuntimeException("Correo o contraseña incorrectos"));
    }

}
