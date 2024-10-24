# Proyecto de Administración de Tareas - (CI/CD y Seguridad)

## Descripción General

Este proyecto de gestión de tareas implementa un pipeline CI/CD utilizando GitHub Actions para la integración continua y despliegue automatizado en Azure App Service. Además, en el Laboratorio 8, se añade seguridad con Spring Security y se aplican certificados SSL para proteger las comunicaciones HTTP de la aplicación.

## Contenidos

1. [Objetivos](#objetivos)
2. [Requisitos](#requisitos)
3. [Parte I: CI/CD (Laboratorio 5)](#parte-i-ci-cd-laboratorio-5)
   - [Pipeline de GitHub Actions](#pipeline-de-github-actions)
   - [Pruebas Unitarias](#pruebas-unitarias)
   - [Despliegue en Azure](#despliegue-en-azure)
4. [Parte II: Seguridad (Laboratorio 8)](#parte-ii-seguridad-laboratorio-8)
   - [Spring Security](#spring-security)
   - [Certificados SSL](#certificados-ssl)
5. [Entrega Final](#entrega-final)
6. [Conclusiones](#conclusiones)

## Objetivos

- **CI/CD**: Crear un pipeline automatizado para integración continua y despliegue utilizando GitHub Actions y Azure App Service.
- **Seguridad**: Implementar autenticación, autorización con roles y protección SSL en el proyecto de gestión de tareas.

## Requisitos

- **Java OpenJDK 17**
- **Apache Maven**
- **Spring Boot**
- **Docker**
- **Azure App Service**
- **MySQL**
- **MongoDB**
- **GitHub Actions**
- **Spring Security**

## Parte I: CI/CD 

### Pipeline de GitHub Actions

Se configuró un workflow en GitHub Actions con tres trabajos para automatizar el proceso de CI/CD:

1. **Build**: Ejecuta la compilación (`mvn compile`) del proyecto.
2. **Test**: Ejecuta pruebas unitarias (`mvn verify`). Este trabajo depende de una compilación exitosa.
3. **Deploy**: Despliega la aplicación en Azure App Service utilizando `azure/webapps-deploy@v2`, asegurando que el despliegue solo ocurra tras la finalización exitosa de los tests.

### Pruebas Unitarias

Se implementaron pruebas unitarias para asegurar la funcionalidad del servicio de tareas:
- Consulta de tareas registradas.
- Creación y eliminación de tareas.
- Validación del comportamiento del servicio en diferentes estados.

### Despliegue en Azure

Se configuró el despliegue automático en **Azure App Service**, ajustando el puerto de despliegue y configurando la conexión a la base de datos MySQL. Se verificó el correcto funcionamiento del endpoint público de la aplicación.

## Parte II: Seguridad 

### Spring Security

Se añadió autenticación y autorización a la aplicación utilizando Spring Security:
- **Autenticación**: Se protegió el inicio de sesión y la gestión de usuarios. Las credenciales se almacenan cifradas en la base de datos MongoDB.
- **Roles de usuario**: Se definieron dos roles:
  - **Usuario**: Acceso a la gestión de tareas.
  - **Administrador**: Acceso a paneles de control y gráficos analíticos.
  
Se ajustaron las vistas de la aplicación para restringir el acceso a determinadas funcionalidades según el rol del usuario.

### Certificados SSL

Para asegurar las comunicaciones, se implementó un certificado SSL autofirmado en la aplicación utilizando Spring Boot. Todas las peticiones HTTP ahora están protegidas con HTTPS, garantizando la seguridad de la transmisión de datos entre el cliente y el servidor.

## Entrega Final

- **CI/CD**:
  - **URL de la aplicación en Azure**: [App en Azure](#) (enlace al despliegue).
  - **Repositorio GitHub con CI/CD configurado**: [Repositorio GitHub](#) (enlace al repositorio).
- **Seguridad**:
  - Se aplicaron las medidas de seguridad necesarias, asegurando la autenticación y protección de la comunicación.
  - **Plan de tareas en Azure DevOps**: Actualizado con las nuevas tareas y ajustes necesarios.

## Conclusiones

El laboratorio 5 permitió la automatización del desarrollo y despliegue mediante un pipeline CI/CD completo, mejorando la eficiencia del flujo de trabajo de integración y entrega. En el laboratorio 8, se reforzó la seguridad del sistema mediante la implementación de autenticación con Spring Security y la protección de las comunicaciones con certificados SSL. Estas mejoras garantizan tanto la disponibilidad como la seguridad de la aplicación desplegada.
