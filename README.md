# Consideraciones
Los mensajes del tipo {"mensaje": "mensaje de error"} fueron aplicados al metodo Post para guardar usuario, ya que eso es lo que estaba indicado en el documento.


# Comencemos!

*Pasos para ejecutar el proyecto.*

1. Clonar el repositorio en un directorio local.

git clone https://github.com/hhidalgo90/usuarios-bci.git

2. Ingresar al directorio usuarios-bci por consola.

cd /usuarios-bci

3. Ya adentro del directorio abrir consola y posicionarse en el directorio actual. Ej: C:\usuarios-bci
Ejecutar el siguiente comando de Maven: mvn spring-boot:run

La aplicacion se ejecutará en el puerto 8080, por lo que la url sera localhost:8080

# 1 Probar la aplicación
1.1 En el directorio /Diagramas se encuentra la coleccion de postman para realizar las pruebas.
1.2 Importar desde Postman la coleccion usuarios.postman_collection.json generada para probar el proyecto.


# 2 Autenticación
2.1 Esta aplicacion tiene implementado JWT, por lo que el usuario debe loguarse para obtener su token de acceso y asi acceder a los recursos de la API.

2.2 Una vez importada la coleccion en Postman acceder al recurso Loguear del tipo POST http://localhost:8080/oauth/token

El request tiene por defecto al usuario "admin", el cual tiene los permisos para acceder a la API.

**Por defecto vienen dos usuarios configurados: admin (ROLE_ADMIN) y andres (ROLE_USER). Si quiere probar el comportamiento de la app con un usuario que no tenga permisos use el usuario "andres" con la misma password(123456).


El servicio respondera con el token de acceso que se debe usar para acceder a la API.

Ejemplo: "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpbmZvX2FkaWNpb25hbCI6IkJpZW52ZW5pZG86IGFkbWluIiwidXNlcl9uYW1lIjoiYWRtaW4iLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXSwiYXBlbGxpZG8iOiJFc3Bpbm96YSIsImV4cCI6MTY4OTk2ODM1Nywibm9tYnJlIjoiQW5kcmVzIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9BRE1JTiIsIlJPTEVfVVNFUiJdLCJqdGkiOiIxMmYzZjExOS1lNDk2LTQ0MWYtYmI1Yy1kOWNkNTFiOGNlNjMiLCJlbWFpbCI6Inp6enp6ekBob3RtYWlsLmVzIiwiY2xpZW50X2lkIjoiY2xpZW50ZXMtYmNpLWFwcCJ9.rmQB93Im6IRpisV7PodVVJ9YpvvwLTsHXQBFju9aKXY"

# 3 Consumir la API

3.1 Mediante postman dirigase al recurso *saveUsuario* del tipo POST.
3.2 En la pestana Authorization seleccione type Bearer Token y pegue el access_token generado.

Ya esta interactuando con la API.

# Swagger!

Para ver documentacion del proyecto mediante Swagger ir a la siguiente URL.

http://localhost:8080/swagger-ui/#/usuario-controller