package com.novaquizz.inventario_service.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "juegos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inventariomodel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String descripcion;

    private String autor;

    private String categoria;

    private String nivel;

    private Double precio;

    private Boolean disponible;
}