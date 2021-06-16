package com.api.daos;

import com.api.entities.Professor;
import com.api.entities.ProfessorTrabalho;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
@Service
@Repository
public class ProfessorTrabalhoDao {

    private ConexaoMySQL conexao;

    public ProfessorTrabalho adicionar(ProfessorTrabalho professorTrabalho) {
        conexao = new ConexaoMySQL();

        String sql = "INSERT INTO professor_trabalho VALUES(null, ?, ?);";
        try{
            PreparedStatement st = conexao.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setLong(1, professorTrabalho.getProfessor().getIdProfessor());
            st.setLong(2, professorTrabalho.getTrabalho().getIdTrabalho());
            st.executeUpdate();

            ResultSet rs = st.getGeneratedKeys();
            if(rs.next()) {
                professorTrabalho = this.buscarPorId(rs.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.conexao.closeConnection();
        }
        return professorTrabalho;
    }

    public void editar(ProfessorTrabalho professorTrabalho) {
        conexao = new ConexaoMySQL();

        String sql = "UPDATE professor_trabalho SET id_professor=?, id_atividade=? WHERE id_professor_trabalho=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, professorTrabalho.getProfessor().getIdProfessor());
            st.setLong(2, professorTrabalho.getTrabalho().getIdTrabalho());
            st.setLong(3, professorTrabalho.getIdProfessorTrabalho());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
    }

    public void excluir(long idProfessorTrabalho) {
        conexao = new ConexaoMySQL();
        String sql = "DELETE FROM professor_trabalho WHERE id_professor_trabalho=?;";

        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idProfessorTrabalho);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
    }

    public ProfessorTrabalho buscarPorId(long idProfessorTrabalho) {
        conexao = new ConexaoMySQL();
        ProfessorTrabalho professorTrabalho = null;
        String sql = "SELECT * FROM professor_trabalho WHERE id_professor_trabalho=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idProfessorTrabalho);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                professorTrabalho = new ProfessorTrabalho();
                professorTrabalho.setIdProfessorTrabalho(rs.getLong("id_professor_trabalho"));
                professorTrabalho.setProfessor(new ProfessorDao().buscarPorId(rs.getLong("id_professor")));
                professorTrabalho.setTrabalho(new TrabalhoDao().buscarPorId(rs.getLong("id_trabalho")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
        return professorTrabalho;
    }

    public List<ProfessorTrabalho> buscarPorIdTrabalho(long idTrabalho) {
        conexao = new ConexaoMySQL();
        List<ProfessorTrabalho> professorTrabalho = new ArrayList<>();
        String sql = "SELECT * FROM professor_trabalho WHERE id_trabalho=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idTrabalho);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                ProfessorTrabalho pt = new ProfessorTrabalho();
                pt.setIdProfessorTrabalho(rs.getLong("id_professor_trabalho"));
                pt.setProfessor(new ProfessorDao().buscarPorId(rs.getLong("id_professor")));
                pt.setTrabalho(new TrabalhoDao().buscarPorId(rs.getLong("id_trabalho")));

                professorTrabalho.add(pt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
        return professorTrabalho;
    }

    public List<ProfessorTrabalho> buscar() {
        conexao = new ConexaoMySQL();
        List<ProfessorTrabalho> listProfessorTrabalho = new ArrayList();
        String sql = "SELECT * FROM professor_trabalho;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                ProfessorTrabalho professorTrabalho = new ProfessorTrabalho();
                professorTrabalho.setIdProfessorTrabalho(rs.getLong("id_professor_trabalho"));
                professorTrabalho.setProfessor(new ProfessorDao().buscarPorId(rs.getLong("id_professor")));
                professorTrabalho.setTrabalho(new TrabalhoDao().buscarPorId(rs.getLong("id_trabalho")));
                listProfessorTrabalho.add(professorTrabalho);
            }
        } catch(SQLException e) {
        }
        return listProfessorTrabalho;
    }

    public List<Professor> buscarProfessorPorIdTrabalho(long idTrabalho) {

        conexao = new ConexaoMySQL();
        List<Professor> list = new ArrayList();
        String sql = "SELECT * FROM professor_trabalho WHERE id_trabalho=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idTrabalho);
            ResultSet rs = st.executeQuery();

            while(rs.next()) {

                list.add(new ProfessorDao().buscarPorId(rs.getLong("id_professor")));

            }

        } catch(SQLException e) {
            e.printStackTrace();
        }
        finally {
            conexao.closeConnection();
        }

        return list;

    }


}

