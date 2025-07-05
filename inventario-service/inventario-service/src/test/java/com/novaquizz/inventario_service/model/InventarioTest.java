package com.novaquizz.inventario_service.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InventariomodelTest {

    @Test
    void crearJuegoYVerificarDatos() {
        Inventariomodel juego = new Inventariomodel(
                1L,
                "Ajedrez",
                "Juego de estrategia clásica",
                "Autor1",
                "Estrategia",
                "Medio",
                15.0,
                true
        );

        assertEquals(1L, juego.getId());
        assertEquals("Ajedrez", juego.getTitulo());
        assertEquals("Juego de estrategia clásica", juego.getDescripcion());
        assertEquals("Autor1", juego.getAutor());
        assertEquals("Estrategia", juego.getCategoria());
        assertEquals("Medio", juego.getNivel());
        assertEquals(15.0, juego.getPrecio());
        assertTrue(juego.getDisponible());
    }

    @Test
    void modificarDatosJuego() {
        Inventariomodel juego = new Inventariomodel();
        juego.setTitulo("Clue");
        juego.setPrecio(18.5);
        juego.setDisponible(false);

        assertEquals("Clue", juego.getTitulo());
        assertEquals(18.5, juego.getPrecio());
        assertFalse(juego.getDisponible());
    }
}