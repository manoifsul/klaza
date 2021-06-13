package com.api.daos;

import com.api.entities.Aluno;
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
public class AlunoDao {

    private ConexaoMySQL conexao;

    public Aluno adicionar(Aluno aluno) {
        conexao = new ConexaoMySQL();
        String sql = "INSERT INTO aluno VALUES(null, ?, ?, ?, ?);";
        try{
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setString(1, aluno.getNome());
            st.setString(2, aluno.getMatricula());
            st.setString(3, aluno.getSenha());
            st.setString(4, aluno.getEmail());
            st.executeUpdate();

            ResultSet rs = st.getGeneratedKeys();
            if(rs.next()) {
                aluno = this.buscarPorId(rs.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.conexao.closeConnection();
        }
        return aluno;
    }

    public void editar(Aluno aluno) {
        conexao = new ConexaoMySQL();

        String sql = "UPDATE aluno SET nome=?, matricula=?, senha=?, email=? WHERE id_aluno=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setString(1, aluno.getNome());
            st.setString(2, aluno.getMatricula());
            st.setString(3, aluno.getSenha());
            st.setString(4, aluno.getEmail());
            st.setLong(5, aluno.getIdAluno());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
    }

    public void excluir(long idAluno) {
        conexao = new ConexaoMySQL();
        String sql = "DELETE FROM aluno WHERE id_aluno=?;";

        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idAluno);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
    }

    public Aluno buscarPorId(long idAluno) {
        conexao = new ConexaoMySQL();
        Aluno aluno = null;
        String sql = "SELECT * FROM aluno WHERE id_aluno=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idAluno);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                aluno = new Aluno();
                aluno.setIdAluno(rs.getLong("id_aluno"));
                aluno.setNome(rs.getString("nome"));
                aluno.setMatricula(rs.getString("matricula"));
                aluno.setSenha(rs.getString("senha"));
                aluno.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
        return aluno;
    }

    public List<Aluno> buscar() {
        conexao = new ConexaoMySQL();
        List<Aluno> listAluno = new ArrayList();
        String sql = "SELECT * FROM aluno;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                Aluno aluno = new Aluno();
                aluno.setIdAluno(rs.getLong("id_aluno"));
                aluno.setNome(rs.getString("nome"));
                aluno.setMatricula(rs.getString("matricula"));
                aluno.setSenha(rs.getString("senha"));
                aluno.setEmail(rs.getString("email"));
                listAluno.add(aluno);
            }
        } catch(SQLException e) {
        }
        return listAluno;
    }

    public Aluno buscarPorMatricula(String matricula) {
        conexao = new ConexaoMySQL();
        Aluno aluno = null;
        String sql = "SELECT * FROM aluno WHERE matricula=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setString(1, matricula);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                aluno = new Aluno();
                aluno.setIdAluno(rs.getLong("id_aluno"));
                aluno.setNome(rs.getString("nome"));
                aluno.setMatricula(rs.getString("matricula"));
                aluno.setSenha(rs.getString("senha"));
                aluno.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
        return aluno;
    }
}

