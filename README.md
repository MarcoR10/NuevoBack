Documentación Back

CONTENIDO:

Para comenzar, se trabajó con las dependencias de spring-boot-jpa, mongoDB, starter-web, starter-test.
![image](https://github.com/user-attachments/assets/5c3fe537-8854-4522-b3d8-b1c39fa46b6f)

Acto seguido, se configuran los espacios para el controlador, repositorio, servicio y modelo especificamente:

![image](https://github.com/user-attachments/assets/8c3fac59-9084-4348-abc7-7f55e3bb3cc5)

El controlador en nuestro caso se encargará de recibir solicitudes, manejar la logica del negocio, validar los datos y hacer la interacción de modelos, esto hará mas eficiente las solicitudes del usuario y la logica de la aplicación:
![image](https://github.com/user-attachments/assets/d8d1a2ce-381e-4c10-9438-d39dd7e6568c)

El modelo se encargará de la representación de los datos, la interacción de la base de datos, manejo de relaciones:
![image](https://github.com/user-attachments/assets/09783f45-5d36-4fdc-a8f4-a2a7ea3230b5)

El repositorio será la interfaz, el encapsulamiento y la facilidad para las pruebas:
![image](https://github.com/user-attachments/assets/17cb6061-01b6-4ddf-9ef5-87612444a468)

El servicio se encargará de la interaccion con repositorios, encapsulamiento y transacciones:
![image](https://github.com/user-attachments/assets/b87d4849-9f0c-439c-bc2e-5b12307e0bed)

Para la conexión relacionada a la base de datos tenemos las properties:
![image](https://github.com/user-attachments/assets/add2579d-8416-4a78-8901-ae4972928ead)

Se adjuntan pruebas de la base de datos:
![image](https://github.com/user-attachments/assets/b7e2bf8c-8bbd-418c-be97-f58f9c7b89cd)

![image](https://github.com/user-attachments/assets/aae3c1db-acf9-4d3d-9a93-e712e77ab8ec)

Integrantes:
Paula Natalia Paez Vega

Marco Antonio Alvarez Ramirez

Andres Felipe Rodriguez Chaparro

Santiago Guerra Penagos

Manuel Felipe Barrera Barrera
