package com.api.daos;

import com.api.entities.Professor;
import com.api.entities.Turma;
import com.api.entities.TurmaProfessor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Service
@Repository
public class TurmaProfessorDao {

    private ConexaoMySQL conexao;

    public TurmaProfessor adicionar(TurmaProfessor turmaProfessor) {
        conexao = new ConexaoMySQL();

        String sql = "INSERT INTO turma_professor VALUES(null, ?, ?);";
        try{
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, turmaProfessor.getTurma().getIdTurma());
            st.setLong(2, turmaProfessor.getProfessor().getIdProfessor());
            st.executeUpdate();

            ResultSet rs = st.getGeneratedKeys();
            if(rs.next()) {
                turmaProfessor = this.buscarPorId(rs.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.conexao.closeConnection();
        }
        return turmaProfessor;
    }

    public void editar(TurmaProfessor turmaProfessor) {
        conexao = new ConexaoMySQL();

        String sql = "UPDATE turma_professor SET id_turma=?, id_professor=? WHERE id_turma_professor=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, turmaProfessor.getTurma().getIdTurma());
            st.setLong(2, turmaProfessor.getProfessor().getIdProfessor());
            st.setLong(3, turmaProfessor.getIdTurmaProfessor());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
    }

    public void excluir(long idTurmaProfessor) {
        conexao = new ConexaoMySQL();
        String sql = "DELETE FROM turma_professor WHERE id_turma_professor=?;";

        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idTurmaProfessor);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
    }

    public TurmaProfessor buscarPorId(long idTurmaProfessor) {
        conexao = new ConexaoMySQL();
        TurmaProfessor turmaProfessor = null;
        String sql = "SELECT * FROM turma_professor WHERE id_turma_professor=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idTurmaProfessor);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                turmaProfessor = new TurmaProfessor();
                turmaProfessor.setIdTurmaProfessor(rs.getLong("id_turma_professor"));
                Turma turma = new TurmaDao().buscarPorId(rs.getLong("id_turma"));
                turmaProfessor.setTurma(turma);
                Professor professor = new ProfessorDao().buscarPorId(rs.getLong("id_professor"));
                turmaProfessor.setProfessor(professor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
        return turmaProfessor;
    }

    public List<TurmaProfessor> buscar() {
        conexao = new ConexaoMySQL();
        List<TurmaProfessor> listTurmaProfessor = new ArrayList();
        String sql = "SELECT * FROM turma_professor;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                TurmaProfessor turmaProfessor = new TurmaProfessor();
                turmaProfessor.setIdTurmaProfessor(rs.getLong("id_turma_professor"));
                Turma turma = new TurmaDao().buscarPorId(rs.getLong("id_turma"));
                turmaProfessor.setTurma(turma);
                Professor professor = new ProfessorDao().buscarPorId(rs.getLong("id_professor"));
                turmaProfessor.setProfessor(professor);
                listTurmaProfessor.add(turmaProfessor);
            }
        } catch(SQLException e) {
        }
        return listTurmaProfessor;
    }
}

