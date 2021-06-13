package com.api.daos;

import com.api.entities.Questao;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@Repository
public class QuestaoDao {

    private ConexaoMySQL conexao;

    public Questao adicionar(Questao questao) {
        conexao = new ConexaoMySQL();

        String sql = "INSERT INTO questao VALUES(null, ?, ?);";
        try{
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setString(1, questao.getPergunta());
            st.setByte(2, questao.getTipo());
            st.executeUpdate();

            ResultSet rs = st.getGeneratedKeys();
            if(rs.next()) {
                questao = this.buscarPorId(rs.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.conexao.closeConnection();
        }
        return questao;
    }

    public void editar(Questao questao) {
        conexao = new ConexaoMySQL();

        String sql = "UPDATE questao SET pergunta=?, tipo=? WHERE id_questao=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setString(1, questao.getPergunta());
            st.setByte(2, questao.getTipo());
            st.setLong(3, questao.getIdQuestao());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
    }

    public void excluir(long idQuestao) {
        conexao = new ConexaoMySQL();
        String sql = "DELETE FROM questao WHERE id_questao=?;";

        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idQuestao);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
    }

    public Questao buscarPorId(long idQuestao) {
        conexao = new ConexaoMySQL();
        Questao questao = null;
        String sql = "SELECT * FROM questao WHERE id_questao=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idQuestao);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                questao = new Questao();
                questao.setIdQuestao(rs.getLong("id_questao"));
                questao.setPergunta(rs.getString("pergunta"));
                questao.setTipo(rs.getByte("tipo"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
        return questao;
    }

    public List<Questao> buscar() {
        conexao = new ConexaoMySQL();
        List<Questao> listQuestao = new ArrayList();
        String sql = "SELECT * FROM questao;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                Questao questao = new Questao();
                questao.setIdQuestao(rs.getLong("id_questao"));
                questao.setPergunta(rs.getString("pergunta"));
                questao.setTipo(rs.getByte("tipo"));
                listQuestao.add(questao);
            }
        } catch(SQLException e) {
        }
        return listQuestao;
    }
}
