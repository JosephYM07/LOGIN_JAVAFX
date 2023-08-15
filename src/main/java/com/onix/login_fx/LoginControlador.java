package com.onix.login_fx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;


public class LoginControlador {

    @FXML
    private TextField correoTextfield;
    @FXML
    private PasswordField contrasenia;

    @FXML
    private void iniciarSesion() {
        String correo = correoTextfield.getText();
        String contraseña = contrasenia.getText();

        if (verificarCredenciales(correo, contraseña)) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("informacion.fxml"));
                AnchorPane root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setTitle("Información del Usuario");
                stage.setScene(scene);

                Stage currentStage = (Stage) correoTextfield.getScene().getWindow();
                currentStage.close();

                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            mostrarAlerta("Credenciales incorrectas", "Por favor, verifica tus credenciales", Alert.AlertType.ERROR);
        }
    }
    private boolean verificarCredenciales(String correo, String contraseña) {
        String url = "jdbc:mysql://localhost:3306/usuarios";
        String usuarioDB = "root";
        String contraseñaDB = "Vodafone2002.";

        try (Connection connection = DriverManager.getConnection(url, usuarioDB, contraseñaDB)) {
            String sql = "SELECT * FROM usuarios WHERE correo = ? AND contraseña = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, correo);
                statement.setString(2, contraseña);
                try (ResultSet resultSet = statement.executeQuery()) {
                    return resultSet.next();
                }
            }
        } catch (SQLException e) {
            System.out.println("ERROR AL CONECTAR CON LA BASE DE DATOS");
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
