package org.example.paqueteria.cliente.Service;

import lombok.RequiredArgsConstructor;
import org.example.paqueteria.cliente.Dto.ClienteDto;
import org.example.paqueteria.cliente.Entity.Cliente;
import org.example.paqueteria.cliente.Exceptions.ClienteNoEncontradoException;
import org.example.paqueteria.cliente.Mapper.ClienteMapper;
import org.example.paqueteria.cliente.Repository.ClienteRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final PasswordEncoder passwordEncoder; // 👈 2. Inyéctalo aquí

    public List<Cliente> obtenerTodos() {
        return clienteRepository.findAll();
    }

    public Cliente obtenerPorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() ->
                        new ClienteNoEncontradoException("Cliente no encontrado"));
    }

    public Cliente guardar(ClienteDto dto) {
        Cliente cliente = ClienteMapper.toEntity(dto);
        cliente.setPassword(passwordEncoder.encode(dto.getPassword()));
        return clienteRepository.save(cliente);
    }

    // IMPLEMENTACIÓN DE LÓGICA DE ACTUALIZAR CLIENTE
    public Cliente actualizar(Long id, ClienteDto dto) {
        Cliente clienteExistente = clienteRepository.findById(id).orElseThrow(() ->
                new ClienteNoEncontradoException("Cliente no encontrado: " + id));

        clienteExistente.setNombre(dto.getNombre());
        clienteExistente.setApellido(dto.getApellido());
        clienteExistente.setTelefono(dto.getTelefono());
        clienteExistente.setDireccion(dto.getDireccion());
        clienteExistente.setEmail(dto.getEmail());

        // Si actualiza contraseña, la hasheamos; si viene vacía puedes validarla, pero por ahora va directo:
        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            clienteExistente.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        return clienteRepository.save(clienteExistente); // 👈 Guardamos directo el existente modificado
    }

    // NUEVO MÉTODO PARA EL AUTOCOMPLETADO
    public List<Cliente> buscarPorNombreOApellido(String query) {
        if (query == null || query.trim().isEmpty()) {
            return List.of();
        }
        return clienteRepository.findByNombreContainingIgnoreCaseOrApellidoContainingIgnoreCase(query, query);
    }

    public void eliminar(Long id) {

        if (!clienteRepository.existsById(id)) {
            throw new ClienteNoEncontradoException(
                    "Cliente no encontrado con ID: " + id
            );
        }

        clienteRepository.deleteById(id);
    }}