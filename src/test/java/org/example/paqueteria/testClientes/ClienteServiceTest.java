package org.example.paqueteria.testClientes;
import org.example.paqueteria.cliente.Dto.ClienteDto;
import org.example.paqueteria.cliente.Entity.Cliente;
import org.example.paqueteria.cliente.Exceptions.ClienteNoEncontradoException;
import org.example.paqueteria.cliente.Repository.ClienteRepository;
import org.example.paqueteria.cliente.Service.ClienteService;
import org.example.paqueteria.paquete.Entity.Paquete;
import org.example.paqueteria.paquete.Exceptions.PaqueteNoEncontradoException;
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
public class ClienteServiceTest {
    @Mock
    private ClienteRepository clienteRepository;


    @InjectMocks
    private ClienteService service;

    //////////////////Obtenr tood los clientes caso exitoso
    @Test
    void testBuscarTodos(){
        List<Cliente> consultaFalsa = List.of(new Cliente(), new Cliente());
        when(clienteRepository.findAll()).thenReturn(consultaFalsa);

        List<Cliente> resultado= service.obtenerTodos();

        assertNotNull(resultado);
        assertEquals(2,resultado.size());

        /*
        "Voy a fingir que mi Repository encuentra 2 clientes. Después voy a ejecutar obtenerTodos()
        y voy a comprobar que el Service me regrese una lista que no sea null y que tenga esos 2 clientes."
         */
    }

    /// obtenr por un ID ok ...
    @Test
    void testObtenerPorId(){
        // 1. Arrange nventa un ID de prueba y configura el when para que el repositorio mock
        // devuelva un objeto falso envuelto en Optional
        Long idBuscar = 1L;
        Cliente clienteFalso = new Cliente();
        clienteFalso.setId(idBuscar);
        when(clienteRepository.findById(idBuscar)).thenReturn(Optional.of(clienteFalso));
        // 2. Act: Llama al método del servicio pasándole ese ID
        Cliente resultado = service.obtenerPorId(idBuscar);
        // 3. Assert: Comprueba con un assert que el resultado no sea nulo y que tenga el ID correcto
        assertNotNull(resultado);
        assertEquals(idBuscar, resultado.getId());

    }

    @Test
    void obtenerPorId_debeLanzarExcepcionSinoExiste(){
        Long id = 99L;
         when(clienteRepository.findById(id)).thenReturn(Optional.empty());

         assertThrows(ClienteNoEncontradoException.class,  () -> service.obtenerPorId(id));
    }

    // para gaurar un cliente

    @Test
    void guardarClienteFalso(){
        Cliente clienteFalso = new Cliente();
        clienteFalso.setNombre("Miguel");
        clienteFalso.setApellido("Juarez");
        clienteFalso.setTelefono("5578655646");
        clienteFalso.setDireccion("Nose");

        when(clienteRepository.save(any(Cliente.class))) .thenAnswer(invocation -> {
            Cliente cliente = invocation.getArgument(0);
            cliente.setId(1L); // Simulamos ID generado por la BD
            return cliente;
        });


        Cliente resultado = service.guardar(clienteFalso);

        assertNotNull(resultado);
        assertEquals(1l, resultado.getId());
        assertEquals("Miguel", resultado.getNombre());
        assertEquals("Juarez", resultado.getApellido());
        assertEquals("5578655646", resultado.getTelefono());
        assertEquals("Nose", resultado.getDireccion());

        verify(clienteRepository, times(1)).save(clienteFalso);
    }

    // para Actualiar un clieinte exitsente jja
    @Test
    void testActualizarCliente(){
        Long id = 1l;
         Cliente clienteExistente = new Cliente();
        clienteExistente.setId(id);
         clienteExistente.setNombre("Miguel");
         clienteExistente.setApellido("Juarez");
         clienteExistente.setTelefono("5567477396");
         clienteExistente.setDireccion("Juarez 5");


        ClienteDto dtoClientAct = new ClienteDto();

        dtoClientAct.setNombre("Azael");
        dtoClientAct.setApellido("Moncayo");
        dtoClientAct.setTelefono("3452");
        dtoClientAct.setDireccion("5 de Febrero");

        when(clienteRepository.findById(id)).thenReturn(Optional.of(clienteExistente));

        when(clienteRepository.save(any(Cliente.class))).thenAnswer(invocation -> invocation.getArgument(0));


        Cliente result = service.actualizar(id,dtoClientAct);


        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Azael", result.getNombre());
        assertEquals("Moncayo", result.getApellido());
        assertEquals("3452", result.getTelefono());
        assertEquals("5 de Febrero", result.getDireccion());


        verify(clienteRepository, times(1)).findById(id);
        verify(clienteRepository, times(1)).save(clienteExistente);

    }


    //este e spar que actulice pero que no encutr eel id
    @Test
    void actualizar_debeLanzarExcepcionSiClienteNoExiste(){
        Long id = 99L;
        ClienteDto clienteDto = new ClienteDto();
        when(clienteRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(
                ClienteNoEncontradoException.class,
                () -> service.actualizar(id, clienteDto)
        );

    }



}
