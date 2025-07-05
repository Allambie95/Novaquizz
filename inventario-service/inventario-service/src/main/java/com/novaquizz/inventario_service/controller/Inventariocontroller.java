package com.novaquizz.inventario_service.controller;

import com.novaquizz.inventario_service.model.Inventariomodel;
import com.novaquizz.inventario_service.repository.Inventariorepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/inventario")
@Tag(name = "Inventario", description = "Operaciones CRUD juegos en el inventario")
public class Inventariocontroller {

    @Autowired
    private Inventariorepository repositorio;

    @GetMapping
    @Operation(summary = "Obtener todos los juegos")
    public List<Inventariomodel> obtenerTodos() {
        return repositorio.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener juego por ID")
    public EntityModel<Inventariomodel> obtenerPorId(@PathVariable Long id) {
        Inventariomodel juego = repositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Juego no encontrado"));

        return EntityModel.of(juego,
                linkTo(methodOn(Inventariocontroller.class).obtenerPorId(id)).withSelfRel(),
                linkTo(methodOn(Inventariocontroller.class).obtenerTodos()).withRel("todos-los-juegos"),
                linkTo(Inventariocontroller.class).slash(id).withRel("eliminar-juego")
        );
    }

    @GetMapping("/buscar")
    @Operation(summary = "Buscar juegos por título")
    public List<Inventariomodel> buscarPorTitulo(@RequestParam String titulo) {
        return repositorio.findByTituloContainingIgnoreCase(titulo);
    }

    @PostMapping
    @Operation(summary = "Crear nuevo juego")
    public Inventariomodel crear(@RequestBody Inventariomodel juego) {
        return repositorio.save(juego);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar juego por ID")
    public Inventariomodel actualizar(@PathVariable Long id, @RequestBody Inventariomodel juego) {
        juego.setId(id);
        return repositorio.save(juego);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar juego por ID")
    public void eliminar(@PathVariable Long id) {
        repositorio.deleteById(id);
    }

    // Para testing si lo necesitas
    public void setRepositorio(Inventariorepository repositorio) {
        this.repositorio = repositorio;
    }
}
