package com.futinfor.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

import com.futinfor.model.dao.TimeDAO;
import com.futinfor.model.entity.Times;

public class TimeController {

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtPais;

    @FXML
    private TableView<Times> tabelaTimes;

    @FXML
    private TableColumn<Times, Integer> colId;

    @FXML
    private TableColumn<Times, String> colNome;

    @FXML
    private TableColumn<Times, String> colPais;

    private Times timeSelecionado;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colPais.setCellValueFactory(new PropertyValueFactory<>("pais"));

        listarTimes();
        tabelaTimes.setOnMouseClicked(e -> selecionarTime());
    }

    private void listarTimes() {
        List<Times> lista = new TimeDAO().listar();
        tabelaTimes.setItems(FXCollections.observableArrayList(lista));
    }

    @FXML
    private void salvar() {
        Times time = new Times();
        time.setNome(txtNome.getText());
        time.setPais(txtPais.getText());

        new TimeDAO().salvar(time);
        limparCampos();
        listarTimes();
    }

    @FXML
    private void atualizar() {
        if (timeSelecionado != null) {
            timeSelecionado.setNome(txtNome.getText());
            timeSelecionado.setPais(txtPais.getText());

            new TimeDAO().atualizar(timeSelecionado);
            limparCampos();
            listarTimes();
        }
    }

    @FXML
    private void deletar() {
        if (timeSelecionado != null) {
            new TimeDAO().deletar(timeSelecionado.getId());
            limparCampos();
            listarTimes();
        }
    }

    private void selecionarTime() {
        timeSelecionado = tabelaTimes.getSelectionModel().getSelectedItem();

        if (timeSelecionado != null) {
            txtNome.setText(timeSelecionado.getNome());
            txtPais.setText(timeSelecionado.getPais());
        }
    }

    private void limparCampos() {
        txtNome.clear();
        txtPais.clear();
        timeSelecionado = null;
    }
}