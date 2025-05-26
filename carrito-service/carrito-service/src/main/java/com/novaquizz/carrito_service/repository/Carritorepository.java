package com.novaquizz.carrito_service.repository;

import com.novaquizz.carrito_service.model.Carritomodel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Carritorepository extends JpaRepository<Carritomodel, Long> {
    List<Carritomodel> findByUsuarioId(Long usuarioId);
}