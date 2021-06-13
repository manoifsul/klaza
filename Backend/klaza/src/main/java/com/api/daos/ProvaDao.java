package com.api.daos;

import com.api.util.ConversaoDeData;
import com.api.entities.Prova;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Repository
public class ProvaDao {

    private ConexaoMySQL conexao;

    public Prova adicionar(Prova prova) {
        conexao = new ConexaoMySQL();

        String sql = "INSERT INTO prova VALUES(null, ?, ?, ?, ?, ?, ?, ?);";
        try{
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setString(1, prova.getNome());
            Timestamp timestampPrazo = ConversaoDeData.localDateTimeToTimestamp(prova.getPrazo());
            st.setTimestamp(2, timestampPrazo);
            st.setString(3, prova.getDescricao());
            Timestamp timestampInicio = ConversaoDeData.localDateTimeToTimestamp(prova.getInicio());
            st.setTimestamp(4, timestampInicio);
            st.setLong(5, prova.getTempo());
            st.setInt(6, prova.getTentativas());
            st.setLong(7, prova.getIdTurma());
            st.executeUpdate();

            ResultSet rs = st.getGeneratedKeys();
            if(rs.next()) {
                prova = this.buscarPorId(rs.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.conexao.closeConnection();
        }
        return prova;
    }

    public void editar(Prova prova) {
        conexao = new ConexaoMySQL();

        String sql = "UPDATE prova SET nome=?, prazo=?, descricao=?, inicio=?, tempo=?, tentativas=?, id_turma=? WHERE id_administrador=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setString(1, prova.getNome());
            Timestamp timestampPrazo = ConversaoDeData.localDateTimeToTimestamp(prova.getPrazo());
            st.setTimestamp(2, timestampPrazo);
            st.setString(3, prova.getDescricao());
            Timestamp timestampInicio = ConversaoDeData.localDateTimeToTimestamp(prova.getInicio());
            st.setTimestamp(4, timestampInicio);
            st.setLong(5, prova.getTempo());
            st.setInt(6, prova.getTentativas());
            st.setLong(7, prova.getIdTurma());
            st.setLong(8, prova.getIdProva());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
    }

    public void excluir(long idProva) {
        conexao = new ConexaoMySQL();
        String sql = "DELETE FROM prova WHERE id_prova=?;";

        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idProva);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
    }

    public Prova buscarPorId(long idProva) {
        conexao = new ConexaoMySQL();
        Prova prova = null;
        String sql = "SELECT * FROM prova WHERE id_prova=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idProva);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                prova = new Prova();
                prova.setIdProva(rs.getLong("id_prova"));
                prova.setNome(rs.getString("nome"));
                LocalDateTime ldtPrazo = ConversaoDeData.timestampToLocalDateTime(rs.getTimestamp("prazo"));
                prova.setPrazo(ldtPrazo);
                prova.setDescricao(rs.getString("descricao"));
                LocalDateTime ldtInicio = ConversaoDeData.timestampToLocalDateTime(rs.getTimestamp("inicio"));
                prova.setInicio(ldtInicio);
                prova.setTempo(rs.getLong("tempo"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
        return prova;
    }

    public List<Prova> buscar() {
        conexao = new ConexaoMySQL();
        List<Prova> listProva = new ArrayList();
        String sql = "SELECT * FROM prova;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                Prova prova = new Prova();
                prova.setIdProva(rs.getLong("id_prova"));
                prova.setNome(rs.getString("nome"));
                LocalDateTime ldtPrazo = ConversaoDeData.timestampToLocalDateTime(rs.getTimestamp("prazo"));
                prova.setPrazo(ldtPrazo);
                prova.setDescricao(rs.getString("descricao"));
                LocalDateTime ldtInicio = ConversaoDeData.timestampToLocalDateTime(rs.getTimestamp("inicio"));
                prova.setInicio(ldtInicio);
                prova.setTempo(rs.getLong("tempo"));
                listProva.add(prova);
            }
        } catch(SQLException e) {
        }
        return listProva;
    }
}
