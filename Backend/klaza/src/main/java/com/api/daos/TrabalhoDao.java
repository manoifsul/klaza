package com.api.daos;

import com.api.util.ConversaoDeData;
import com.api.entities.Trabalho;
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
public class TrabalhoDao {

    private ConexaoMySQL conexao;

    public Trabalho adicionar(Trabalho trabalho) {
        conexao = new ConexaoMySQL();

        String sql = "INSERT INTO trabalho VALUES(null, ?, ?, ?, ?, ?, ?, ?, ?);";
        try{
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setString(1, trabalho.getNome());
            Timestamp timestampPrazo = ConversaoDeData.localDateTimeToTimestamp(trabalho.getPrazo());
            st.setTimestamp(2, timestampPrazo);
            st.setString(3, trabalho.getDescricao());
            Timestamp timestampInicio = ConversaoDeData.localDateTimeToTimestamp(trabalho.getInicio());
            st.setTimestamp(4, timestampInicio);
            st.setLong(5, trabalho.getTempo());
            st.setByte(6, trabalho.getTentativas());
            st.setByte(7, trabalho.getTipo());
            st.setLong(8, trabalho.getIdTurma());
            st.executeUpdate();

            ResultSet rs = st.getGeneratedKeys();
            if(rs.next()) {
                trabalho = this.buscarPorId(rs.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.conexao.closeConnection();
        }
        return trabalho;
    }

    public void editar(Trabalho trabalho) {
        conexao = new ConexaoMySQL();

        String sql = "UPDATE trabalho SET nome=?, prazo=?, descricao=?, inicio=?, tempo=?, tentativas=?, tipo=?, id_turma=? WHERE id_trabalho=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setString(1, trabalho.getNome());
            Timestamp timestampPrazo = ConversaoDeData.localDateTimeToTimestamp(trabalho.getPrazo());
            st.setTimestamp(2, timestampPrazo);
            st.setString(3, trabalho.getDescricao());
            Timestamp timestampInicio = ConversaoDeData.localDateTimeToTimestamp(trabalho.getInicio());
            st.setTimestamp(4, timestampInicio);
            st.setLong(5, trabalho.getTempo());
            st.setByte(6, trabalho.getTentativas());
            st.setByte(7, trabalho.getTipo());
            st.setLong(8, trabalho.getIdTurma());
            st.setLong(9, trabalho.getIdTrabalho());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
    }

    public void excluir(long idTrabalho) {
        conexao = new ConexaoMySQL();
        String sql = "DELETE FROM trabalho WHERE id_trabalho=?;";

        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idTrabalho);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
    }

    public Trabalho buscarPorId(long idTrabalho) {
        conexao = new ConexaoMySQL();
        Trabalho trabalho = null;
        String sql = "SELECT * FROM trabalho WHERE id_trabalho=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idTrabalho);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                trabalho = new Trabalho();
                trabalho.setIdTrabalho(rs.getLong("id_trabalho"));
                trabalho.setNome(rs.getString("nome"));
                LocalDateTime ldtPrazo = ConversaoDeData.timestampToLocalDateTime(rs.getTimestamp("prazo"));
                trabalho.setPrazo(ldtPrazo);
                trabalho.setDescricao(rs.getString("descricao"));
                LocalDateTime ldtInicio = ConversaoDeData.timestampToLocalDateTime(rs.getTimestamp("inicio"));
                trabalho.setInicio(ldtInicio);
                trabalho.setTempo(rs.getLong("tempo"));
                trabalho.setTentativas(rs.getByte("tentativas"));
                trabalho.setTipo(rs.getByte("tipo"));
                trabalho.setIdTurma(rs.getLong("id_turma"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
        return trabalho;
    }

    public List<Trabalho> buscar() {
        conexao = new ConexaoMySQL();
        List<Trabalho> listTrabalho = new ArrayList();
        String sql = "SELECT * FROM trabalho;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                Trabalho trabalho = new Trabalho();
                trabalho.setIdTrabalho(rs.getLong("id_trabalho"));
                trabalho.setNome(rs.getString("nome"));
                LocalDateTime ldtPrazo = ConversaoDeData.timestampToLocalDateTime(rs.getTimestamp("prazo"));
                trabalho.setPrazo(ldtPrazo);
                trabalho.setDescricao(rs.getString("descricao"));
                LocalDateTime ldtInicio = ConversaoDeData.timestampToLocalDateTime(rs.getTimestamp("inicio"));
                trabalho.setInicio(ldtInicio);
                trabalho.setTempo(rs.getLong("tempo"));
                trabalho.setTentativas(rs.getByte("tentativas"));
                trabalho.setTipo(rs.getByte("tipo"));
                trabalho.setIdTurma(rs.getLong("id_turma"));
                listTrabalho.add(trabalho);
            }
        } catch(SQLException e) {
        }
        return listTrabalho;
    }
}
