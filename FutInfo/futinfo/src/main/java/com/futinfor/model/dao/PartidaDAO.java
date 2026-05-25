package com.futinfor.model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.futinfor.model.entity.Partida;
import com.futinfor.model.entity.Times;
import com.futinfor.util.ConnectionFactory;

import javafx.scene.control.Alert;

public class PartidaDAO implements CrudDAO<Partida> {

    @Override
    public void salvar(Partida partida) {

        String sql = """
                INSERT INTO partida 
                (time_casa_id, time_visitante_id, gols_casa, gols_visitante, data_hora, estadio) 
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, partida.getTimeCasa().getId());
            stmt.setLong(2, partida.getTimeVisitante().getId());
            stmt.setInt(3, partida.getGolsCasa());
            stmt.setInt(4, partida.getGolsVisitante());      
            stmt.setTimestamp(5, Timestamp.valueOf(partida.getDataHora()));  
            stmt.setString(6, partida.getEstadio());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
   public List<Partida> listar() {
    List<Partida> partidas = new ArrayList<>();
    String sql = """
        SELECT 
            p.id,
            p.data_hora,
            p.time_casa_id,
            p.time_visitante_id,
            p.gols_casa,
            p.gols_visitante
        FROM partida p
    """;

    try (Connection conn = ConnectionFactory.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        TimeDAO timeDAO = new TimeDAO();  

        while (rs.next()) {
            Partida p = new Partida();
            p.setId(rs.getLong("id"));
            p.setDataHora(rs.getTimestamp("data_hora").toLocalDateTime());
            p.setGolsCasa(rs.getInt("gols_casa"));
            p.setGolsVisitante(rs.getInt("gols_visitante"));

            
            p.setTimeCasa(timeDAO.buscarPorId(rs.getLong("time_casa_id")));
            p.setTimeVisitante(timeDAO.buscarPorId(rs.getLong("time_visitante_id")));

            partidas.add(p);
        }
    } catch (SQLException e) {
    e.printStackTrace();
    
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erro ao salvar partida");
    alert.setHeaderText("Falha ao inserir no banco");
    alert.setContentText(e.getMessage());
    alert.showAndWait();
    }
    return partidas;
}

    @Override
    public void atualizar(Partida partida) {

        String sql = """
            UPDATE partida 
            SET data_hora = ?, estadio = ?, gols_casa = ?, gols_visitante = ?
            WHERE id = ?
            """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setTimestamp(1, Timestamp.valueOf(partida.getDataHora()));
            stmt.setString(2, partida.getEstadio());
            stmt.setInt(3, partida.getGolsCasa());
            stmt.setInt(4, partida.getGolsVisitante());  
            stmt.setLong(5, partida.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletar(Long id) {

        String sql = "DELETE FROM partida WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id); 
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}