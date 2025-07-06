package com.novaquizz.usuarios_service.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {

    @Test
    void crearUsuarioYVerificarDatos() {
        Usuario usuario = new Usuario(1L, "Catalina", "cata@mail.com", "1234", Usuario.Rol.ALUMNO);

        assertEquals(1L, usuario.getId());
        assertEquals("Catalina", usuario.getNombre());
        assertEquals("cata@mail.com", usuario.getCorreo());
        assertEquals("1234", usuario.getContrasena());
        assertEquals(Usuario.Rol.ALUMNO, usuario.getRol());
    }

    @Test
    void modificarDatosUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNombre("Carlos");
        usuario.setCorreo("carlos@mail.com");

        assertEquals("Carlos", usuario.getNombre());
        assertEquals("carlos@mail.com", usuario.getCorreo());
    }
}