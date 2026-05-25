package com.futinfor.model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.futinfor.model.entity.Times;
import com.futinfor.util.ConnectionFactory;

public class TimeDAO implements CrudDAO<Times> {

    public void salvar(Times times) {
        String sql = "INSERT INTO time (nome, pais) VALUES (?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, times.getNome());
            stmt.setString(2, times.getPais());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Times> listar() {
        List<Times> lista = new ArrayList<>();
        String sql = "SELECT * FROM time";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Times t = new Times();
                t.setId(rs.getLong("id"));
                t.setNome(rs.getString("nome"));
                t.setPais(rs.getString("pais"));

                lista.add(t);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public void atualizar(Times time) {
        String sql = "UPDATE time SET nome = ?, pais = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, time.getNome());
            stmt.setString(2, time.getPais());
            stmt.setLong(3, time.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletar(Long id) {
        String sql = "DELETE FROM time WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Times buscarPorId(Long id) {
        String sql = "SELECT * FROM time WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Times t = new Times();
                    t.setId(rs.getLong("id"));
                    t.setNome(rs.getString("nome"));
                    t.setPais(rs.getString("pais"));
                    return t;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;  // Retorna null se não encontrar
    }
}