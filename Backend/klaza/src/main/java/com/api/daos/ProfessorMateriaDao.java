package com.api.daos;

import com.api.entities.ProfessorMateria;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Service
@Repository
public class ProfessorMateriaDao {

    private ConexaoMySQL conexao;

    public ProfessorMateria adicionar(ProfessorMateria professorMateria) {
        conexao = new ConexaoMySQL();

        String sql = "INSERT INTO professor_materia VALUES(null, ?, ?);";
        try{
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, professorMateria.getProfessor().getIdProfessor());
            st.setLong(2, professorMateria.getMateria().getIdMateria());
            st.executeUpdate();

            ResultSet rs = st.getGeneratedKeys();
            if(rs.next()) {
                professorMateria = this.buscarPorId(rs.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.conexao.closeConnection();
        }
        return professorMateria;
    }

    public void editar(ProfessorMateria professorMateria) {
        conexao = new ConexaoMySQL();

        String sql = "UPDATE professor_materia SET id_professor=?, id_materia=? WHERE id_professor_materia=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, professorMateria.getProfessor().getIdProfessor());
            st.setLong(2, professorMateria.getMateria().getIdMateria());
            st.setLong(3, professorMateria.getIdProfessorMateria());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
    }

    public void excluir(long idProfessorMateria) {
        conexao = new ConexaoMySQL();
        String sql = "DELETE FROM professor_materia WHERE id_professor_materia=?;";

        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idProfessorMateria);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
    }

    public ProfessorMateria buscarPorId(long idProfessorMateria) {
        conexao = new ConexaoMySQL();
        ProfessorMateria professorMateria = null;
        String sql = "SELECT * FROM professor_materia WHERE id_professor_materia=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idProfessorMateria);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                professorMateria = new ProfessorMateria();
                professorMateria.setIdProfessorMateria(rs.getLong("id_professor_materia"));
                professorMateria.setProfessor(new ProfessorDao().buscarPorId(rs.getLong("id_professor")));
                professorMateria.setMateria(new MateriaDao().buscarPorId(rs.getLong("id_materia")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
        return professorMateria;
    }

    public List<ProfessorMateria> buscar() {
        conexao = new ConexaoMySQL();
        List<ProfessorMateria> listProfessorMateria = new ArrayList();
        String sql = "SELECT * FROM professor_materia;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                ProfessorMateria professorMateria = new ProfessorMateria();
                professorMateria.setIdProfessorMateria(rs.getLong("id_professor_materia"));
                professorMateria.setProfessor(new ProfessorDao().buscarPorId(rs.getLong("id_professor")));
                professorMateria.setMateria(new MateriaDao().buscarPorId(rs.getLong("id_materia")));
                listProfessorMateria.add(professorMateria);
            }
        } catch(SQLException e) {
        }
        return listProfessorMateria;
    }
}

