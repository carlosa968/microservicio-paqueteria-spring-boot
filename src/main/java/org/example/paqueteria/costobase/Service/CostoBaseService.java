package org.example.paqueteria.costobase.Service;

import org.example.paqueteria.costobase.Dto.CostoBaseDto;
import org.example.paqueteria.costobase.Entity.CostoBase;
import org.example.paqueteria.costobase.Mapper.CostoBaseMapper;
import org.example.paqueteria.costobase.Repository.CostoBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CostoBaseService {

    @Autowired
    private CostoBaseRepository costoBaseRepository;

    // Listar todos los costos base
    public List<CostoBaseDto> listarTodos() {
        List<CostoBase> entidades = costoBaseRepository.findAll();
        return entidades.stream()
                .map(CostoBaseMapper::toDto)
                .collect(Collectors.toList());
    }

    // Buscar por ID
    public CostoBaseDto buscarPorId(Long id) {
        CostoBase entidad = costoBaseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Costo base no encontrado con el id: " + id));
        return CostoBaseMapper.toDto(entidad);
    }

    // Guardar o actualizar
    public CostoBaseDto guardar(CostoBaseDto costoBaseDto) {
        CostoBase entidad = CostoBaseMapper.toEntity(costoBaseDto);
        CostoBase entidadGuardada = costoBaseRepository.save(entidad);
        return CostoBaseMapper.toDto(entidadGuardada);
    }

    // Eliminar
    public void eliminar(Long id) {
        if (!costoBaseRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar, el costo base con id " + id + " no existe");
        }
        costoBaseRepository.deleteById(id);
    }
}