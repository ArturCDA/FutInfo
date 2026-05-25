package com.futinfor.model.dao;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.futinfor.model.entity.Noticia;
import com.futinfor.util.ConnectionFactory;
import com.futinfor.util.Sessao;

public class NoticiaDAO implements CrudDAO<Noticia> {

    public void salvar(Noticia noticia) {
    String sql = """
        INSERT INTO noticia 
        (titulo, conteudo, data_publicacao, usuario_id) 
        VALUES (?, ?, CURRENT_DATE, ?)
    """;

    try (Connection conn = ConnectionFactory.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, noticia.getTitulo());
        stmt.setString(2, noticia.getConteudo());
        stmt.setLong(3, noticia.getUsuarioId());  
        int rowsAffected = stmt.executeUpdate();
        if (rowsAffected == 0) {
            throw new SQLException("Nenhuma linha afetada");
        }

        System.out.println("Notícia salva com sucesso! ID usuário: " + noticia.getUsuarioId());

    } catch (SQLException e) {
        throw new RuntimeException("Erro ao salvar notícia: " + e.getMessage(), e);
    }
}

    @Override
    public List<Noticia> listar() {

        List<Noticia> lista = new ArrayList<>();

        String sql = """
            SELECT n.id,
                   n.titulo,
                   n.conteudo,
                   n.usuario_id,
                   n.data_publicacao,
                   u.nome AS autor_nome
            FROM noticia n
            INNER JOIN usuario u
                ON n.usuario_id = u.id
            ORDER BY n.data_publicacao DESC
        """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Noticia n = new Noticia();

                n.setId(rs.getLong("id"));
                n.setTitulo(rs.getString("titulo"));
                n.setConteudo(rs.getString("conteudo"));
                n.setUsuarioId(rs.getLong("usuario_id"));
                n.setAutorNome(rs.getString("autor_nome"));
                n.setDataPublicacao(
                        rs.getTimestamp("data_publicacao")
                          .toLocalDateTime()
                );

                lista.add(n);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public void atualizar(Noticia noticia) {

        String sql = "UPDATE noticia SET titulo = ?, conteudo = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, noticia.getTitulo());
            stmt.setString(2, noticia.getConteudo());
            stmt.setLong(3, noticia.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletar(Long id) {

        String sql = "DELETE FROM noticia WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}