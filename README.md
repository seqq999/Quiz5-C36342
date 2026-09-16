# LogiExpress API

API REST para la gestion de paquetes y clientes de la empresa de logistica urbana LogiExpress.

## Arquitectura

La aplicacion sigue un modelo cliente-servidor desacoplado. Los clientes HTTP no acceden
directamente a la base de datos: se comunican con la API REST, que coordina las validaciones,
la logica de negocio y la persistencia.

```text
Cliente web o movil
	|
	v
API REST - Spring Boot
	|
	v
Controller -> Service -> Repository -> Base de datos
```

Las responsabilidades estan separadas por capas:

- `domain`: entidades y enumeraciones del modelo.
- `data`: repositorios Spring Data JPA.
- `business`: reglas de negocio y transacciones.
- `controller`: endpoints REST y DTOs para la comunicacion HTTP.
- `config`: configuraciones tecnicas de la aplicacion.

Este desacoplamiento permite agregar en el futuro un cliente SPA web o una aplicacion movil
sin modificar la logica interna del servidor. Ambos clientes pueden consumir los mismos
recursos REST mediante solicitudes HTTP y recibir respuestas JSON.

## API de paquetes

Ruta base: `/api/v1/paquetes`

| Metodo | Ruta | Descripcion |
| --- | --- | --- |
| `POST` | `/api/v1/paquetes` | Registra un paquete usando `PaqueteRequestDto`. |
| `GET` | `/api/v1/paquetes/obtener` | Consulta los paquetes registrados. |

## Ejecucion

Requisitos: JDK 25 y Maven Wrapper.

En Windows:

```powershell
./mvnw.cmd spring-boot:run
```

Para ejecutar las pruebas:

```powershell
./mvnw.cmd test
```
