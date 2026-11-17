# Banking Microservices System — proyecto educativo

Sistema backend **en desarrollo** orientado al dominio bancario, construido para practicar Java 21, Spring Boot, HTTP, APIs REST, bases de datos, Git, Docker y pruebas. **No es un sistema bancario real ni una aplicacion de produccion.**

## Estado actual (primera practica)
- Microservicio: `microservices/ms-client-service` (Spring Boot 3.5.7, Java 21).
- Endpoint inicial: `GET http://localhost:8081/api/v1/clientes/estado`.
- Ejemplo de respuesta: `{"servicio":"client-service","estado":"OPERATIVO"}`.
- Prueba HTTP con MockMvc y prueba de contexto, que deben ejecutarse localmente.
- Base de datos MySQL local mediante Docker Compose; las credenciales se configuran fuera de Git.
- **Pendiente:** modelo Cliente, CRUD, persistencia, validaciones, seguridad, pruebas de integración y más microservicios.

## Arquitectura inicial

```text
banking-microservices-system/
├── docker-compose.yml
├── .env.example
└── microservices/
    └── ms-client-service/
        ├── pom.xml
        └── src/main/java/com/bank/client/
            ├── ClientServiceApplication.java
            ├── controller/ClienteController.java
            └── dto/EstadoServicioResponse.java
```

Flujo: consumidor HTTP (Postman) -> Controller -> DTO JSON. **Todavia no implementa Service/Repository**; agregaremos esas capas al incorporar logica de negocio y persistencia.

## Ejecutar en tu PC
Requisitos: JDK 21, Docker Desktop, Git e IntelliJ IDEA (o Maven).

1. Clona el repositorio y cambia a la rama de trabajo:
   ```bash
   git clone https://github.com/baldeonstefany/banking-microservices-system.git
   cd banking-microservices-system
   git switch feature/client-service-primer-endpoint
   ```
2. Copia `.env.example` a `.env` y cambia las contrasenas de ejemplo. No subas `.env` a GitHub.
3. Ejecuta `docker compose up -d` desde la raiz para iniciar MySQL. Asegurate de que el puerto 3307 este disponible.
4. Configura en IntelliJ la variable de entorno `DB_PASSWORD` con el mismo valor de tu `.env`. Para ejecutar pruebas unitarias no necesitas levantar MySQL.
5. Abre la carpeta `microservices/ms-client-service` como proyecto Maven en IntelliJ. Ejecuta `ClientServiceApplication`.
6. En Postman: metodo GET, URL `http://localhost:8081/api/v1/clientes/estado`. Espera HTTP 200 con el JSON indicado.

## Pruebas
Desde `microservices/ms-client-service`: `./mvnw test` (macOS/Linux) o `mvnw.cmd test` (Windows). Verifica resultados antes de reportar el proyecto como funcional.

## Ruta de evolucion
1. API cliente con DTO, Service, Repository y MySQL.
2. Validaciones y excepciones HTTP 400/404.
3. JUnit/Mockito, pruebas de integracion y Swagger.
4. Dockerfile y pipeline CI/CD.
5. Seguridad y modulo de solicitudes de adelanto de sueldo con reglas **ficticias**.

## Flujo Git
Trabaja en `feature/*`, realiza commits descriptivos y abre un pull request hacia `main`. Cada PR debe explicar que se hizo, por que, y como se probo. No subas contrasenas, tokens, datos de clientes ni informacion bancaria real.
