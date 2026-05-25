package com.futinfor.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.futinfor.model.dao.NoticiaDAO;
import com.futinfor.model.entity.Noticia;
import com.futinfor.util.Sessao;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class NoticiaController {

    @FXML
    private TextField txtTitulo;

    @FXML
    private TextArea txtConteudo;

    @FXML
    private TableView<Noticia> tabelaNoticias;

    @FXML
    private TableColumn<Noticia, String> colTitulo;

    @FXML
    private TableColumn<Noticia, String> colAutor;

    @FXML 
    private TableColumn<Noticia, String> colConteudo;

    @FXML 
    private TableColumn<Noticia, LocalDateTime> colDataPublicacao;

    @FXML
    public void initialize() {

        colTitulo.setCellValueFactory(
                new PropertyValueFactory<>("titulo")
        );

        colAutor.setCellValueFactory(
        new PropertyValueFactory<>("autorNome")
        );

        colConteudo.setCellValueFactory(new PropertyValueFactory<>("conteudo"));

        colDataPublicacao.setCellValueFactory(new PropertyValueFactory<>("dataPublicacao"));
        colDataPublicacao.setCellFactory(column -> new TableCell<Noticia, LocalDateTime>() {
        @Override
        protected void updateItem(LocalDateTime date, boolean empty) {
            super.updateItem(date, empty);
            if (empty || date == null) {
                setText("");
            } else {
                setText(date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            }
        }
    });

    listarNoticias();
}


    @FXML
private void handleSalvar() {
    if (!Sessao.isAdmin() && !Sessao.isJornalista()) {
        mostrarErro("Você não tem permissão!");
        return;
    }

    if (txtTitulo.getText().trim().isEmpty() || txtConteudo.getText().trim().isEmpty()) {
        mostrarErro("Preencha todos os campos!");
        return;
    }

    try {
        Noticia noticia = new Noticia();
        noticia.setTitulo(txtTitulo.getText().trim());
        noticia.setConteudo(txtConteudo.getText().trim());
        noticia.setUsuarioId(Sessao.getUsuario().getId());  

        NoticiaDAO dao = new NoticiaDAO();
        dao.salvar(noticia);

        listarNoticias();
        limparCampos();
        mostrarInfo("Notícia salva com sucesso!");

    } catch (Exception e) {
        e.printStackTrace();
        mostrarErro("Falha ao salvar notícia: " + e.getMessage());
    }
}
    
    @FXML
private void handleAtualizar() {
    Noticia selecionada = tabelaNoticias.getSelectionModel().getSelectedItem();
    if (selecionada == null) {
        mostrarErro("Selecione uma notícia para atualizar!");
        return;
    }

    txtTitulo.setText(selecionada.getTitulo());
    txtConteudo.setText(selecionada.getConteudo());

    mostrarInfo("Preencha os campos e clique Salvar para atualizar.");
}

@FXML
private void handleDeletar() {
    Noticia selecionada = tabelaNoticias.getSelectionModel().getSelectedItem();
    if (selecionada == null) {
        mostrarErro("Selecione uma notícia para deletar!");
        return;
    }

    Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
    confirm.setTitle("Confirmação");
    confirm.setHeaderText("Deletar notícia?");
    confirm.setContentText("Tem certeza que deseja deletar: " + selecionada.getTitulo() + "?");

    if (confirm.showAndWait().get() == ButtonType.OK) {
        NoticiaDAO dao = new NoticiaDAO();
        dao.deletar(selecionada.getId());
        listarNoticias();
        mostrarInfo("Notícia deletada com sucesso!");
    }
}




    private void listarNoticias() {

        NoticiaDAO dao = new NoticiaDAO();
        List<Noticia> lista = dao.listar();

        tabelaNoticias.setItems(
                FXCollections.observableArrayList(lista)
        );
    }

    private void limparCampos() {
        txtTitulo.clear();
        txtConteudo.clear();
    }

    private void mostrarErro(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(msg);
        alert.show();
    }

    private void mostrarInfo(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(msg);
        alert.show();
    }
}
