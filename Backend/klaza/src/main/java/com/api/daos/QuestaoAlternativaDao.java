package com.api.daos;

import com.api.entities.QuestaoAlternativa;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class QuestaoAlternativaDao {

    private ConexaoMySQL conexao;

    public QuestaoAlternativa adicionar(QuestaoAlternativa questaoAlternativa) {
        conexao = new ConexaoMySQL();

        String sql = "INSERT INTO questao_alternativa VALUES(null, ?, ?);";
        try{
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setString(1, questaoAlternativa.getResposta());
            st.setLong(2, questaoAlternativa.getIdQuestao());
            st.executeUpdate();

            ResultSet rs = st.getGeneratedKeys();
            if(rs.next()) {
                questaoAlternativa = this.buscarPorId(rs.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.conexao.closeConnection();
        }
        return questaoAlternativa;
    }

    public void editar(QuestaoAlternativa questaoAlternativa) {
        conexao = new ConexaoMySQL();

        String sql = "UPDATE questao_alternativa SET resposta=?, id_questao=? WHERE id_questao_alternativa=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setString(1, questaoAlternativa.getResposta());
            st.setLong(2, questaoAlternativa.getIdQuestao());
            st.setLong(3, questaoAlternativa.getIdQuestaoAlternativa());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
    }

    public void excluir(long idQuestaoAlternativa) {
        conexao = new ConexaoMySQL();
        String sql = "DELETE FROM questao_alternativa WHERE id_questao_alternativa=?;";

        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idQuestaoAlternativa);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
    }

    public QuestaoAlternativa buscarPorId(long idQuestaoAlternativa) {
        conexao = new ConexaoMySQL();
        QuestaoAlternativa questaoAlternativa = null;
        String sql = "SELECT * FROM questao_alternativa WHERE id_questao_alternativa=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idQuestaoAlternativa);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                questaoAlternativa = new QuestaoAlternativa();
                questaoAlternativa.setIdQuestaoAlternativa(rs.getLong("id_questao_alternativa"));
                questaoAlternativa.setResposta(rs.getString("resposta"));
                questaoAlternativa.setIdQuestao(rs.getByte("id_questao"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
        return questaoAlternativa;
    }

    public List<QuestaoAlternativa> buscar() {
        conexao = new ConexaoMySQL();
        List<QuestaoAlternativa> listQuestaoAlternativa = new ArrayList();
        String sql = "SELECT * FROM questao_alternativa;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                QuestaoAlternativa questaoAlternativa = new QuestaoAlternativa();
                questaoAlternativa.setIdQuestaoAlternativa(rs.getLong("id_questao_alternativa"));
                questaoAlternativa.setResposta(rs.getString("resposta"));
                questaoAlternativa.setIdQuestao(rs.getByte("id_questao"));
                listQuestaoAlternativa.add(questaoAlternativa);
            }
        } catch(SQLException e) {
        }
        return listQuestaoAlternativa;
    }
}
