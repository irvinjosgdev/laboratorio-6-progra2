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
por capas: `model`, `repository`, `service`, `controller` y `exception`. Los datos se
almacenan en listas en memoria y se precargan algunos registros de ejemplo al iniciar
cada aplicacion.

## Ejercicios de diseno (1, 2 y 3)

Cada carpeta contiene un archivo `.yaml` con la especificacion OpenAPI 3.0.3 del API
correspondiente (rutas, esquemas de request/response y codigos de estado HTTP). Puede
visualizarse cargando el archivo en https://editor.swagger.io o en la extension de
Swagger Viewer de un editor de codigo.

## Como ejecutar cada API (ejercicios 4, 5 y 6)

Desde la carpeta del ejercicio correspondiente:

```bash
mvn spring-boot:run
```

La API queda disponible en `http://localhost:8080`.

### Ejercicio 4 - API de libros (`/api/libros`)

| Metodo | Ruta                      | Descripcion              | Codigos de respuesta |
|--------|---------------------------|---------------------------|-----------------------|
| GET    | /api/libros               | Consultar libros           | 200 |
| GET    | /api/libros/titulo/{titulo} | Consultar libro por titulo | 200, 404 |
| POST   | /api/libros               | Registrar libro             | 201, 400 |
| PUT    | /api/libros/{id}          | Actualizar libro            | 200, 400, 404 |
| DELETE | /api/libros/{id}          | Eliminar libro              | 204, 404 |

Ejemplo de registro:

```bash
curl -X POST http://localhost:8080/api/libros \
  -H "Content-Type: application/json" \
  -d '{"titulo":"Don Quijote de la Mancha","autor":"Miguel de Cervantes","isbn":"978-8420412146","anioPublicacion":1605,"estado":"DISPONIBLE"}'
```

### Ejercicio 5 - API de cursos (`/api/cursos`)

| Metodo | Ruta                      | Descripcion               | Codigos de respuesta |
|--------|---------------------------|----------------------------|-----------------------|
| GET    | /api/cursos               | Consultar cursos            | 200 |
| GET    | /api/cursos/codigo/{codigo} | Consultar curso por codigo | 200, 404 |
| POST   | /api/cursos               | Crear curso                  | 201, 400 |
| PUT    | /api/cursos/{id}          | Actualizar curso             | 200, 400, 404 |
| DELETE | /api/cursos/{id}          | Eliminar curso               | 204, 404 |

Ejemplo de creacion:

```bash
curl -X POST http://localhost:8080/api/cursos \
  -H "Content-Type: application/json" \
  -d '{"nombre":"Programacion 2","codigo":"CC-220","creditos":4,"estado":"ACTIVO"}'
```

### Ejercicio 6 - API de reservas (`/api/reservas`)

| Metodo | Ruta                        | Descripcion               | Codigos de respuesta |
|--------|-----------------------------|-----------------------------|-----------------------|
| GET    | /api/reservas               | Consultar reservas           | 200 |
| GET    | /api/reservas/{id}          | Consultar reserva por id     | 200, 404 |
| POST   | /api/reservas               | Crear reserva                 | 201, 400 |
| PUT    | /api/reservas/{id}          | Actualizar reserva            | 200, 400, 404 |
| PATCH  | /api/reservas/{id}/cancelar | Cancelar reserva              | 200, 404, 409 |

Una reserva se cancela cambiando su estado a `CANCELADA` en lugar de eliminar el
registro. Si ya estaba cancelada, el endpoint responde `409 Conflict`. Ademas, tanto al
crear como al actualizar una reserva se valida que `fechaSalida` sea posterior a
`fechaEntrada`, devolviendo `400 Bad Request` en caso contrario.

Ejemplo de creacion y cancelacion:

```bash
curl -X POST http://localhost:8080/api/reservas \
  -H "Content-Type: application/json" \
  -d '{"nombreCliente":"Jorge Ramirez","habitacion":"412","fechaEntrada":"2026-11-01","fechaSalida":"2026-11-05","estado":"PENDIENTE"}'

curl -X PATCH http://localhost:8080/api/reservas/1/cancelar
```
