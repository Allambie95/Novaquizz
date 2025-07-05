# NovaQuizz - Sistema de Gestión de Juegos Educativos

Este proyecto implementa una arquitectura de microservicios en Java usando Spring Boot. Está compuesto por tres servicios independientes:

- **usuarios-service**: gestiona usuarios y sus roles (Alumno, Instructor, Coordinador, Administrador).
- **inventario-service**: administra un catálogo de juegos educativos con información como título, autor, categoría y disponibilidad.
- **carrito-service**: permite a los usuarios agregar juegos a un carrito de compras.

## Tecnologías utilizadas

- Java 21
- Spring Boot 3.5.2
- Maven
- JPA/Hibernate
- Lombok
- Swagger/OpenAPI (para documentación de APIs)
- HATEOAS (para navegación de recursos)
- JUnit y Mockito (para pruebas unitarias) jupiter
- MySQL (entorno local con Laragon)

## Estructura del proyecto

Proyecto2/
├── usuarios-service/
├── inventario-service/
├── carrito-service/
├── Scriptabla.sql
└── .gitignore
