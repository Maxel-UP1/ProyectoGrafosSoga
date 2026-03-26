# PathFinder Sogamoso
Proyecto : Samuel González
Este proyecto permite la visualización y registro de rutas para paqueteria mediante una interfaz desarrollada en JavaFX, integrando herramientas externas para la gestión de ubicaciones geográficas.

---

## Compilación y Ejecución

Para ejecutar la aplicación, sigue los siguientes pasos:

1. Compilar el archivo principal:

   `javac LoginView.java`

2. Ejecutar la aplicación:

   `java LoginView`

---

## Acceso al Sistema

El sistema cuenta con dos roles principales: **Administrador** y **Usuario**, cada uno con funcionalidades específicas.

### Administrador

Credenciales de acceso:

- **Usuario:** `admin`  
- **Contraseña:** `admin`

#### Funcionalidades:

- Visualización de rutas registradas en el sistema.
- Acceso a la representación gráfica de rutas en el navegador.

#### Procedimiento:

1. Iniciar sesión con las credenciales de administrador.
2. Seleccionar la opción **"Ver ruta"** de cualquier paquete.
3. El sistema abrirá automáticamente el navegador con la siguiente dirección:

   `http://localhost:63342/ProyectoGrafosSoga/src/main/java/persistence/map.html`

4. En esta vista se presenta el mapa con las rutas calculada.

   

---

###  Nueva Ruta Cliente

Credenciales de acceso:

- **Usuario:** `sam12`  
- **Contraseña:** `123`

#### Funcionalidades:

- Registro de nuevas rutas mediante coordenadas personalizadas.

#### Procedimiento:

1. Cerrar la sesión actual e iniciar sesión como cliente.
2. Acceder a la opción **"Dirección de entrega"**.
3. Seleccionar el modo **"Personalizado"**.
4. Utilizar la herramienta externa para generación de coordenadas:

   https://geojson.io/#new&map=13.31/5.71738/-72.93601

5. En la plataforma:
   - Agregar un punto en el mapa.
   - Copiar el contenido completo del archivo **GeoJSON** generado.

6. Regresar a la aplicación:
   - Pegar el contenido en el campo correspondiente.
   - Asignar un nombre a la ruta.
   - Confirmar la acción mediante el botón **"Realizar pedido"**.

---

##  Verificación de Resultados

Para validar el registro de una nueva ruta:

1. Iniciar sesión nuevamente como administrador.
2. Acceder a la opción **"Ver ruta"**.
3. Confirmar que la nueva ruta se visualiza correctamente en el mapa.

---

## ⚠️ Consideraciones Técnicas

- Se recomienda el uso de **Google Chrome** para la correcta visualización del mapa.
- Es necesario que la ruta del archivo `map.html` sea válida dentro del entorno local.
- El correcto funcionamiento depende de la integración con herramientas de visualización GeoJSON.

---
