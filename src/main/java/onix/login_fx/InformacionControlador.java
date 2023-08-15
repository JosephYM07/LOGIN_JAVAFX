package onix.login_fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.*;

public class InformacionControlador {
    @FXML
    private Label mostrarnombres;

    @FXML
    private TextField ingresonombres;

    @FXML
    private TextField ingresocorreo;

    @FXML
    private TextField ingresocontraseña;

    private String nombreUsuario;

    public void inicializarInformacion(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
        mostrarnombres.setText("Bienvenido, " + nombreUsuario);
    }

    @FXML
    private void actualizarDatos(ActionEvent event) {
        String nuevoNombre = ingresonombres.getText();
        String nuevoCorreo = ingresocorreo.getText();
        String nuevaContraseña = ingresocontraseña.getText();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/usuarios", "root", "SoaD1725.")) {
            String sql = "UPDATE usuarios SET nombre = ?, correo = ?, contraseña = ? WHERE nombre = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, nuevoNombre);
                statement.setString(2, nuevoCorreo);
                statement.setString(3, nuevaContraseña);
                statement.setString(4, nombreUsuario);
                statement.executeUpdate();
                mostrarnombres.setText("Bienvenido, " + nuevoNombre);
                mostrarAlerta("Datos actualizados con éxito", Alert.AlertType.INFORMATION);
            }
        } catch (SQLException e) {
            mostrarAlerta("Error al actualizar datos", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    @FXML
    private void cerrarSesion(ActionEvent event) {
        Stage currentStage = (Stage) mostrarnombres.getScene().getWindow();
        currentStage.close();
        abrirVentanaInicioSesion();
    }

    @FXML
    private void eliminarCuenta(ActionEvent event) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/usuarios", "root", "SoaD1725.")) {
            String sql = "DELETE FROM usuarios WHERE nombre = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, nombreUsuario);
                statement.executeUpdate();
                mostrarAlerta("Cuenta eliminada con éxito", Alert.AlertType.INFORMATION);
                abrirVentanaInicioSesion();
            }
        } catch (SQLException e) {
            mostrarAlerta("Error al eliminar cuenta", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    private void mostrarAlerta(String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void abrirVentanaInicioSesion() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InicioSesion.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
