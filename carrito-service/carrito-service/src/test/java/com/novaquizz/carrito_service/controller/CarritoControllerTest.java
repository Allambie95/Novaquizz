package com.novaquizz.carrito_service.controller;

import com.novaquizz.carrito_service.model.Carritomodel;
import com.novaquizz.carrito_service.repository.Carritorepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CarritocontrollerTest {

    private Carritorepository repositorio;
    private Carritocontroller controller;

    @BeforeEach
    void setUp() {
        repositorio = mock(Carritorepository.class);
        controller = new Carritocontroller();
        controller.setRepositorio(repositorio);
    }

    @Test
    void testObtenerTodos() {
        Carritomodel c = new Carritomodel(1L, 10L, 20L, 2, LocalDateTime.now());
        when(repositorio.findAll()).thenReturn(Arrays.asList(c));

        var result = controller.obtenerTodos();

        assertEquals(1, result.size());
        assertEquals(10L, result.get(0).getUsuarioId());
    }

    @Test
    void testObtenerPorId() {
        Carritomodel c = new Carritomodel(2L, 11L, 21L, 1, LocalDateTime.now());
        when(repositorio.findById(2L)).thenReturn(Optional.of(c));

        var result = controller.obtenerPorId(2L);

        assertNotNull(result);
        assertEquals(11L, result.getContent().getUsuarioId());
    }

    @Test
    void testObtenerPorUsuario() {
        Carritomodel c = new Carritomodel(3L, 12L, 22L, 3, LocalDateTime.now());
        when(repositorio.findByUsuarioId(12L)).thenReturn(Arrays.asList(c));

        var result = controller.obtenerPorUsuario(12L);

        assertEquals(1, result.size());
        assertEquals(22L, result.get(0).getJuegoId());
    }

    @Test
    void testCrear() {
        Carritomodel nuevo = new Carritomodel(null, 13L, 23L, 1, LocalDateTime.now());
        Carritomodel guardado = new Carritomodel(4L, 13L, 23L, 1, LocalDateTime.now());

        when(repositorio.save(nuevo)).thenReturn(guardado);

        var result = controller.crear(nuevo);

        assertEquals(4L, result.getId());
        assertEquals(13L, result.getUsuarioId());
    }

    @Test
    void testActualizar() {
        Carritomodel original = new Carritomodel(null, 14L, 24L, 2, LocalDateTime.now());
        Carritomodel actualizado = new Carritomodel(5L, 14L, 24L, 2, LocalDateTime.now());

        when(repositorio.save(actualizado)).thenReturn(actualizado);

        var result = controller.actualizar(5L, original);

        assertEquals(5L, result.getId());
        assertEquals(14L, result.getUsuarioId());
    }

    @Test
    void testEliminar() {
        doNothing().when(repositorio).deleteById(6L);

        assertDoesNotThrow(() -> controller.eliminar(6L));
        verify(repositorio, times(1)).deleteById(6L);
    }
}