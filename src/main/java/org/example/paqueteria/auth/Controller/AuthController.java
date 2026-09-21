package org.example.paqueteria.auth.Controller;


import lombok.RequiredArgsConstructor;
import org.example.paqueteria.auth.Dto.LoginRequestDto;
import org.example.paqueteria.auth.Dto.LoginResponseDto;
import org.example.paqueteria.auth.JwtService;
import org.example.paqueteria.auth.Service.AuthService;
import org.example.paqueteria.cliente.Entity.Cliente;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins =  "*")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final JwtService jwtService; // 👈 Inyectamos el generador de tokens

    @PostMapping("/login") // 👈 Deja solamente "/login"
    public ResponseEntity<?> login (@RequestBody LoginRequestDto request){
        try{
            // Validamos credenciales (lanza excepción si falla)
            Cliente cliente = authService.autenticar(request.getEmail(), request.getPassword());

            // Generamos el JWT usando su email
            String token = jwtService.generarToken(cliente.getEmail());

            // Regresamos el token al frontend
            return ResponseEntity.status(HttpStatus.OK).body(new LoginResponseDto(token, "Login exitoso"));

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
