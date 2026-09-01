package org.example.paqueteria.testPaqueteria;

import org.example.paqueteria.cliente.Entity.Cliente;
import org.example.paqueteria.cliente.Repository.ClienteRepository;
import org.example.paqueteria.costobase.Entity.CostoBase;
import org.example.paqueteria.costobase.Repository.CostoBaseRepository;
import org.example.paqueteria.descuento.Repository.DescuentosRepository;
import org.example.paqueteria.paquete.Dto.PaqueteDto;
import org.example.paqueteria.paquete.Entity.Paquete;
import org.example.paqueteria.paquete.Exceptions.IdInvalidoException;
import org.example.paqueteria.paquete.Exceptions.PaqueteNoEncontradoException;
import org.example.paqueteria.paquete.Repository.PaqueteRepository;
import org.example.paqueteria.paquete.Service.PaqueteService;
import org.example.paqueteria.recargo.Repository.RecargoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaqueteServiceTest {

    @Mock
    private PaqueteRepository paqueteRepository;
    @Mock
    private ClienteRepository clienteRepository;
    @Mock
    private CostoBaseRepository costoBaseRepository;
    @Mock
    private DescuentosRepository descuentosRepository;
    @Mock
    private RecargoRepository recargoRepository;

    @InjectMocks
    private PaqueteService service;

    @Test
    void guardarPaquete() {
        // 1. Arrange (Preparar datos y mocks)
        Cliente clienteFalso = new Cliente();
        clienteFalso.setId(1L);

        CostoBase costoBaseFalso = new CostoBase();
        costoBaseFalso.setCostoBase(100.0);
        costoBaseFalso.setLimiteKilos(5.0);
        costoBaseFalso.setCostoExtra(20.0);

        Paquete paqueteFalso = new Paquete();
        paqueteFalso.setPesoKg(4.0);
        paqueteFalso.setZonaDestino("NORTE");
        paqueteFalso.setDistanciaKm(100);

        // --- CONFIGURANDO LOS WHEN (Simulando la base de datos) ---
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(clienteFalso));
        when(costoBaseRepository.findTopByOrderByIdAsc()).thenReturn(Optional.of(costoBaseFalso));
        when(paqueteRepository.countByClienteId(1L)).thenReturn(0L);
        when(descuentosRepository.findByEsClienteFrecuente(false)).thenReturn(Optional.empty());
        when(recargoRepository.findByZona("NORTE")).thenReturn(Optional.empty());

        when(paqueteRepository.save(any(Paquete.class))).thenAnswer(invocation -> {
            Paquete p = invocation.getArgument(0);
            p.setId(10L); // Simulamos el ID generado por la BD
            return p;
        });

        // 2. Act (Ejecutar el método real del servicio)
        Paquete resultado = service.guardar(paqueteFalso, 1L);

        // 3. Assert (Comprobar los resultados esperados)
        assertNotNull(resultado);
        assertEquals(10L, resultado.getId());
        assertEquals(100.0, resultado.getCostoEnvio()); // Costo base directo ya que pesa menos del límite y no tiene recargos
        assertEquals("Normal", resultado.getPrioridad()); // Menos de 500 km
    }

    @Test
    void testBuscarPaquetePorId() {
        // 1. Arrange: Inventa un ID de prueba y configura el when para que el repositorio mock devuelva un objeto falso envuelto en Optional
        Long idBuscado = 1L;
        Paquete tuPaqueteFalso = new Paquete();
        tuPaqueteFalso.setId(idBuscado);
        when(paqueteRepository.findById(idBuscado)).thenReturn(Optional.of(tuPaqueteFalso));
        // 2. Act: Llama al método del servicio pasándole ese ID
        Paquete resultado = service.obtenerPorId(idBuscado);
        // 3. Assert: Comprueba con un assert que el resultado no sea nulo y que tenga el ID correcto
        assertNotNull(resultado);
        assertEquals(idBuscado, resultado.getId());
    }

    @Test
    void testBuscarTodos(){
        List<Paquete> consultaFalsa= List.of(new Paquete(), new Paquete());
        when(paqueteRepository.findAll()).thenReturn(consultaFalsa);

        List<Paquete> resultado = service.obtenerTodos();

        assertNotNull(resultado);
        assertEquals(2,resultado.size());
    }

    @Test
    void testActualizar(){
        Long id = 3L;
        Long clienteIdFalso = 1L;

        // 1. Arrange: Datos existentes en la BD
        Paquete paqueteExistente = new Paquete();
        paqueteExistente.setId(id);
        paqueteExistente.setPesoKg(9.0);
        paqueteExistente.setDistanciaKm(500);
        paqueteExistente.setZonaDestino("Nacional");

        // Datos nuevos que vienen del DTO
        PaqueteDto dtoNuevosDatos = new PaqueteDto();
        dtoNuevosDatos.setPesoKg(8.8);
        dtoNuevosDatos.setZonaDestino("Internacional");
        dtoNuevosDatos.setDistanciaKm(700);

        // Mocks para buscar el paquete a actualizar
        when(paqueteRepository.findById(id)).thenReturn(Optional.of(paqueteExistente));

        // Mocks extra que necesita el método guardar (porque actualizar llama a guardar)
        Cliente clienteFalso = new Cliente();
        clienteFalso.setId(clienteIdFalso);
        CostoBase costoBaseFalso = new CostoBase();
        costoBaseFalso.setCostoBase(100.0);
        costoBaseFalso.setLimiteKilos(5.0);
        costoBaseFalso.setCostoExtra(20.0);

        when(clienteRepository.findById(clienteIdFalso)).thenReturn(Optional.of(clienteFalso));
        when(costoBaseRepository.findTopByOrderByIdAsc()).thenReturn(Optional.of(costoBaseFalso));
        when(paqueteRepository.countByClienteId(clienteIdFalso)).thenReturn(0L);
        when(descuentosRepository.findByEsClienteFrecuente(false)).thenReturn(Optional.empty());
        when(recargoRepository.findByZona("Internacional")).thenReturn(Optional.empty());

        when(paqueteRepository.save(any(Paquete.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // 2. Act
        Paquete resultado = service.actualizar(id, clienteIdFalso, dtoNuevosDatos);

        // 3. Assert
        assertNotNull(resultado);
        assertEquals(8.8, resultado.getPesoKg());
        assertEquals(700, resultado.getDistanciaKm());
        assertEquals("Internacional", resultado.getZonaDestino());
    }

    @Test
    void testEliminarPaquete(){
        Long id = 1L;
        when(paqueteRepository.existsById(id)).thenReturn(true);


        service.borrar(id);

        verify(paqueteRepository, times(1)).deleteById(id);

    }
//////////////////////////fallo de que algo no existe etc
    @Test
    void obtenerPorId_debeLanzarExcepcionSiNoExiste() {

        Long id = 99L;

        when(paqueteRepository.findById(id))
                .thenReturn(Optional.empty());

        assertThrows(
                PaqueteNoEncontradoException.class,
                () -> service.obtenerPorId(id)
        );
    }

    @Test
    void borrar_debeLanzarExcepcionSiNoExiste() {

        Long id = 99L;

        when(paqueteRepository.existsById(id))
                .thenReturn(false);

        assertThrows(
                PaqueteNoEncontradoException.class,
                () -> service.borrar(id)
        );

        verify(paqueteRepository, never()).deleteById(id);
    }

    @Test
    void actualizar_debeLanzarExcepcionSiPaqueteNoExiste() {

        Long id = 99L;
        Long clienteId = 1L;

        PaqueteDto dto = new PaqueteDto();

        when(paqueteRepository.findById(id))
                .thenReturn(Optional.empty());

        assertThrows(
                PaqueteNoEncontradoException.class,
                () -> service.actualizar(id, clienteId, dto)
        );
    }
    @Test
    void guardar_debeLanzarExcepcionSiClienteNoExiste() {

        Long clienteId = 99L;

        Paquete paquete = new Paquete();

        when(clienteRepository.findById(clienteId))
                .thenReturn(Optional.empty());

        assertThrows(
                RuntimeException.class,
                () -> service.guardar(paquete, clienteId)
        );
    }

    @Test
    void guardar_debeLanzarExcepcionSiNoExisteCostoBase() {

        Long clienteId = 1L;

        Paquete paquete = new Paquete();

        Cliente cliente = new Cliente();

        when(clienteRepository.findById(clienteId))
                .thenReturn(Optional.of(cliente));

        when(costoBaseRepository.findTopByOrderByIdAsc())
                .thenReturn(Optional.empty());

        assertThrows(
                RuntimeException.class,
                () -> service.guardar(paquete, clienteId)
        );
    }

    @Test
    void obtenerPorId_debeLanzarExcepcionSiIdInvalido() {
        Long idInvalido = 0L; // Puede ser 0 o un número negativo como -5L

        assertThrows(
                IdInvalidoException.class,
                () -> service.obtenerPorId(idInvalido)
        );

        // Verificamos que la base de datos jamás sea consultada porque la validación frena el proceso antes
        verify(paqueteRepository, never()).findById(any());
    }
}