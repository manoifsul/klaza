package com.api.daos;

import com.api.entities.ProfessorAtividade;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Service
@Repository
public class ProfessorAtividadeDao {

    private ConexaoMySQL conexao;

    public ProfessorAtividade adicionar(ProfessorAtividade professorAtividade) {
        conexao = new ConexaoMySQL();

        String sql = "INSERT INTO professor_atividade VALUES(null, ?, ?);";
        try{
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, professorAtividade.getProfessor().getIdProfessor());
            st.setLong(2, professorAtividade.getAtividade().getIdAtividade());
            st.executeUpdate();

            ResultSet rs = st.getGeneratedKeys();
            if(rs.next()) {
                professorAtividade = this.buscarPorId(rs.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.conexao.closeConnection();
        }
        return professorAtividade;
    }

    public void editar(ProfessorAtividade professorAtividade) {
        conexao = new ConexaoMySQL();

        String sql = "UPDATE professor_atividade SET id_professor=?, id_atividade=? WHERE id_professor_atividade=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, professorAtividade.getProfessor().getIdProfessor());
            st.setLong(2, professorAtividade.getAtividade().getIdAtividade());
            st.setLong(3, professorAtividade.getIdProfessorAtividade());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
    }

    public void excluir(long idProfessorAtividade) {
        conexao = new ConexaoMySQL();
        String sql = "DELETE FROM professor_atividade WHERE id_professor_atividade=?;";

        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idProfessorAtividade);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
    }

    public ProfessorAtividade buscarPorId(long idProfessorAtividade) {
        conexao = new ConexaoMySQL();
        ProfessorAtividade professorAtividade = null;
        String sql = "SELECT * FROM professor_atividade WHERE id_professor_atividade=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idProfessorAtividade);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                professorAtividade = new ProfessorAtividade();
                professorAtividade.setIdProfessorAtividade(rs.getLong("id_professor_atividade"));
                professorAtividade.setProfessor(new ProfessorDao().buscarPorId(rs.getLong("id_professor")));
                professorAtividade.setAtividade(new AtividadeDao().buscarPorId(rs.getLong("id_atividade")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
        return professorAtividade;
    }

    public List<ProfessorAtividade> buscar() {
        conexao = new ConexaoMySQL();
        List<ProfessorAtividade> listProfessorAtividade = new ArrayList();
        String sql = "SELECT * FROM professor_atividade;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                ProfessorAtividade professorAtividade = new ProfessorAtividade();
                professorAtividade.setIdProfessorAtividade(rs.getLong("id_professor_atividade"));
                professorAtividade.setProfessor(new ProfessorDao().buscarPorId(rs.getLong("id_professor")));
                professorAtividade.setAtividade(new AtividadeDao().buscarPorId(rs.getLong("id_atividade")));
                listProfessorAtividade.add(professorAtividade);
            }
        } catch(SQLException e) {
        }
        return listProfessorAtividade;
    }
}
