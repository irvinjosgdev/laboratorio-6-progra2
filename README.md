# Laboratorio VI - Preparacion para Segundo Parcial

Programacion 2

## Objetivo

Reforzar los conocimientos del Modulo II mediante el diseno y desarrollo de APIs REST,
resolviendo ejercicios similares a los del segundo parcial.

## Estructura del repositorio

```
LABORATORIO 6/
├── Ejercicio01_DisenioApiLibros/      Diseno OpenAPI - API de libros de biblioteca
├── Ejercicio02_DisenioApiCursos/      Diseno OpenAPI - API de cursos universitarios
├── Ejercicio03_DisenioApiReservas/    Diseno OpenAPI - API de reservas de hotel
├── Ejercicio04_ApiLibros/             Desarrollo Spring Boot - API de libros
├── Ejercicio05_ApiCursos/             Desarrollo Spring Boot - API de cursos
└── Ejercicio06_ApiReservas/           Desarrollo Spring Boot - API de reservas
```

Cada ejercicio de desarrollo (4, 5 y 6) es un proyecto Maven independiente, organizado
por capas: `model`, `repository`, `service` y `controller`. Los datos se almacenan en
listas en memoria.

## Como ejecutar cada API

Desde la carpeta del ejercicio correspondiente (`Ejercicio04_ApiLibros`,
`Ejercicio05_ApiCursos` o `Ejercicio06_ApiReservas`):

```bash
mvn spring-boot:run
```

La API queda disponible en `http://localhost:8080`.
