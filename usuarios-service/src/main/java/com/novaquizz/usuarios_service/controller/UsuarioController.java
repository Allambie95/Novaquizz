package com.novaquizz.usuarios_service.controller;

import com.novaquizz.usuarios_service.model.Usuario;
import com.novaquizz.usuarios_service.repository.UsuarioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Usuarios", description = "Operaciones CRUD para usuarios")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepo;

    @GetMapping
    @Operation(summary = "Obtener todos los usuarios")
    public List<Usuario> obtenerTodos() {
        return usuarioRepo.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener usuario por ID")
    public EntityModel<Usuario> obtenerPorId(@PathVariable Long id) {
        Usuario usuario = usuarioRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return EntityModel.of(usuario,
                linkTo(methodOn(UsuarioController.class).obtenerPorId(id)).withSelfRel(),
                linkTo(methodOn(UsuarioController.class).obtenerTodos()).withRel("todos-los-usuarios"),
                linkTo(UsuarioController.class).slash(id).withRel("eliminar-usuario")
        );
    }

    @GetMapping("/buscar")
    @Operation(summary = "Buscar usuarios por nombre")
    public List<Usuario> buscarPorNombre(@RequestParam String nombre) {
        return usuarioRepo.findByNombreContainingIgnoreCase(nombre);
    }

    @PostMapping
    @Operation(summary = "Crear nuevo usuario")
    public Usuario crear(@RequestBody Usuario usuario) {
        return usuarioRepo.save(usuario);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar usuario por ID")
    public Usuario actualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        usuario.setId(id);
        return usuarioRepo.save(usuario);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar usuario por ID")
    public void eliminar(@PathVariable Long id) {
        usuarioRepo.deleteById(id);
    }

    // Para usar en tests
    public void setUsuarioRepo(UsuarioRepository usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }
}