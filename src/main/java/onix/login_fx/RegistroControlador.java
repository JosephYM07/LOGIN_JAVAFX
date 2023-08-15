package onix.login_fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistroControlador {

    @FXML
    private TextField ingresonombres;
    @FXML
    private TextField ingresocorreo;
    @FXML
    private PasswordField ingresocontraseña;

    @FXML
    private void regresarInicio() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("inicio.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Inicio");
            stage.setScene(scene);

            // Cerrar la ventana actual
            Stage currentStage = (Stage) ingresonombres.getScene().getWindow();
            currentStage.close();

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void registrarse() {
        String nombre = ingresonombres.getText();
        String correo = ingresocorreo.getText();
        String contraseña = ingresocontraseña.getText();

        // Realizar la inserción en la base de datos
        if (insertarUsuario(nombre, correo, contraseña)) {
            mostrarAlerta("Registro exitoso", "El usuario ha sido registrado exitosamente", Alert.AlertType.INFORMATION);
            regresarInicio(); // Regresar a la ventana de inicio después del registro exitoso
        } else {
            mostrarAlerta("Error", "Ha ocurrido un error al registrar el usuario", Alert.AlertType.ERROR);
        }
    }

    private boolean insertarUsuario(String nombre, String correo, String contraseña) {
        String url = "jdbc:mysql://localhost:3306/usuarios";
        String usuarioDB = "root";
        String contraseñaDB = "SoaD1725.";

        try (Connection connection = DriverManager.getConnection(url, usuarioDB, contraseñaDB)) {
            String sql = "INSERT INTO usuarios (nombre, correo, contraseña) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, nombre);
                statement.setString(2, correo);
                statement.setString(3, contraseña);
                int filasAfectadas = statement.executeUpdate();
                return filasAfectadas > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error al insertar el usuario");
            e.printStackTrace();
            return false;
        }
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

}
