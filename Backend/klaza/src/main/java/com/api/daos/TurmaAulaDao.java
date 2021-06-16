package com.api.daos;

import com.api.entities.Aluno;
import com.api.entities.Aula;
import com.api.entities.Turma;
import com.api.entities.TurmaAula;
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
public class TurmaAulaDao {

    private ConexaoMySQL conexao;

    public TurmaAula adicionar(TurmaAula turmaAula) {
        conexao = new ConexaoMySQL();

        String sql = "INSERT INTO turma_aula VALUES(null, ?, ?);";
        try{
            PreparedStatement st = conexao.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setLong(1, turmaAula.getTurma().getIdTurma());
            st.setLong(2, turmaAula.getAula().getIdAula());
            st.executeUpdate();

            ResultSet rs = st.getGeneratedKeys();
            if(rs.next()) {
                turmaAula = this.buscarPorId(rs.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.conexao.closeConnection();
        }
        return turmaAula;
    }

    public void editar(TurmaAula turmaAula) {
        conexao = new ConexaoMySQL();

        String sql = "UPDATE turma_aula SET id_turma=?, id_aula=? WHERE id_turma_aula=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, turmaAula.getTurma().getIdTurma());
            st.setLong(2, turmaAula.getAula().getIdAula());
            st.setLong(3, turmaAula.getIdTurmaAula());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
    }

    public void excluir(long idTurmaAula) {
        conexao = new ConexaoMySQL();
        String sql = "DELETE FROM turma_aula WHERE id_turma_aula=?;";

        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idTurmaAula);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
    }

    public TurmaAula buscarPorId(long idTurmaAula) {
        conexao = new ConexaoMySQL();
        TurmaAula turmaAula = null;
        String sql = "SELECT * FROM turma_aula WHERE id_turma_aula=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idTurmaAula);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                turmaAula = new TurmaAula();
                turmaAula.setIdTurmaAula(rs.getLong("id_turma_aula"));
                Turma turma = new TurmaDao().buscarPorId(rs.getLong("id_turma"));
                turmaAula.setTurma(turma);
                Aula aula = new AulaDao().buscarPorId(rs.getLong("id_aula"));
                turmaAula.setAula(aula);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
        return turmaAula;
    }

    public TurmaAula buscarPorIdAula(long idAula) {
        conexao = new ConexaoMySQL();
        TurmaAula turmaAula = null;
        String sql = "SELECT * FROM turma_aula WHERE id_aula=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idAula);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                turmaAula = new TurmaAula();
                turmaAula.setIdTurmaAula(rs.getLong("id_turma_aula"));
                Turma turma = new TurmaDao().buscarPorId(rs.getLong("id_turma"));
                turmaAula.setTurma(turma);
                Aula aula = new AulaDao().buscarPorId(rs.getLong("id_aula"));
                turmaAula.setAula(aula);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
        return turmaAula;
    }

    public List<TurmaAula> buscar() {
        conexao = new ConexaoMySQL();
        List<TurmaAula> listTurmaAula = new ArrayList();
        String sql = "SELECT * FROM turma_aula;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                TurmaAula turmaAula = new TurmaAula();
                turmaAula.setIdTurmaAula(rs.getLong("id_turma_aula"));
                Turma turma = new TurmaDao().buscarPorId(rs.getLong("id_turma"));
                turmaAula.setTurma(turma);
                Aula aula = new AulaDao().buscarPorId(rs.getLong("id_aula"));
                listTurmaAula.add(turmaAula);
            }
        } catch(SQLException e) {
        }
        return listTurmaAula;
    }

    public Turma buscarTurmaPorIdAula(long idAula) {

        conexao = new ConexaoMySQL();
        Turma turma = null;
        String sql = "SELECT * FROM turma_aula WHERE id_aula=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idAula);
            ResultSet rs = st.executeQuery();

            if(rs.next()) {

                turma = new TurmaDao().buscarPorId(rs.getLong("id_turma"));

            }

        } catch(SQLException e) {}

        return turma;

    }

    public Turma buscarTurmaPorIdAulaSemTurma(long idAula) {

        conexao = new ConexaoMySQL();
        Turma turma = null;
        String sql = "SELECT * FROM turma_aula WHERE id_aula=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idAula);
            ResultSet rs = st.executeQuery();

            while(rs.next()) {

                turma = new TurmaDao().buscarPorIdSemProfessor(rs.getLong("id_turma"));

            }

        } catch(SQLException e) {}

        return turma;

    }

    public List<Aula> buscarAulaPorIdTurma(long idTurma) {

        conexao = new ConexaoMySQL();
        List<Aula> listAula = new ArrayList();
        String sql = "SELECT * FROM turma_aula WHERE id_turma=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idTurma);
            ResultSet rs = st.executeQuery();

            while(rs.next()) {

                listAula.add(new AulaDao().buscarPorId(rs.getLong("id_aula")));

            }

        } catch(SQLException e) {}

        return listAula;

    }

    public List<Aula> buscarAulaPorIdTurmaSemTurma(long idTurma) {

        conexao = new ConexaoMySQL();
        List<Aula> listAula = new ArrayList();
        String sql = "SELECT * FROM turma_aula WHERE id_turma=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idTurma);
            ResultSet rs = st.executeQuery();

            while(rs.next()) {

                listAula.add(new AulaDao().buscarPorIdSemTurma(rs.getLong("id_aula")));

            }

        } catch(SQLException e) {}

        return listAula;

    }

}

