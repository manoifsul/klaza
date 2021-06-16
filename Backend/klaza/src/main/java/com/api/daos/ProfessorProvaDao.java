package com.api.daos;

import com.api.entities.Aula;
import com.api.entities.Professor;
import com.api.entities.ProfessorProva;
import com.api.entities.Prova;
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
public class ProfessorProvaDao {

    private ConexaoMySQL conexao;

    public ProfessorProva adicionar(ProfessorProva professorProva) {
        conexao = new ConexaoMySQL();

        String sql = "INSERT INTO professor_prova VALUES(null, ?, ?);";
        try{
            PreparedStatement st = conexao.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setLong(1, professorProva.getProfessor().getIdProfessor());
            st.setLong(2, professorProva.getProva().getIdProva());
            st.executeUpdate();

            ResultSet rs = st.getGeneratedKeys();
            if(rs.next()) {
                professorProva = this.buscarPorId(rs.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.conexao.closeConnection();
        }
        return professorProva;
    }

    public void editar(ProfessorProva professorProva) {
        conexao = new ConexaoMySQL();

        String sql = "UPDATE professor_prova SET id_professor=?, id_prova=? WHERE id_professor_prova=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, professorProva.getProfessor().getIdProfessor());
            st.setLong(2, professorProva.getProva().getIdProva());
            st.setLong(3, professorProva.getIdProfessorProva());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
    }

    public void excluir(long idProfessorProva) {
        conexao = new ConexaoMySQL();
        String sql = "DELETE FROM professor_prova WHERE id_professor_prova=?;";

        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idProfessorProva);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
    }

    public ProfessorProva buscarPorId(long idProfessorProva) {
        conexao = new ConexaoMySQL();
        ProfessorProva professorProva = null;
        String sql = "SELECT * FROM professor_prova WHERE id_professor_prova=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idProfessorProva);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                professorProva = new ProfessorProva();
                professorProva.setIdProfessorProva(rs.getLong("id_professor_prova"));
                professorProva.setProfessor(new ProfessorDao().buscarPorId(rs.getLong("id_professor")));
                professorProva.setProva(new ProvaDao().buscarPorId(rs.getLong("id_prova")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
        return professorProva;
    }

    public List<ProfessorProva> buscarPorIdProva(long idProva) {
        conexao = new ConexaoMySQL();
        List<ProfessorProva> professorProva = new ArrayList<>();
        String sql = "SELECT * FROM professor_prova WHERE id_prova=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idProva);
            ResultSet rs = st.executeQuery();
            while (rs.next()){

                ProfessorProva pp = new ProfessorProva();
                pp.setIdProfessorProva(rs.getLong("id_professor_prova"));
                pp.setProfessor(new ProfessorDao().buscarPorId(rs.getLong("id_professor")));
                pp.setProva(new ProvaDao().buscarPorId(rs.getLong("id_prova")));

                professorProva.add(pp);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
        return professorProva;
    }

    public List<ProfessorProva> buscar() {
        conexao = new ConexaoMySQL();
        List<ProfessorProva> listProfessorProva = new ArrayList();
        String sql = "SELECT * FROM professor_prova;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                ProfessorProva professorProva = new ProfessorProva();
                professorProva.setIdProfessorProva(rs.getLong("id_professor_prova"));
                professorProva.setProfessor(new ProfessorDao().buscarPorId(rs.getLong("id_professor")));
                professorProva.setProva(new ProvaDao().buscarPorId(rs.getLong("id_prova")));
                listProfessorProva.add(professorProva);
            }
        } catch(SQLException e) {
        }
        return listProfessorProva;
    }

    public List<Professor> buscarProfessorPorIdProva(long idProva) {

        conexao = new ConexaoMySQL();
        List<Professor> list = new ArrayList();
        String sql = "SELECT * FROM professor_prova WHERE id_prova=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idProva);
            ResultSet rs = st.executeQuery();

            while(rs.next()) {

                list.add(new ProfessorDao().buscarPorId(rs.getLong("id_professor")));

            }

        } catch(SQLException e) {}

        return list;

    }

}

