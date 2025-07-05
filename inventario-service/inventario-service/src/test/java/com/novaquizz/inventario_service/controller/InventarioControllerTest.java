package com.novaquizz.inventario_service.controller;

import com.novaquizz.inventario_service.model.Inventariomodel;
import com.novaquizz.inventario_service.repository.Inventariorepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InventariocontrollerTest {

    private Inventariorepository repositorio;
    private Inventariocontroller controller;

    @BeforeEach
    void setUp() {
        repositorio = mock(Inventariorepository.class);
        controller = new Inventariocontroller();
        controller.setRepositorio(repositorio);
    }

    @Test
    void testObtenerTodos() {
        Inventariomodel juego = new Inventariomodel(1L, "Ajedrez", "Juego clásico", "Autor1", "Estrategia", "Medio", 15.0, true);
        when(repositorio.findAll()).thenReturn(Arrays.asList(juego));

        var result = controller.obtenerTodos();

        assertEquals(1, result.size());
        assertEquals("Ajedrez", result.get(0).getTitulo());
    }

    @Test
    void testObtenerPorId() {
        Inventariomodel juego = new Inventariomodel(2L, "Damas", "Juego simple", "Autor2", "estrategia", "Básico", 10.0, true);
        when(repositorio.findById(2L)).thenReturn(Optional.of(juego));

        var result = controller.obtenerPorId(2L);

        assertNotNull(result);
        assertEquals("Damas", result.getContent().getTitulo());
    }

    @Test
    void testBuscarPorTitulo() {
        Inventariomodel juego = new Inventariomodel(3L, "Monopoly", "Juego de negocios", "Autor3", "Mesa", "Familiar", 20.0, true);
        when(repositorio.findByTituloContainingIgnoreCase("mono")).thenReturn(Arrays.asList(juego));

        var result = controller.buscarPorTitulo("mono");

        assertEquals(1, result.size());
        assertEquals("Monopoly", result.get(0).getTitulo());
    }

    @Test
    void testCrear() {
        Inventariomodel nuevo = new Inventariomodel(null, "Clue", "Detectives", "Autor4", "Mesa", "Familiar", 18.0, true);
        Inventariomodel guardado = new Inventariomodel(4L, "Clue", "Detectives", "Autor4", "Mesa", "Familiar", 18.0, true);

        when(repositorio.save(nuevo)).thenReturn(guardado);

        var result = controller.crear(nuevo);

        assertEquals(4L, result.getId());
        assertEquals("Clue", result.getTitulo());
    }

    @Test
    void testActualizar() {
        Inventariomodel original = new Inventariomodel(null, "Risk", "Estrategia mundial", "Autor5", "Estrategia", "Avanzado", 25.0, true);
        Inventariomodel actualizado = new Inventariomodel(5L, "Risk", "Estrategia mundial", "Autor5", "Estrategia", "Avanzado", 25.0, true);

        when(repositorio.save(actualizado)).thenReturn(actualizado);

        var result = controller.actualizar(5L, original);

        assertEquals(5L, result.getId());
        assertEquals("Risk", result.getTitulo());
    }

    @Test
    void testEliminar() {
        doNothing().when(repositorio).deleteById(6L);

        assertDoesNotThrow(() -> controller.eliminar(6L));
        verify(repositorio, times(1)).deleteById(6L);
    }
}