package com.api.daos;


import com.api.entities.QuestaoAlternativa;
import com.api.entities.QuestaoCorreta;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class QuestaoCorretaDao {

    private ConexaoMySQL conexao;

    public QuestaoCorreta adicionar(QuestaoCorreta questaoCorreta) {
        conexao = new ConexaoMySQL();

        String sql = "INSERT INTO questao_correta VALUES(null, ?, ?);";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            for (int i = 0; i < questaoCorreta.getQuestaoAlternativa().size(); i++) {
                st.setLong(1, questaoCorreta.getIdQuestao());
                st.setLong(2, questaoCorreta.getQuestaoAlternativa().get(i).getIdQuestaoAlternativa());
                st.executeUpdate();
            }

            ResultSet rs = st.getGeneratedKeys();
            if(rs.next()) {
                questaoCorreta = this.buscarPorId(rs.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.conexao.closeConnection();
        }
        return questaoCorreta;
    }

    public void editar(QuestaoCorreta questaoCorreta) {
        conexao = new ConexaoMySQL();

        String sql = "UPDATE questao_correta SET id_questao=?, id_questao_alternativa=? WHERE id_questao_correta=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            for (int i = 0; i < questaoCorreta.getQuestaoAlternativa().size(); i++) {
                st.setLong(1, questaoCorreta.getIdQuestao());
                st.setLong(2, questaoCorreta.getQuestaoAlternativa().get(i).getIdQuestaoAlternativa());
                st.setLong(3, questaoCorreta.getIdQuestaoCorreta());
                st.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
    }

    public void excluir(long idQuestaoCorreta) {
        conexao = new ConexaoMySQL();
        String sql = "DELETE FROM questao_correta WHERE id_questao_correta=?;";

        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idQuestaoCorreta);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
    }

    public QuestaoCorreta buscarPorId(long idQuestaoCorreta) {
        conexao = new ConexaoMySQL();
        QuestaoCorreta questaoCorreta = null;
        String sql = "SELECT * FROM questao_correta WHERE id_questao_correta=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
                st.setLong(1, idQuestaoCorreta);
                ResultSet rs = st.executeQuery();
                if (rs.next()) {
                    questaoCorreta = new QuestaoCorreta();
                    questaoCorreta.setIdQuestaoCorreta(rs.getLong("id_questao_alternativa"));
                    questaoCorreta.setIdQuestao(rs.getLong("id_questao"));
                    QuestaoAlternativa questaoAlternativa = new QuestaoAlternativaDao().buscarPorId(rs.getLong("id_questao_alternativa"));
                    List<QuestaoAlternativa> idQuestaoAlternativa = new ArrayList<QuestaoAlternativa>();
                    questaoAlternativa.getIdQuestaoAlternativa();
                    questaoCorreta.setQuestaoAlternativa(idQuestaoAlternativa);
                }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
        return questaoCorreta;
    }

    public List<QuestaoCorreta> buscar() {
        conexao = new ConexaoMySQL();
        List<QuestaoCorreta> listQuestaoCorreta = new ArrayList();
        String sql = "SELECT * FROM questao_correta;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                QuestaoCorreta questaoCorreta = new QuestaoCorreta();
                questaoCorreta.setIdQuestaoCorreta(rs.getLong("id_questao_alternativa"));
                questaoCorreta.setIdQuestao(rs.getLong("id_questao"));
                QuestaoAlternativa questaoAlternativa = new QuestaoAlternativaDao().buscarPorId(rs.getLong("id_questao_alternativa"));
                List<QuestaoAlternativa> idQuestaoAlternativa = new ArrayList<QuestaoAlternativa>();
                questaoAlternativa.getIdQuestaoAlternativa();
                listQuestaoCorreta.add(questaoCorreta);
            }
        } catch (SQLException e) {
        }
        return listQuestaoCorreta;
    }
}
