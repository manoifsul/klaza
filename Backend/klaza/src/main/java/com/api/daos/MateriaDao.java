package com.api.daos;


import com.api.entities.Materia;
import com.api.entities.MateriaProfessor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@Repository
public class MateriaDao {

    private ConexaoMySQL conexao;

    public Materia adicionar(Materia materia) {
        conexao = new ConexaoMySQL();

        String sql = "INSERT INTO materia VALUES(null, ?);";
        try{
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setString(1, materia.getNome());
            st.executeUpdate();

            ResultSet rs = st.getGeneratedKeys();
            if(rs.next()) {
                materia = this.buscarPorId(rs.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.conexao.closeConnection();
        }
        return materia;
    }

    public void editar(Materia materia) {
        conexao = new ConexaoMySQL();

        String sql = "UPDATE materia SET nome=? WHERE id_materia=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setString(1, materia.getNome());
            st.setLong(2, materia.getIdMateria());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
    }

    public void excluir(long idMateria) {
        conexao = new ConexaoMySQL();
        String sql = "DELETE FROM materia WHERE id_materia=?;";

        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idMateria);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
    }

    public Materia buscarPorId(long idMateria) {
        conexao = new ConexaoMySQL();
        Materia materia = null;
        String sql = "SELECT * FROM materia WHERE id_materia=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idMateria);
            ResultSet rs = st.executeQuery();

            if(rs.next()){
                materia = new Materia();
                materia.setIdMateria(rs.getLong("id_materia"));
                materia.setNome(rs.getString("nome"));
                materia.setIdProfessor(new MateriaProfessorDao().buscarProfessorPorIdMateria(materia.getIdMateria()).stream().map(p -> p.getIdProfessor()).toList());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
        return materia;
    }

    public Materia buscarPorIdSemProfessor(long idMateria) {

        conexao = new ConexaoMySQL();
        Materia materia = null;
        String sql = "SELECT * FROM materia WHERE id_materia=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idMateria);
            ResultSet rs = st.executeQuery();

            if(rs.next()){
                materia = new Materia();
                materia.setIdMateria(rs.getLong("id_materia"));
                materia.setNome(rs.getString("nome"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
        return materia;

    }

    public List<Materia> buscar() {
        conexao = new ConexaoMySQL();
        List<Materia> listMateria = new ArrayList();
        String sql = "SELECT * FROM materia;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                Materia materia = new Materia();
                materia.setIdMateria(rs.getLong("id_materia"));
                materia.setNome(rs.getString("nome"));
                materia.setIdProfessor(new MateriaProfessorDao().buscarPorIdMateria(materia.getIdMateria()).stream().map(materiaProfessor -> materiaProfessor.getProfessor().getIdProfessor()).toList());
                listMateria.add(materia);
            }
        } catch(SQLException e) {
        }
        return listMateria;
    }

}
