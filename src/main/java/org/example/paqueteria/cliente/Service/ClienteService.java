package org.example.paqueteria.cliente.Service;

import lombok.RequiredArgsConstructor;
import org.example.paqueteria.cliente.Dto.ClienteDto;
import org.example.paqueteria.cliente.Entity.Cliente;
import org.example.paqueteria.cliente.Repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public List<Cliente> obtenerTodos() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> obtenerPorId(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente guardar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    // IMPLEMENTACIÓN DE LÓGICA DE ACTUALIZAR CLIENTE
    public Optional<Cliente> actualizar(Long id, ClienteDto dto) {
        return clienteRepository.findById(id).map(clienteExistente -> {
            clienteExistente.setNombre(dto.getNombre());
            clienteExistente.setApellido(dto.getApellido());
            clienteExistente.setTelefono(dto.getTelefono());
            clienteExistente.setDireccion(dto.getDireccion());

            return clienteRepository.save(clienteExistente);
        });
    }

    // NUEVO MÉTODO PARA EL AUTOCOMPLETADO
    public List<Cliente> buscarPorNombreOApellido(String query) {
        if (query == null || query.trim().isEmpty()) {
            return List.of();
        }
        return clienteRepository.findByNombreContainingIgnoreCaseOrApellidoContainingIgnoreCase(query, query);
    }

    public void eliminar(Long id) {
        clienteRepository.deleteById(id);
    }
}