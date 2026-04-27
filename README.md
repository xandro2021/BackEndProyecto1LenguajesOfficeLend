# BackEndProyecto1LenguajesOfficeLend

## 📌 Descripción

Proyecto backend desarrollado con **Spring Boot** y **MySQL** para la gestión de usuarios, equipos y préstamos.  
Incluye autenticación con **JWT**, control de roles (ADMIN/USER) y consumo desde un cliente web.

---

## 🚀 Instalación y ejecución

### Requisitos previos

- Java 17+
- Maven
- MySQL
- Docker (opcional, para despliegue con `docker-compose`)
- VS Code con extensiones de Java y Spring Boot

### Ejecución 

Ejecución con Spring Boot Dashboard (VS Code)
Instalar extensiones: Spring Boot Extension Pack, Spring Boot Dashboard, Maven for Java.

Abrir el proyecto en la raíz (donde está el pom.xml).

En la barra lateral, abrir Spring Boot Dashboard.

Seleccionar la aplicación y presionar Play ▶️.

Verificar en la consola:

Código Tomcat started on port(s): 8080
Started LenguajesApplication in X seconds

🗂️ Arquitectura del proyecto

Controller: manejo de endpoints REST.

Service: lógica de negocio.

Repository: acceso a datos con JPA.

Model: entidades Usuario, Equipo, Préstamo.

Frontend: cliente web con HTML, CSS, JS y Bootstrap.

📡 Endpoints principales

Usuarios

POST /api/usuarios/registro → Registro de usuario.

POST /api/usuarios/login → Login y generación de JWT.

GET /api/usuarios → Listado de usuarios (ADMIN).

Equipos
GET /api/equipos → Listar equipos.

POST /api/equipos → Crear equipo (ADMIN).

PUT /api/equipos/{id} → Actualizar equipo.

DELETE /api/equipos/{id} → Eliminar equipo.

Préstamos
GET /api/prestamos → Listar préstamos.

POST /api/prestamos → Crear préstamo.

PUT /api/prestamos/{id} → Actualizar estado (ADMIN).

DELETE /api/prestamos/{id} → Eliminar préstamo.

🧪 Pruebas
Se recomienda usar Postman para probar los endpoints.

🌐 Frontend (Cliente Web)
Base en HTML + CSS + JS.

Diseño con Bootstrap.

Pantallas implementadas:

Login y registro.

Catálogo de equipos.

Mis préstamos.

Vista Admin: gestión de equipos y aprobación de préstamos.

Manejo de JWT en localStorage.

🐳 Docker
Configuración de Dockerfile y docker-compose.yml para levantar:

Backend (Spring Boot).

Base de datos MySQL.

Ejecución:

bash
docker-compose up --build

📅 Cronograma de actividades

Semana 1: Configuración inicial, entidad Usuario, CRUD de equipos, frontend base.

Semana 2: Autenticación JWT, CRUD de préstamos, consumo API en frontend.

Semana 3: Protección de rutas, documentación endpoints, vista Admin.

Semana 4: Integración frontend-backend, Docker, pruebas completas y demo final.

📌 Estado actual

✅ Registro y login de usuarios
✅ CRUD de equipos y préstamos
✅ Seguridad con JWT y roles
✅ Frontend básico con consumo de API
⚙️ Pendiente: controlador de estadísticas por grado/categoría, pruebas finales y documentación extendida

👥 Equipo

Integrante 1: Seguridad + Usuarios

Integrante 2: Módulos CRUD

Integrante 3: Frontend ( Cliente Web)
