package com.futinfor.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.futinfor.model.dao.PartidaDAO;
import com.futinfor.model.entity.Partida;
import com.futinfor.model.entity.Times;

public class PartidaController {

    @FXML private TextField txtTimeCasaId;
    @FXML private TextField txtTimeForaId;
    @FXML private TextField txtEstadio;
    @FXML private DatePicker dpData;



    @FXML
    private TableView<Partida> tabelaPartidas;

    @FXML
    private TableColumn<Partida, Long> colId;

    @FXML
    private TableColumn<Partida, String> colCasa;

    @FXML
    private TableColumn<Partida, String> colFora;

    @FXML
    private TableColumn<Partida, String> colEstadio;
    
    @FXML 
    private TableColumn<Partida, LocalDateTime> colDataHora;

    private Partida partidaSelecionada;

    @FXML
    public void initialize() {

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));

        colCasa.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(
                        cellData.getValue().getTimeCasa().getNome()
                )
        );

        colFora.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(
                        cellData.getValue().getTimeVisitante().getNome()
                )
        );

        colEstadio.setCellValueFactory(new PropertyValueFactory<>("estadio"));
    
        colDataHora.setCellValueFactory(new PropertyValueFactory<>("dataHora"));

    
        colDataHora.setCellFactory(column -> new TableCell<Partida, LocalDateTime>() {
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

    listarPartidas();  
}

    private void listarPartidas() {
        List<Partida> lista = new PartidaDAO().listar();
        tabelaPartidas.setItems(FXCollections.observableArrayList(lista));
    }

   @FXML
private void salvar() {
    try {
        if (txtTimeCasaId.getText().trim().isEmpty() || txtTimeForaId.getText().trim().isEmpty()) {
            mostrarErro("Preencha os IDs dos times!");
            return;
        }

        if (txtEstadio.getText().trim().isEmpty()) {
            mostrarErro("Preencha o nome do estádio!");
            return;
        }

        if (dpData.getValue() == null) {
            mostrarErro("Selecione a data da partida!");
            return;
        }

        long idCasa;
        long idFora;
        try {
            idCasa = Long.parseLong(txtTimeCasaId.getText().trim());
            idFora = Long.parseLong(txtTimeForaId.getText().trim());
        } catch (NumberFormatException e) {
            mostrarErro("IDs dos times devem ser números inteiros!");
            return;
        }

        Times casa = new Times();
        casa.setId(idCasa);

        Times visitante = new Times();
        visitante.setId(idFora);

        Partida partida = new Partida();
        partida.setTimeCasa(casa);
        partida.setTimeVisitante(visitante);
        partida.setEstadio(txtEstadio.getText().trim());
        partida.setDataHora(dpData.getValue().atStartOfDay());  

        System.out.println("Salvando partida:");
        System.out.println("  Estádio: '" + partida.getEstadio() + "'");
        System.out.println("  Data: " + partida.getDataHora());
        System.out.println("  Time Casa ID: " + idCasa);
        System.out.println("  Time Fora ID: " + idFora);

        new PartidaDAO().salvar(partida);

        mostrarInfo("Partida salva com sucesso!");
        limparCampos();
        listarPartidas();

    } catch (Exception e) {
        e.printStackTrace();
        mostrarErro("Erro ao salvar: " + e.getMessage());
    }
}

    @FXML
    private void atualizar() {

        if (partidaSelecionada != null) {

            partidaSelecionada.setEstadio(txtEstadio.getText());
            partidaSelecionada.setDataHora(dpData.getValue().atStartOfDay());

            new PartidaDAO().atualizar(partidaSelecionada);

            limparCampos();
            listarPartidas();
        }
    }

    @FXML
    private void deletar() {

        if (partidaSelecionada != null) {

            new PartidaDAO().deletar(partidaSelecionada.getId());

            limparCampos();
            listarPartidas();
        }
    }

    private void selecionarPartida() {

        partidaSelecionada = tabelaPartidas.getSelectionModel().getSelectedItem();

        if (partidaSelecionada != null) {

            txtTimeCasaId.setText(
                    String.valueOf(partidaSelecionada.getTimeCasa().getId())
            );

            txtTimeForaId.setText(
                    String.valueOf(partidaSelecionada.getTimeVisitante().getId())
            );

            txtEstadio.setText(partidaSelecionada.getEstadio());

            dpData.setValue(
                    partidaSelecionada.getDataHora().toLocalDate()
            );
        }
    }

    private void mostrarInfo(String mensagem) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Sucesso");
    alert.setHeaderText(null);
    alert.setContentText(mensagem);
    alert.showAndWait();
}

private void mostrarErro(String mensagem) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erro");
    alert.setHeaderText(null);
    alert.setContentText(mensagem);
    alert.showAndWait();
}

private void limparCampos() {
    txtTimeCasaId.clear();
    txtTimeForaId.clear();
    txtEstadio.clear();
    dpData.setValue(null);  
}
}