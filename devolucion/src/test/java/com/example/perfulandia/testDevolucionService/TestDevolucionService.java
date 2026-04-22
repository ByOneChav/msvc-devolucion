package com.example.perfulandia.testDevolucionService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.perfulandia.repository.DevolucionRepository;
import com.example.perfulandia.service.DevolucionService;
import com.example.perfulandia.model.Devolucion;




@ExtendWith(MockitoExtension.class)
public class TestDevolucionService {

    @Mock
    private DevolucionRepository devolucionRepository;

    @InjectMocks
    private DevolucionService devolucionService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testBuscarTodo(){
        List<Devolucion> listaDevolucion = new ArrayList<>();

        Devolucion d1 = new Devolucion();

        d1.setIdDevolucion(1L);
        d1.setUsuario("Daniel");
        d1.setMotivo("No me gusto");
        d1.setFechaDevolucion(LocalDate.parse("2026-09-12"));
        d1.setProducto("Esika");
        d1.setEstado("En proceso");
        d1.setMontoReembolso(55000);


        listaDevolucion.add(d1);

        when(devolucionRepository.findAll()).thenReturn(listaDevolucion);
        List<Devolucion> resultadoBucado = devolucionService.buscarTodo();
        assertEquals(1, resultadoBucado.size());
        verify(devolucionRepository, times(1)).findAll();

    }

    @Test
    public void testBuscarUnaDevolucion(){

        Devolucion d1 = new Devolucion();

        d1.setIdDevolucion(1L);
        d1.setUsuario("Daniel");
        d1.setMotivo("No me gusto");
        d1.setFechaDevolucion(LocalDate.parse("2026-09-12"));
        d1.setProducto("Esika");
        d1.setEstado("En proceso");
        d1.setMontoReembolso(55000);

        when(devolucionRepository.findById(1L)).thenReturn(Optional.of(d1));
        Devolucion devolucionBuscado = devolucionService.buscar(1L);
        assertEquals(1, devolucionBuscado.getIdDevolucion());
        verify(devolucionRepository, times(1)).findById(1L);

    }

    @Test
    public void testGuardarDevolucion(){

        Devolucion d1 = new Devolucion();

        d1.setIdDevolucion(1L);
        d1.setUsuario("Daniel");
        d1.setMotivo("No me gusto");
        d1.setFechaDevolucion(LocalDate.parse("2026-09-12"));
        d1.setProducto("Esika");
        d1.setEstado("En proceso");
        d1.setMontoReembolso(55000);

        when(devolucionRepository.save(d1)).thenReturn(d1);
        Devolucion pagoGuardado = devolucionService.guardar(d1);
        assertEquals(1, pagoGuardado.getIdDevolucion());
        verify(devolucionRepository, times(1)).save(d1);
    }

    @Test
    public void testEliminarDevolucion(){
        Long idDevolucion = 1L; 
        doNothing().when(devolucionRepository).deleteById(idDevolucion);

        devolucionService.Eliminar(idDevolucion);
        verify(devolucionRepository, times(1)).deleteById(idDevolucion);
    }

}
