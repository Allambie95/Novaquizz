package com.novaquizz.carrito_service.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CarritomodelTest {

    @Test
    void crearCarritoYVerificarDatos() {
        LocalDateTime ahora = LocalDateTime.now();
        Carritomodel carrito = new Carritomodel(1L, 10L, 20L, 2, ahora);

        assertEquals(1L, carrito.getId());
        assertEquals(10L, carrito.getUsuarioId());
        assertEquals(20L, carrito.getJuegoId());
        assertEquals(2, carrito.getCantidad());
        assertEquals(ahora, carrito.getFechaAgregado());
    }

    @Test
    void modificarDatosCarrito() {
        Carritomodel carrito = new Carritomodel();
        LocalDateTime fecha = LocalDateTime.of(2025, 7, 5, 12, 0);

        carrito.setUsuarioId(15L);
        carrito.setJuegoId(25L);
        carrito.setCantidad(3);
        carrito.setFechaAgregado(fecha);

        assertEquals(15L, carrito.getUsuarioId());
        assertEquals(25L, carrito.getJuegoId());
        assertEquals(3, carrito.getCantidad());
        assertEquals(fecha, carrito.getFechaAgregado());
    }
}