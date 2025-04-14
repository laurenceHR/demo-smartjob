# Demo Project

Este proyecto es una aplicación basada en **Spring Boot** que utiliza **Maven** como herramienta de construcción. A continuación, se describen los pasos para ejecutar el proyecto.

## Requisitos previos

Asegúrate de tener instalados los siguientes componentes en tu sistema:

- **Java 17** o superior
- **Maven 3.6.0** o superior
- Un IDE como **IntelliJ IDEA** (opcional)

## Configuración inicial

1. Clona el repositorio del proyecto:
   ```bash
   git clone <URL_DEL_REPOSITORIO>
   cd <NOMBRE_DEL_PROYECTO>
   ```
2. Construir el proyecto
   Usa Maven para compilar y construir el proyecto. Ejecuta el siguiente comando en la raíz del proyecto:
   ```bash
   mvn clean install
   ```
3. Ejecutar el proyecto
   Una vez construido, puedes ejecutar la aplicación con el siguiente comando:
   ```bash
   mvn spring-boot:run
   ```
4. Acceso a la aplicación
   Por defecto, la aplicación estará disponible en http://localhost:9091. Asegúrate de verificar la configuración del puerto en el archivo application.properties o application.yml si es necesario.
