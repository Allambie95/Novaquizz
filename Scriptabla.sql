-- Eliminar la base de datos si ya existe (¡CUIDADO! esto borra todo su contenido)
DROP DATABASE IF EXISTS Novaquizz;

-- Crear nueva base de datos
CREATE DATABASE Novaquizz;
USE Novaquizz;

-- Tabla de usuarios
CREATE TABLE IF NOT EXISTS usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    correo VARCHAR(100) NOT NULL UNIQUE,
    contrasena VARCHAR(255) NOT NULL,
    rol ENUM('ALUMNO', 'INSTRUCTOR', 'COORDINADOR', 'ADMINISTRADOR') NOT NULL
);

-- Tabla de juegos (inventario)
CREATE TABLE IF NOT EXISTS juegos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    descripcion TEXT,
    autor VARCHAR(100),
    categoria VARCHAR(50),
    nivel VARCHAR(50),
    precio DECIMAL(10,2) NOT NULL,
    disponible BOOLEAN DEFAULT TRUE
);

-- Insertar 5 juegos de ejemplo
INSERT INTO juegos (titulo, descripcion, autor, categoria, nivel, precio, disponible) VALUES
('Trivia Matemática Básica', 'Juego educativo sobre operaciones matemáticas simples.', 'Profe Camila', 'Matemáticas', 'Básico', 1500.00, TRUE),
('Historia de Chile - Nivel Medio', 'Preguntas sobre historia nacional desde 1810.', 'EduHistoriador', 'Historia', 'Medio', 2000.00, TRUE),
('Ortografía al Límite', 'Identifica errores de ortografía en frases comunes.', 'Lengua Pro', 'Lenguaje', 'Avanzado', 1800.00, TRUE),
('Quizz de Ciencias Naturales', 'Juega aprendiendo sobre animales, plantas y el cuerpo humano.', 'BioJuegos', 'Ciencias', 'Básico', 1600.00, TRUE),
('Lógica y Pensamiento Crítico', 'Ejercicios interactivos de lógica y deducción.', 'SmartKids', 'Razonamiento', 'Medio', 2200.00, TRUE);

-- Tabla de carrito
CREATE TABLE IF NOT EXISTS carrito (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    usuario_id BIGINT NOT NULL,
    juego_id BIGINT NOT NULL,
    cantidad INT DEFAULT 1,
    fecha_agregado TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    FOREIGN KEY (juego_id) REFERENCES juegos(id)
);