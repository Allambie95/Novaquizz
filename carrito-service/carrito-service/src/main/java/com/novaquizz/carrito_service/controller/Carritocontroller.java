package com.novaquizz.carrito_service.controller;

import com.novaquizz.carrito_service.model.Carritomodel;
import com.novaquizz.carrito_service.repository.Carritorepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/carrito")
@Tag(name = "Carrito", description = "Operaciones CRUD para registros del carrito")
public class Carritocontroller {

    @Autowired
    private Carritorepository repositorio;

    @GetMapping
    @Operation(summary = "Obtener todos los registros del carrito")
    public List<Carritomodel> obtenerTodos() {
        return repositorio.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener registro de carrito por ID")
    public EntityModel<Carritomodel> obtenerPorId(@PathVariable Long id) {
        Carritomodel carrito = repositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));

        return EntityModel.of(carrito,
                linkTo(methodOn(Carritocontroller.class).obtenerPorId(id)).withSelfRel(),
                linkTo(methodOn(Carritocontroller.class).obtenerTodos()).withRel("todos-los-registros"),
                linkTo(Carritocontroller.class).slash(id).withRel("eliminar-registro")
        );
    }

    @GetMapping("/usuario/{usuarioId}")
    @Operation(summary = "Obtener registros por ID de usuario")
    public List<Carritomodel> obtenerPorUsuario(@PathVariable Long usuarioId) {
        return repositorio.findByUsuarioId(usuarioId);
    }

    @PostMapping
    @Operation(summary = "Crear nuevo registro en el carrito")
    public Carritomodel crear(@RequestBody Carritomodel carrito) {
        return repositorio.save(carrito);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un registro del carrito")
    public Carritomodel actualizar(@PathVariable Long id, @RequestBody Carritomodel carrito) {
        carrito.setId(id);
        return repositorio.save(carrito);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un registro del carrito")
    public void eliminar(@PathVariable Long id) {
        repositorio.deleteById(id);
    }

    // Para pruebas unitarias
    public void setRepositorio(Carritorepository repositorio) {
        this.repositorio = repositorio;
    }
}
