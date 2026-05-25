package com.futinfor.controller;

import com.futinfor.model.dao.UsuarioDAO;
import com.futinfor.model.entity.Usuario;
import com.futinfor.util.Sessao;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField senhaField;

    @FXML
    private Label mensagemLabel;

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    @FXML
    private void handleLogin() {

        String email = emailField.getText();
        String senha = senhaField.getText();

        if (email.isEmpty() || senha.isEmpty()) {
            mensagemLabel.setText("Preencha todos os campos!");
            return;
        }

        Usuario usuario = usuarioDAO.autenticar(email, senha);

        if (usuario != null) {

            Sessao.setUsuario(usuario);

            abrirMainView();

        } else {
            mensagemLabel.setText("Email ou senha inválidos!");
        }
    }

    private void abrirMainView() {
        try {

            FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));

            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("FutInfo - Sistema");
            stage.setScene(new Scene(root));
            stage.show();

            Stage loginStage =
                    (Stage) emailField.getScene().getWindow();
            loginStage.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}