package com.futinfor.controller;

import com.futinfor.util.Sessao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class MainController {

    @FXML private Button btnUsuarios;
    @FXML private Button btnNoticias;
    @FXML private Button btnTimes;
    @FXML private Button btnPartidas;

    @FXML private AnchorPane conteudoArea;
    @FXML private Label lblUsuario;

    @FXML
    public void initialize() {
        if (Sessao.getUsuario() != null && lblUsuario != null) {
            lblUsuario.setText("Logado como: " + Sessao.getUsuario().getNome() +
                               " (" + Sessao.getUsuario().getTipo() + ")");
        }

        if (Sessao.isLeitor()) {
            btnNoticias.setDisable(true);
            btnUsuarios.setDisable(true);
        }

        if (Sessao.isJornalista()) {
            btnUsuarios.setDisable(true);
        }
    }

   @FXML
public void abrirNoticias() {
    carregarTela("/fxml/NoticiaView.fxml");
}

@FXML
public void abrirUsuarios() {
    carregarTela("/fxml/UsuarioView.fxml");
}

@FXML
public void abrirTimes() {
    carregarTela("/fxml/TimeView.fxml");
}

@FXML
public void abrirPartidas() {
    carregarTela("/fxml/PartidaView.fxml");
}

private void carregarTela(String caminhoFxml) {
    try {
        Parent novaTela = FXMLLoader.load(getClass().getResource(caminhoFxml));
        
        conteudoArea.getChildren().clear();
        conteudoArea.getChildren().add(novaTela);

        AnchorPane.setTopAnchor(novaTela, 0.0);
        AnchorPane.setBottomAnchor(novaTela, 0.0);
        AnchorPane.setLeftAnchor(novaTela, 0.0);
        AnchorPane.setRightAnchor(novaTela, 0.0);

        System.out.println("Carregado com sucesso: " + caminhoFxml);
        
    } catch (Exception e) {
        e.printStackTrace();
        new Alert(Alert.AlertType.ERROR, "Erro ao carregar: " + e.getMessage()).showAndWait();
    }
}
}