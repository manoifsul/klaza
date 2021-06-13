package com.api.daos;

import com.api.entities.Professor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@Repository
public class ProfessorDao {

    private ConexaoMySQL conexao;

    public Professor adicionar(Professor professor) {
        conexao = new ConexaoMySQL();

        String sql = "INSERT INTO professor VALUES(null, ?, ?, ?, ?);";
        try{
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setString(1, professor.getNome());
            st.setString(2, professor.getMatricula());
            st.setString(3, professor.getSenha());
            st.setString(4, professor.getEmail());
            st.executeUpdate();

            ResultSet rs = st.getGeneratedKeys();
            if(rs.next()) {
                professor = this.buscarPorId(rs.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.conexao.closeConnection();
        }
        return professor;
    }

    public void editar(Professor professor) {
        conexao = new ConexaoMySQL();

        String sql = "UPDATE professor SET nome=?, matricula=?, senha=?, email=? WHERE id_professor=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setString(1, professor.getNome());
            st.setString(2, professor.getMatricula());
            st.setString(3, professor.getSenha());
            st.setString(4, professor.getEmail());
            st.setLong(5, professor.getIdProfessor());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
    }

    public void excluir(long idProfessor) {
        conexao = new ConexaoMySQL();
        String sql = "DELETE FROM professor WHERE id_professor=?;";

        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idProfessor);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
    }

    public Professor buscarPorId(long idProfessor) {
        conexao = new ConexaoMySQL();
        Professor professor = null;
        String sql = "SELECT * FROM professor WHERE id_professor=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idProfessor);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                professor = new Professor();
                professor.setIdProfessor(rs.getLong("id_professor"));
                professor.setNome(rs.getString("nome"));
                professor.setMatricula(rs.getString("matricula"));
                professor.setSenha(rs.getString("senha"));
                professor.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
        return professor;
    }

    public List<Professor> buscar() {
        conexao = new ConexaoMySQL();
        List<Professor> listProfessor = new ArrayList();
        String sql = "SELECT * FROM professor;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                Professor professor = new Professor();
                professor.setIdProfessor(rs.getLong("id_professor"));
                professor.setNome(rs.getString("nome"));
                professor.setMatricula(rs.getString("matricula"));
                professor.setSenha(rs.getString("senha"));
                professor.setEmail(rs.getString("email"));
                listProfessor.add(professor);
            }
        } catch(SQLException e) {
        }
        return listProfessor;
    }

    public Professor buscarPorMatricula(String matricula) {
        conexao = new ConexaoMySQL();
        Professor professor = null;
        String sql = "SELECT * FROM professor WHERE matricula=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setString(1, matricula);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                professor = new Professor();
                professor.setIdProfessor(rs.getLong("id_aluno"));
                professor.setNome(rs.getString("nome"));
                professor.setMatricula(rs.getString("matricula"));
                professor.setSenha(rs.getString("senha"));
                professor.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
        return professor;
    }
}

