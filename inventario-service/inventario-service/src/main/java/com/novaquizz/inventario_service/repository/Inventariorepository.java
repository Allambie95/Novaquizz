package com.novaquizz.inventario_service.repository;

import com.novaquizz.inventario_service.model.Inventariomodel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface Inventariorepository extends JpaRepository<Inventariomodel, Long> {
    List<Inventariomodel> findByTituloContainingIgnoreCase(String titulo);
}
