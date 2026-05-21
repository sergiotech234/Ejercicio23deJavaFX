package org.example;

// Importación de clases JavaFX
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// Importación de clases JDBC para conectar con la base de datos
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

// Clase principal que hereda de Application
public class Main extends Application {

    // Tabla que mostrará los empleados
    private TableView<Empleado> tableView;

    // Método que inicia la interfaz gráfica
    @Override
    public void start(Stage primaryStage) {

        // Título de la ventana
        primaryStage.setTitle("Ejemplo JDBC");

        // Crear la tabla
        tableView = new TableView<>();

        // Crear columna para el nombre
        TableColumn<Empleado, String> nombreCol =
                new TableColumn<>("Nombre");

        // Crear columna para el salario
        TableColumn<Empleado, Integer> salarioCol =
                new TableColumn<>("Salario");

        // Asociar la columna con el atributo nombre
        // Debe coincidir con getNombre()
        nombreCol.setCellValueFactory(
                new PropertyValueFactory<>("nombre")
        );

        // Asociar la columna con el atributo salario
        // Debe coincidir con getSalario()
        salarioCol.setCellValueFactory(
                new PropertyValueFactory<>("salario")
        );

        // Añadir columnas a la tabla
        tableView.getColumns().addAll(
                nombreCol,
                salarioCol
        );

        // Crear VBox y añadir tabla
        VBox vbox = new VBox(tableView);

        // Crear escena
        Scene scene =
                new Scene(vbox, 400, 300);

        // Asignar escena
        primaryStage.setScene(scene);

        // Mostrar ventana
        primaryStage.show();

        // Cargar datos desde la base de datos
        cargarDatos();
    }

    // Método encargado de conectarse a la BD
    // y recuperar datos
    private void cargarDatos() {

        // URL de conexión Oracle
        String url =
                "jdbc:oracle:thin:@localhost:1521:xe";

        // Usuario de Oracle
        String user = "RIBERA";

        // Contraseña
        String password = "ribera";

        // try-with-resources:
        // cierra automáticamente conexión,
        // Statement y ResultSet
        try (

            // Crear conexión
            Connection conn =
                    DriverManager.getConnection(
                            url,
                            user,
                            password
                    );

            // Crear consulta SQL
            Statement stmt =
                    conn.createStatement();

            // Ejecutar consulta
            ResultSet rs =
                    stmt.executeQuery(
                    "SELECT nombre, salario FROM empleado2"
                    )

        ) {

            // Recorrer resultados
            while (rs.next()) {

                // Obtener nombre
                String nombre =
                        rs.getString("nombre");

                // Obtener salario
                int salario =
                        rs.getInt("salario");

                // Crear objeto empleado
                // y añadirlo a la tabla
                tableView.getItems().add(
                        new Empleado(
                                nombre,
                                salario
                        )
                );
            }

        } catch (Exception e) {

            // Mostrar errores en consola
            e.printStackTrace();
        }
    }

    // Método principal
    public static void main(String[] args) {
        launch(args);
    }

    // Clase modelo Empleado
    public static class Empleado {

        // Atributos
        private final String nombre;
        private final int salario;

        // Constructor
        public Empleado(
                String nombre,
                int salario
        ) {

            this.nombre = nombre;
            this.salario = salario;
        }

        // Getter del nombre
        public String getNombre() {
            return nombre;
        }

        // Getter salario
        public int getSalario() {
            return salario;
        }
    }
}
