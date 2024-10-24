# Proyecto de Administración de Tareas - Laboratorio 5 (CI/CD)

## Descripción General

Este proyecto es una aplicación de gestión de tareas que incorpora un flujo de trabajo CI/CD utilizando GitHub Actions para la integración continua y despliegue automatizado en Azure App Service. Además, se agregan funcionalidades para el análisis de datos de las tareas mediante gráficos interactivos generados con JavaScript.

## Contenidos

1. [Objetivos](#objetivos)
2. [Requisitos](#requisitos)
3. [Parte I: CI/CD](#parte-i-ci-cd)
   - [Pipeline de GitHub Actions](#pipeline-de-github-actions)
   - [Pruebas Unitarias](#pruebas-unitarias)
   - [Despliegue en Azure](#despliegue-en-azure)
4. [Parte II: Gráficos](#parte-ii-gráficos)
   - [Generación Procedural de Datos](#generación-procedural-de-datos)
   - [Visualizaciones](#visualizaciones)
5. [Entrega Final](#entrega-final)
6. [Conclusiones](#conclusiones)

## Objetivos

- Crear un pipeline de CI/CD en GitHub Actions con trabajos de build, test y deploy.
- Ejecutar pruebas unitarias para asegurar la correcta funcionalidad de la aplicación.
- Desplegar la aplicación en Azure App Service.
- Generar gráficos interactivos para el análisis de datos relacionados con las tareas gestionadas.

## Requisitos

- **Java OpenJDK 17**
- **Apache Maven**
- **Spring Boot**
- **Docker**
- **Azure App Service**
- **MySQL**
- **GitHub Actions**
- **Bibliotecas de Gráficos (JavaScript)**: D3.js, Chart.js, Google Charts.

## Parte I: CI/CD

### Pipeline de GitHub Actions

Se configuró un workflow en GitHub Actions con tres trabajos:

1. **Build**: Ejecuta la compilación (`mvn compile`) del proyecto.
2. **Test**: Ejecuta las pruebas unitarias (`mvn verify`). Este trabajo depende de la compilación exitosa.
3. **Deploy**: Despliega la aplicación en Azure App Service usando `azure/webapps-deploy@v2`. Este trabajo depende de la ejecución exitosa de las pruebas.

### Pruebas Unitarias

Se añadieron pruebas para validar el correcto funcionamiento del servicio de tareas. Entre las pruebas implementadas se encuentran:
- Consultar tareas registradas.
- Crear y eliminar tareas.
- Validar las respuestas del servicio en diferentes estados.

### Despliegue en Azure

Se desplegó la aplicación en **Azure App Service**, configurando las variables de entorno y la base de datos MySQL para su correcto funcionamiento. Se verificó que el endpoint estuviera disponible y se solucionaron problemas relacionados con la configuración del puerto y la base de datos.

## Parte II: Gráficos

### Generación Procedural de Datos

Se añadieron nuevos atributos a las tareas (nivel de dificultad y prioridad) y se generaron de manera procedural entre 100 y 1000 tareas. Estos datos se utilizan para alimentar gráficos de análisis.

### Visualizaciones

Se desarrolló una nueva página "Analítica" con varias gráficas que muestran los siguientes datos:
- **Histograma de Dificultad**: Distribución de tareas por nivel de dificultad.
- **Número de Tareas Finalizadas por Tiempo**: Gráfica de líneas mostrando el progreso de tareas completadas.
- **Promedios de Tareas por Prioridad**: Representación gráfica del promedio de tareas según su prioridad.
- **Tiempo Total Invertido por Tareas Realizadas**: Gráfica que muestra el tiempo acumulado invertido en tareas completadas.

## Entrega Final

- **URL de la aplicación desplegada en Azure**: [App en Azure](#) (enlace al despliegue).
- **Repositorio GitHub con CI/CD configurado**: [Repositorio GitHub](#) (enlace al repositorio).
- **Plan de tareas en Azure DevOps**: Actualización del plan con las nuevas tareas y ajustes.

## Conclusiones

Este laboratorio permitió integrar un flujo CI/CD completo, automatizando el desarrollo, pruebas y despliegue de la aplicación en la nube. Además, se añadieron funcionalidades para la generación y visualización de datos, proporcionando una visión más detallada sobre la gestión de tareas dentro del sistema.
