# JavaFX con Base de Datos y TableView

## Descripción

Este proyecto consiste en desarrollar una aplicación en **JavaFX** que se conecta a una **base de datos** y muestra los registros de una tabla en un **TableView**.

El objetivo del ejercicio es aprender a integrar JavaFX con bases de datos y visualizar información de forma estructurada en una interfaz gráfica.

## Objetivos

- Conectar una aplicación JavaFX a una base de datos.
- Realizar consultas SQL para obtener datos.
- Mostrar los datos en un `TableView`.
- Practicar el uso de JDBC.
- Integrar interfaz gráfica con persistencia de datos.
## Funcionamiento

1. Se inicia la aplicación.
2. Se establece conexión con la base de datos.
3. Se realiza una consulta SQL a una tabla.
4. Los datos obtenidos se cargan en un `TableView`.
5. El usuario puede visualizar la información en pantalla.
## Ejemplo de interfaz

```text
+--------------------------------------+
| ID | Nombre | Email | Teléfono      |
|----|--------|-------|--------------|
| 1  | Ana    | ...   | 123456789    |
| 2  | Luis   | ...   | 987654321    |
| 3  | Marta  | ...   | 555555555    |
+--------------------------------------+
```

## Ejemplo de consulta SQL

```sql
SELECT * FROM usuarios;
```
