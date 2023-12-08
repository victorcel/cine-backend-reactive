# Cine-Backend-Reactive
# API del Cine - Documentación del Desarrollador

Este repositorio contiene la implementación inicial del API para la aplicación móvil/web del cine, que proyecta exclusivamente películas de la saga Rápido y Furioso.

## Funcionalidades Soportadas

### 1. Actualizar Horario y Precios (Solo para Dueños del Cine)

**Endpoint:**


Permite a los dueños del cine actualizar el horario de las funciones y los precios para el catálogo de películas.

**Parámetros:**
- `horario`: Nuevo horario de funciones (formato: HH:mm).
- `precios`: Lista de nuevos precios por película.

### 2. Obtener Horarios de Películas

**Endpoint:**


Proporciona a los clientes (visitantes del cine) los horarios de las películas disponibles.

### 3. Obtener Detalles de Película

**Endpoint:**


Ofrece a los clientes detalles sobre una película específica, utilizando la API OMDB para obtener información adicional.

**Parámetros:**
- `id_pelicula`: Identificador único de la película.

### 4. Dejar Calificación a una Película

**Endpoint:**

Permite a los clientes dejar una calificación (de 1 a 5 estrellas) para una película en particular.

**Parámetros:**
- `id_pelicula`: Identificador único de la película.
- `calificacion`: Calificación de la película (1-5).

### 5. Otros Endpoints Sugeridos

Agregar cualquier otro endpoint que se considere útil para mejorar la experiencia del cliente.

## Capa de Persistencia

Se implementará una capa de persistencia para almacenar resultados mediante un sistema de base de datos (puede ser SQL, NoSQL, etc.). Los datos relevantes para almacenar incluyen información sobre horarios, precios, detalles de películas y calificaciones.

## Documentación API (OpenAPI/Swagger)

La documentación del API seguirá el estándar OpenAPI/Swagger y estará disponible en el siguiente enlace: [Enlace a la Documentación del API](url_de_la_documentacion).

---

Este README proporciona una visión general clara de las funcionalidades admitidas, la capa de persistencia y la referencia a la documentación del API. Asegúrate de actualizar y mantener este documento a medida que evoluciona el desarrollo del API.

