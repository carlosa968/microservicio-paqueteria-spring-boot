package org.example.paqueteria.descuento.Service;

import lombok.RequiredArgsConstructor;
import org.example.paqueteria.costobase.Dto.CostoBaseDto;
import org.example.paqueteria.costobase.Entity.CostoBase;
import org.example.paqueteria.costobase.Mapper.CostoBaseMapper;
import org.example.paqueteria.descuento.Dto.DescuentoDto;
import org.example.paqueteria.descuento.Entity.Descuentos;
import org.example.paqueteria.descuento.Mapper.DescuentoMapper;
import org.example.paqueteria.descuento.Repository.DescuentosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DescuentoService {

    private final DescuentosRepository descuentosRepository;

    public List<DescuentoDto> listarTodos(){
        List<Descuentos> entidades = descuentosRepository.findAll();
        return  entidades.stream()
                .map(DescuentoMapper::toDto)
                .collect(Collectors.toList());

    }

    // Buscar por ID
    public DescuentoDto buscarPorId(Long id) {
        Descuentos entidad = descuentosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Descuento  no encontrado con el id: " + id));
        return DescuentoMapper.toDto(entidad);
    }

    // Guardar o actualizar
    public DescuentoDto guardar(DescuentoDto descuentoDto) {
        Descuentos entidad = DescuentoMapper.toEntity(descuentoDto);
        Descuentos entidadGuardada = descuentosRepository.save(entidad);
        return DescuentoMapper.toDto(entidadGuardada);
    }
    //actulizar
    public  DescuentoDto actualizar (Long id, DescuentoDto dto){
        Descuentos descuentoExistente = descuentosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Descuento no encontrdo con ID: " + id));
        descuentoExistente.setDescuento(dto.getDescuento());
        descuentoExistente.setEsClienteFrecuente(dto.isEsClienteFrecuente());
        Descuentos actulizado = descuentosRepository.save(descuentoExistente);
        return  DescuentoMapper.toDto(actulizado);
    }

    // Eliminar
    public void eliminar(Long id) {
        if (!descuentosRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar, el desceunto con id " + id + " no existe");
        }
        descuentosRepository.deleteById(id);
    }

}
