# Consideraciones
Los mensajes del tipo {"mensaje": "mensaje de error"} fueron aplicados al metodo Post para guardar usuario, ya que eso es lo que estaba indicado en el documento.


#Comencemos!

Pasos para ejecutar el proyecto.

1. Clonar el repositorio en un directorio local.

git clone Https://nombrerepo.git

2. Ingresar al directorio usuarios-bci

cd /usuarios-bci

3. Ya adentro del directorio abrir consola y posicionarse en el directorio actual. Ej: C:\usuarios-bci
Ejecutar el siguiente comando de Maven: mvn spring-boot:run

La aplicacion se ejecutará en el puerto 8080, por lo que la url sera localhost:8080

Para verificar que la app se este ejecutando correctamente puede ir a su navegador y escribir la Url localhost:8080/usuarios la cual obtendra usuarios de ejemplo.

4. Ejecutar la aplicacion: se puede realizar mediante el comando curl desde una terminal o desde un cliente como Postman.

4.1 Mediante curl: Dentro del directorio /Diagramas del proyecto se dejara un archivo llamado data.json , ejecutar mediante linea de comandos.

cd /Diagramas

curl -d "@data.json" -H "Content-Type: application/json" -X POST http://localhost:8080/usuarios/

4.2 Mediante postman (recomendado)
Importar desde postman la coleccion usuarios.postman_collection.json generada para probar el proyecto.

En el directorio /Diagramas se encuentra la coleccion de postman para realizar las pruebas.
