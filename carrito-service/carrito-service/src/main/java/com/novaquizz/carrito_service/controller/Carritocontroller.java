package com.novaquizz.carrito_service.controller;

import com.novaquizz.carrito_service.model.Carritomodel;
import com.novaquizz.carrito_service.repository.Carritorepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/carrito")
public class Carritocontroller {

    @Autowired
    private Carritorepository repositorio;

    // Obtener todos los registros del carrito
    @GetMapping
    public List<Carritomodel> obtenerTodos() {
        return repositorio.findAll();
    }

    // Obtener carrito por ID
    @GetMapping("/{id}")
    public Optional<Carritomodel> obtenerPorId(@PathVariable Long id) {
        return repositorio.findById(id);
    }

    // Buscar por usuario
    @GetMapping("/usuario/{usuarioId}")
    public List<Carritomodel> obtenerPorUsuario(@PathVariable Long usuarioId) {
        return repositorio.findByUsuarioId(usuarioId);
    }

    // Crear nuevo registro en el carrito
    @PostMapping
    public Carritomodel crear(@RequestBody Carritomodel carrito) {
        return repositorio.save(carrito);
    }

    // Actualizar un registro
    @PutMapping("/{id}")
    public Carritomodel actualizar(@PathVariable Long id, @RequestBody Carritomodel carrito) {
        carrito.setId(id);
        return repositorio.save(carrito);
    }

    // Eliminar un registro
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        repositorio.deleteById(id);
    }
}