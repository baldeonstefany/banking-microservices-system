# Account Service — segundo microservicio bancario (educativo)

Este proyecto se inició reutilizando conceptos de `spring-boot-crud-api` (CuentaController, CuentaEntity, CuentaServiceImpl), pero **NO es una copia directa**: se extrajo el dominio de cuentas para formar una aplicación Spring Boot **independiente**.

## Arquitectura y limitaciones
- Java 21, Spring Boot 3.5.7, Spring MVC, JPA, MySQL.
- Puerto local: `8082`; base de datos independiente: `db_account_service` (MySQL en 3308).
- Cuentas identifican a sus clientes por `clienteId`, no por una relación JPA entre servicios.
- **El clienteId todavía no se verifica con la API de clientes**. Este es el siguiente objetivo de integración. No usar datos reales.
- Sin autenticación ni operaciones de transferencias. **No es apto para producción.**

## Endpoints
- `POST /api/v1/cuentas` — crear cuenta, HTTP 201.
- `GET /api/v1/cuentas/{id}` — buscar cuenta, HTTP 200 o 404.

Ejemplo de cuerpo POST:

```json
{
  "clienteId": 1,
  "numeroCuenta": "DEMO-CUENTA-001",
  "saldoInicial": 125.50,
  "moneda": "PEN"
}
```

El POST valida clienteId positivo, número de cuenta, saldo no negativo y moneda PEN/USD. Número de cuenta duplicado produce HTTP 409.

## Cómo ejecutar
1. Abre `microservices/ms-account-service` en IntelliJ con JDK 21.
2. Copia `.env.example` a `.env` **dentro de esta carpeta**. Cambia las contraseñas y no subas `.env`.
3. Abre una terminal en esta carpeta y ejecuta `docker compose up -d` para iniciar MySQL.
4. En la configuración de ejecución de IntelliJ, define `DB_PASSWORD` con el mismo valor del archivo `.env`.
5. Ejecuta `AccountServiceApplication` desde IntelliJ, o usa Maven instalado: `mvn spring-boot:run`.
6. Prueba `POST http://localhost:8082/api/v1/cuentas` en Postman.

## Pruebas automáticas
Con Maven instalado: `mvn test`. Las pruebas utilizan H2 en memoria; no requieren Docker.

**Pendiente de validación:** el código se publicó en GitHub pero las pruebas aún no se ejecutaron en la computadora de la autora. No fusionar el Pull Request hasta comprobarlo.

## Lo siguiente
Concluir el CRUD de clientes en `ms-client-service` y conectar cuentas -> clientes mediante una llamada HTTP, con timeouts y manejo de errores. Después incorporar pruebas, Swagger, seguridad y documentación del sistema.
