package com.futinfor.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.List;

import com.futinfor.model.dao.UsuarioDAO;
import com.futinfor.model.entity.Usuario;

public class UsuarioController {

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtSenha;

    @FXML
    private ComboBox<String> cbTipo;

    @FXML
    private TableView<Usuario> tabelaUsuarios;

    @FXML
    private TableColumn<Usuario, Integer> colId;

    @FXML
    private TableColumn<Usuario, String> colNome;

    @FXML
    private TableColumn<Usuario, String> colEmail;

    @FXML
    private TableColumn<Usuario, String> colTipo;

    private Usuario usuarioSelecionado;

    @FXML
    public void initialize() {
        cbTipo.getItems().addAll("ADMIN", "JORNALISTA", "LEITOR");

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));

        listarUsuarios();

        tabelaUsuarios.setOnMouseClicked(event -> selecionarUsuario());
    }

    private void listarUsuarios() {
        UsuarioDAO dao = new UsuarioDAO();
        List<Usuario> lista = dao.listar();
        tabelaUsuarios.setItems(FXCollections.observableArrayList(lista));
    }

    @FXML
    private void salvar() {
        Usuario usuario = new Usuario();
        usuario.setNome(txtNome.getText());
        usuario.setEmail(txtEmail.getText());
        usuario.setSenha(txtSenha.getText());
        usuario.setTipo(cbTipo.getValue());
        usuario.setDataCadastro(LocalDate.now());

        new UsuarioDAO().salvar(usuario);
        limparCampos();
        listarUsuarios();
    }

    @FXML
    private void atualizar() {
        if (usuarioSelecionado != null) {
            usuarioSelecionado.setNome(txtNome.getText());
            usuarioSelecionado.setEmail(txtEmail.getText());
            usuarioSelecionado.setSenha(txtSenha.getText());
            usuarioSelecionado.setTipo(cbTipo.getValue());

            new UsuarioDAO().atualizar(usuarioSelecionado);
            limparCampos();
            listarUsuarios();
        }
    }

    @FXML
    private void deletar() {
        if (usuarioSelecionado != null) {
            new UsuarioDAO().deletar(usuarioSelecionado.getId());
            limparCampos();
            listarUsuarios();
        }
    }

    private void selecionarUsuario() {
        usuarioSelecionado = tabelaUsuarios.getSelectionModel().getSelectedItem();

        if (usuarioSelecionado != null) {
            txtNome.setText(usuarioSelecionado.getNome());
            txtEmail.setText(usuarioSelecionado.getEmail());
            txtSenha.setText(usuarioSelecionado.getSenha());
            cbTipo.setValue(usuarioSelecionado.getTipo());
        }
    }

    private void limparCampos() {
        txtNome.clear();
        txtEmail.clear();
        txtSenha.clear();
        cbTipo.setValue(null);
        usuarioSelecionado = null;
    }
}