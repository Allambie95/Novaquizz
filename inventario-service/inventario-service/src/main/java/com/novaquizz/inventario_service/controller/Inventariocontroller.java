package com.novaquizz.inventario_service.controller;

import com.novaquizz.inventario_service.model.Inventariomodel;
import com.novaquizz.inventario_service.repository.Inventariorepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/inventario")
public class Inventariocontroller {

    @Autowired
    private Inventariorepository repositorio;

    // Obtener todos los juegos
    @GetMapping
    public List<Inventariomodel> obtenerTodos() {
        return repositorio.findAll();
    }

    // Obtener un juego por ID
    @GetMapping("/{id}")
    public Optional<Inventariomodel> obtenerPorId(@PathVariable Long id) {
        return repositorio.findById(id);
    }

    // Buscar por título
    @GetMapping("/buscar")
    public List<Inventariomodel> buscarPorTitulo(@RequestParam String titulo) {
        return repositorio.findByTituloContainingIgnoreCase(titulo);
    }

    // Crear un nuevo juego
    @PostMapping
    public Inventariomodel crear(@RequestBody Inventariomodel juego) {
        return repositorio.save(juego);
    }

    // Actualizar un juego
    @PutMapping("/{id}")
    public Inventariomodel actualizar(@PathVariable Long id, @RequestBody Inventariomodel juego) {
        juego.setId(id);
        return repositorio.save(juego);
    }

    // Eliminar un juego
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        repositorio.deleteById(id);
    }
}
