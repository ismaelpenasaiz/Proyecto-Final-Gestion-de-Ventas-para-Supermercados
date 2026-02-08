🚀 Gestión de Ventas para Supermercados – API REST con Spring Boot
📌 Descripción

API RESTful desarrollada con Java + Spring Boot para digitalizar la gestión de ventas de una cadena de supermercados.

El sistema permite:

🛒 Gestión de productos

🏪 Gestión de sucursales

🧾 Registro de ventas con múltiples productos

📊 Estadísticas de ventas

🔐 Autenticación JWT

📄 Documentación Swagger

🧪 Testing unitario e integración

El proyecto aplica buenas prácticas de arquitectura, separación por capas, uso de DTOs, JPA, Streams, manejo global de excepciones y seguridad con JWT.

🏗️ Arquitectura del Proyecto
📂 Estructura principal
Organización por capas (Clean Architecture)
controller  → Endpoints REST
service     → Lógica de negocio
repository  → Acceso a datos (JPA)
entities    → Entidades JPA
dtos        → Objetos de transferencia
exceptions  → Manejo de errores
security    → JWT + Spring Security
config      → Swagger / configuración
tests       → Unitarios + Integración

Esta separación facilita:

✅ Mantenibilidad
✅ Testeo
✅ Escalabilidad
✅ Buenas prácticas REST

⚙️ Stack Tecnológico
Tecnología	Uso
Java 17+	Lenguaje principal
Spring Boot	Framework backend
Spring Web	API REST
Spring Data JPA	Persistencia
MySQL	Base de datos
Spring Security	Autenticación
JWT	Seguridad stateless
Swagger (springdoc-openapi)	Documentación
Maven	Build
JUnit + SpringBootTest	Testing
🗄️ Modelo de Datos
Entidades principales

Sucursal

Producto

Venta

VentaDetalle

Usuario

Relaciones
Sucursal 1 ──── * Venta
Venta    1 ──── * VentaDetalle
Producto 1 ──── * VentaDetalle
🔐 Seguridad

Implementación con JWT:

Funcionalidades

Registro de usuario

Login

Generación de token JWT

Filtro de autorización

Reglas

✅ GET → público
🔒 POST / PUT / DELETE → requiere token

Header:

Authorization: Bearer <token>
📄 Documentación API

Swagger disponible en:

http://localhost:8080/swagger-ui.html

o

http://localhost:8080/swagger-ui/index.html

Permite:

Probar endpoints

Ver DTOs

Ver respuestas HTTP

Autenticarse con JWT

🧩 Endpoints Implementados
📦 Productos
Método	Endpoint	Descripción
GET	/api/productos	Listar productos
POST	/api/productos	Crear
PUT	/api/productos/{id}	Actualizar
DELETE	/api/productos/{id}	Eliminar
🏬 Sucursales
Método	Endpoint
GET	/api/sucursales
POST	/api/sucursales
PUT	/api/sucursales/{id}
DELETE	/api/sucursales/{id}
💰 Ventas
Registrar venta

POST /api/ventas

{
"sucursalId": 1,
"detalle": [
{ "productoId": 10, "cantidad": 2 },
{ "productoId": 5, "cantidad": 1 }
]
}
Consultar ventas

GET /api/ventas?sucursalId=1&fecha=2025-06-01

Eliminar / anular

DELETE /api/ventas/{id}

📊 Estadísticas
Producto más vendido

GET /api/estadisticas/producto-mas-vendido

Implementado usando Streams y programación funcional.

Ejemplo:

ventas.stream()
.flatMap(v -> v.getDetalles().stream())
.collect(Collectors.groupingBy(...))
🧠 Buenas Prácticas Aplicadas

✅ DTOs para desacoplar entidades
✅ ResponseEntity + códigos HTTP
✅ Validaciones
✅ Manejo global de errores
✅ Arquitectura por capas
✅ Streams / Lambdas
✅ Soft delete en ventas
✅ Seguridad JWT
✅ Swagger

⚠️ Manejo de Errores Global

Implementado con:

@RestControllerAdvice

Excepciones:

ProductoNotFoundException

SucursalNotFoundException

VentaNotFoundException

Ejemplo respuesta:

{
"error": "Producto no encontrado",
"status": 404
}
🧪 Testing
✅ Unit Test

ProductoServiceTest

Prueba lógica de negocio aislada.

✅ Integración

VentaIntegrationTest

Características:

@SpringBootTest

@Transactional

Rollback automático

No modifica la BD real

▶️ Cómo ejecutar el proyecto
1️⃣ Clonar
git clone <repo>
cd proyecto
2️⃣ Configurar MySQL

Crear BD:

CREATE DATABASE supermercado_db;

Editar application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/supermercado_db
spring.datasource.username=root
spring.datasource.password=1234
3️⃣ Ejecutar
mvn spring-boot:run

o desde el IDE.

📬 Postman

Incluye colección con:

✅ Todos los endpoints
✅ Auth JWT
✅ Casos de prueba

📈 Funcionalidades Extra Implementadas

✔ Autenticación JWT
✔ Swagger
✔ Estadísticas con Streams
✔ Soft delete ventas
✔ Tests
✔ Arquitectura limpia
✔ Manejo global de excepciones

🎯 Objetivos cumplidos de la prueba
Requisito	Estado
CRUD JPA	✅
Relaciones	✅
DTOs	✅
Swagger	✅
JWT	✅
Excepciones globales	✅
Streams	✅
Test unitario	✅
Test integración	✅
Arquitectura modular	✅

👨‍💻 Autores

Ismael Peña
Sebastian Riveros
Leonardo de Oliveira
Sergio Gago

Proyecto realizado como prueba técnica.