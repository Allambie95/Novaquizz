package com.novaquizz.usuarios_service.controller;

import com.novaquizz.usuarios_service.model.Usuario;
import com.novaquizz.usuarios_service.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioControllerTest {

    private UsuarioRepository usuarioRepo;
    private UsuarioController controller;

    @BeforeEach
    void setUp() {
        usuarioRepo = mock(UsuarioRepository.class);
        controller = new UsuarioController();
        controller.setUsuarioRepo(usuarioRepo);
    }

    @Test
    void testObtenerTodos() {
        Usuario u = new Usuario(1L, "Ana", "ana@mail.com", "1234", Usuario.Rol.ALUMNO);
        when(usuarioRepo.findAll()).thenReturn(Arrays.asList(u));

        var result = controller.obtenerTodos();

        assertEquals(1, result.size());
        assertEquals("Ana", result.get(0).getNombre());
    }

    @Test
    void testObtenerPorId() {
        Usuario u = new Usuario(2L, "Beto", "beto@mail.com", "pass", Usuario.Rol.ADMIN);
        when(usuarioRepo.findById(2L)).thenReturn(Optional.of(u));

        var result = controller.obtenerPorId(2L);

        assertNotNull(result);
     assertEquals("Beto", result.getContent().getNombre());
    }

    @Test
    void testBuscarPorNombre() {
        Usuario u = new Usuario(3L, "Carla", "carla@mail.com", "clave", Usuario.Rol.ALUMNO);
        when(usuarioRepo.findByNombreContainingIgnoreCase("car")).thenReturn(Arrays.asList(u));

        var result = controller.buscarPorNombre("car");

        assertEquals(1, result.size());
        assertEquals("Carla", result.get(0).getNombre());
    }

    @Test
    void testCrear() {
        Usuario u = new Usuario(null, "David", "david@mail.com", "123", Usuario.Rol.ADMIN);
        Usuario guardado = new Usuario(4L, "David", "david@mail.com", "123", Usuario.Rol.ADMIN);

        when(usuarioRepo.save(u)).thenReturn(guardado);

        var result = controller.crear(u);

        assertEquals(4L, result.getId());
        assertEquals("David", result.getNombre());
    }

    @Test
    void testActualizar() {
        Usuario original = new Usuario(null, "Elena", "elena@mail.com", "abc", Usuario.Rol.ALUMNO);
        Usuario actualizado = new Usuario(5L, "Elena", "elena@mail.com", "abc", Usuario.Rol.ALUMNO);

        when(usuarioRepo.save(actualizado)).thenReturn(actualizado);

        var result = controller.actualizar(5L, original);

        assertEquals(5L, result.getId());
        assertEquals("Elena", result.getNombre());
    }

    @Test
    void testEliminar() {
        doNothing().when(usuarioRepo).deleteById(6L);

        assertDoesNotThrow(() -> controller.eliminar(6L));
        verify(usuarioRepo, times(1)).deleteById(6L);
    }
}
