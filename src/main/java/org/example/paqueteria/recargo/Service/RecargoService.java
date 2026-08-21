package org.example.paqueteria.recargo.Service;

import lombok.RequiredArgsConstructor;
import org.example.paqueteria.recargo.Dto.RecargoDto;
import org.example.paqueteria.recargo.Entity.Recargo;
import org.example.paqueteria.recargo.Mapper.RecargoMapper;
import org.example.paqueteria.recargo.Repository.RecargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor

public class RecargoService {

    private final  RecargoRepository recargoRepository;
    public List<RecargoDto> listasTodos(){
        List<Recargo> entidades = recargoRepository.findAll();
        return entidades.stream()
                .map(RecargoMapper::toDto)
                .collect(Collectors.toList());

    }


    // Buscar por ID
    public RecargoDto buscarPorId(Long id) {
        Recargo entidad = recargoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Costo base no encontrado con el id: " + id));
        return RecargoMapper.toDto(entidad);
    }

    // Guardar o actualizar
    public RecargoDto guardar(RecargoDto recargoDto) {
        Recargo entidad = RecargoMapper.toEntity(recargoDto);
        Recargo entidadGuardada = recargoRepository.save(entidad);
        return RecargoMapper.toDto(entidadGuardada);
    }

    //Actulizar
    public RecargoDto actualizar(Long id, RecargoDto dto) {
        // 1. Buscamos si el recargo existe en la BD (La lógica y validación viven aquí)
        Recargo recargoExistente = recargoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recargo no encontrado con ID: " + id));

        // 2. Actualizamos los campos con los datos que vienen del DTO
        recargoExistente.setZona(dto.getZona());
        recargoExistente.setMontoRecargo(dto.getMontoRecargo());

        // 3. Guardamos el objeto actualizado en la BD y lo convertimos a DTO para regresarlo
        Recargo actualizado = recargoRepository.save(recargoExistente);
        return RecargoMapper.toDto(actualizado); // (O como estés convirtiendo tu entidad a DTO en tu service)
    }

    // Eliminar
    public void eliminar(Long id) {
        if (!recargoRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar, el costo base con id " + id + " no existe");
        }
        recargoRepository.deleteById(id);
    }
}
