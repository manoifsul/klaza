package com.api.daos;

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
public class MateriaProfessorDao {

    private ConexaoMySQL conexao;

    public MateriaProfessor adicionar(MateriaProfessor materiaProfessor) {
        conexao = new ConexaoMySQL();

        String sql = "INSERT INTO materia_professor VALUES(null, ?, ?);";
        try{
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, materiaProfessor.getMateria().getIdMateria());
            st.setLong(2, materiaProfessor.getProfessor().getIdProfessor());
            st.executeUpdate();

            ResultSet rs = st.getGeneratedKeys();
            if(rs.next()) {
                materiaProfessor = this.buscarPorId(rs.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.conexao.closeConnection();
        }
        return materiaProfessor;
    }

    public void editar(MateriaProfessor materiaProfessor) {
        conexao = new ConexaoMySQL();

        String sql = "UPDATE materia_professor SET id_materia=?, id_professor=? WHERE id_materia_professor=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, materiaProfessor.getMateria().getIdMateria());
            st.setLong(2, materiaProfessor.getProfessor().getIdProfessor());
            st.setLong(3, materiaProfessor.getIdMateriaProfessor());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
    }

    public void excluir(long idMateriaProfessor) {
        conexao = new ConexaoMySQL();
        String sql = "DELETE FROM materia_professor WHERE id_materia_professor=?;";

        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idMateriaProfessor);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
    }

    public MateriaProfessor buscarPorId(long idMateriaProfessor) {
        conexao = new ConexaoMySQL();
        MateriaProfessor materiaProfessor = null;
        String sql = "SELECT * FROM materia_professor WHERE id_materia_professor=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idMateriaProfessor);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                materiaProfessor = new MateriaProfessor();
                materiaProfessor.setIdMateriaProfessor(rs.getLong("id_materia_professor"));
                materiaProfessor.setMateria(new MateriaDao().buscarPorId(rs.getLong("id_materia")));
                materiaProfessor.setProfessor(new ProfessorDao().buscarPorId(rs.getLong("id_professor")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
        return materiaProfessor;
    }

    public List<MateriaProfessor> buscar() {
        conexao = new ConexaoMySQL();
        List<MateriaProfessor> listMateriaProfessor = new ArrayList();
        String sql = "SELECT * FROM materia_professor;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                MateriaProfessor materiaProfessor = new MateriaProfessor();
                materiaProfessor.setIdMateriaProfessor(rs.getLong("id_materia_professor"));
                materiaProfessor.setMateria(new MateriaDao().buscarPorId(rs.getLong("id_materia")));
                materiaProfessor.setProfessor(new ProfessorDao().buscarPorId(rs.getLong("id_professor")));
                listMateriaProfessor.add(materiaProfessor);
            }
        } catch(SQLException e) {
        }
        return listMateriaProfessor;
    }
}

