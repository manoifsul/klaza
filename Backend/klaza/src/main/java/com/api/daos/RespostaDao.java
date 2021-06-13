package com.api.daos;

import com.api.entities.Questao;
import com.api.entities.Resposta;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@Repository
public class RespostaDao {

    private ConexaoMySQL conexao;

    public Resposta adicionar(Resposta resposta) {
        conexao = new ConexaoMySQL();

        String sql = "INSERT INTO resposta_aluno VALUES(null, ?, ?, ?);";
        try{
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setString(1, resposta.getResposta());
            st.setLong(2, resposta.getIdAluno());
            st.setLong(3, resposta.getQuestao().getIdQuestao());
            st.executeUpdate();

            ResultSet rs = st.getGeneratedKeys();
            if(rs.next()) {
                resposta = this.buscarPorId(rs.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.conexao.closeConnection();
        }
        return resposta;
    }

    public void editar(Resposta resposta) {
        conexao = new ConexaoMySQL();

        String sql = "UPDATE resposta_aluno SET pergunta=?, tipo=? WHERE id_resposta_aluno=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setString(1, resposta.getResposta());
            st.setLong(2, resposta.getIdAluno());
            st.setLong(3, resposta.getQuestao().getIdQuestao());
            st.setLong(4, resposta.getIdResposta());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
    }

    public void excluir(long idResposta) {
        conexao = new ConexaoMySQL();
        String sql = "DELETE FROM resposta_aluno WHERE id_resposta_aluno=?;";

        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idResposta);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
    }

    public Resposta buscarPorId(long idResposta) {
        conexao = new ConexaoMySQL();
        Resposta resposta = null;
        String sql = "SELECT * FROM resposta_aluno WHERE id_resposta_aluno=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idResposta);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                resposta = new Resposta();
                resposta.setIdResposta(rs.getLong("id_resposta_aluno"));
                resposta.setResposta(rs.getString("resposta"));
                resposta.setIdAluno(rs.getLong("id_aluno"));
                Questao questao = new QuestaoDao().buscarPorId(rs.getLong("id_questao"));
                resposta.setQuestao(questao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
        return resposta;
    }

    public List<Resposta> buscar() {
        conexao = new ConexaoMySQL();
        List<Resposta> listResposta = new ArrayList();
        String sql = "SELECT * FROM resposta_aluno;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                Resposta resposta = new Resposta();
                resposta.setIdResposta(rs.getLong("id_resposta_aluno"));
                resposta.setResposta(rs.getString("resposta"));
                resposta.setIdAluno(rs.getLong("id_aluno"));
                Questao questao = new QuestaoDao().buscarPorId(rs.getLong("id_questao"));
                listResposta.add(resposta);
            }
        } catch(SQLException e) {
        }
        return listResposta;
    }
}
