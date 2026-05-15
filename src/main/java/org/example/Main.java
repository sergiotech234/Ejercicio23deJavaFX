package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
public class Main extends Application {

    private TableView<Empleado> tableView;
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Ejemplo JDBC");
        tableView = new TableView<>();
        // Definir columnas
        TableColumn<Empleado, String> nombreCol = new
                TableColumn<>("Nombre");
        TableColumn<Empleado, Integer> salarioCol = new
                TableColumn<>("Salario");
        // Asignar las propiedades del modelo a las columnas
        nombreCol.setCellValueFactory(new
                PropertyValueFactory<>("nombre"));
        salarioCol.setCellValueFactory(new
                PropertyValueFactory<>("salario"));
        tableView.getColumns().addAll(nombreCol, salarioCol);
        VBox vbox = new VBox(tableView);
        Scene scene = new Scene(vbox, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
        cargarDatos();
    }
    private void cargarDatos() {
        String url = "jdbc:oracle:thin:@localhost:1521:xe"; // Cambia según tu configuración
        String user = "RIBERA"; // Cambia si es necesario
        String password = "ribera"; // Cambia si es necesario

        try (Connection conn = DriverManager.getConnection(url, user,
                password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT nombre, salario FROM empleado2")) {
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                int salario = rs.getInt("salario");
                tableView.getItems().add(new Empleado(nombre, salario));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
    public static class Empleado {
        private final String nombre;
        private final int salario;
        public Empleado(String nombre, int salario) {
            this.nombre = nombre;
            this.salario = salario;
        }
        public String getNombre() {
            return nombre;
        }
        public int getSalario() {
            return salario;
        }
    }
}
