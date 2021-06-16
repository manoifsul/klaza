package com.api.daos;

import com.api.entities.Aluno;
import com.api.entities.Turma;
import com.api.entities.TurmaAluno;
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
public class TurmaAlunoDao {

    private ConexaoMySQL conexao;

    public TurmaAluno adicionar(TurmaAluno turmaAluno) {

        if (this.buscarTurmaPorIdAluno(turmaAluno.getAluno().getIdAluno()).isEmpty()) {

            conexao = new ConexaoMySQL();

            String sql = "INSERT INTO turma_aluno VALUES(null, ?, ?);";
            try {
                PreparedStatement st = conexao.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                st.setLong(1, turmaAluno.getTurma().getIdTurma());
                st.setLong(2, turmaAluno.getAluno().getIdAluno());
                st.executeUpdate();

                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    turmaAluno.setIdTurmaAluno(rs.getLong(1));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                this.conexao.closeConnection();
            }

        }

        return turmaAluno;
    }

    public void editar(TurmaAluno turmaAluno) {
        conexao = new ConexaoMySQL();

        String sql = "UPDATE turma_aluno SET id_turma=?, id_aluno=? WHERE id_turma_aluno=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, turmaAluno.getTurma().getIdTurma());
            st.setLong(2, turmaAluno.getAluno().getIdAluno());
            st.setLong(3, turmaAluno.getIdTurmaAluno());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
    }

    public void excluir(long idTurmaAluno) {
        conexao = new ConexaoMySQL();
        String sql = "DELETE FROM turma_aluno WHERE id_turma_aluno=?;";

        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idTurmaAluno);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
    }

    public TurmaAluno buscarPorId(long idTurmaAluno) {
        conexao = new ConexaoMySQL();
        TurmaAluno turmaAluno = null;
        String sql = "SELECT * FROM turma_aluno WHERE id_turma_aluno=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idTurmaAluno);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                turmaAluno = new TurmaAluno();
                turmaAluno.setIdTurmaAluno(rs.getLong("id_turma_aluno"));
                Turma turma = new TurmaDao().buscarPorId(rs.getLong("id_turma"));
                turmaAluno.setTurma(turma);
                Aluno aluno = new AlunoDao().buscarPorId(rs.getLong("id_aluno"));
                turmaAluno.setAluno(aluno);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
        return turmaAluno;
    }

    public List<TurmaAluno> buscar() {
        conexao = new ConexaoMySQL();
        List<TurmaAluno> listTurmaAluno = new ArrayList();
        String sql = "SELECT * FROM turma_aluno;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                TurmaAluno turmaAluno = new TurmaAluno();
                turmaAluno.setIdTurmaAluno(rs.getLong("id_turma_aluno"));
                Turma turma = new TurmaDao().buscarPorId(rs.getLong("id_turma"));
                turmaAluno.setTurma(turma);
                Aluno aluno = new AlunoDao().buscarPorId(rs.getLong("id_aluno"));
                turmaAluno.setAluno(aluno);
                listTurmaAluno.add(turmaAluno);
            }
        } catch(SQLException e) {
        }
        return listTurmaAluno;
    }

    public List<TurmaAluno> buscarPorIdAluno(long idAluno) {
        conexao = new ConexaoMySQL();
        List<TurmaAluno> listTurmaAluno = new ArrayList();
        String sql = "SELECT * FROM turma_aluno WHERE id_aluno=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idAluno);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                TurmaAluno turmaAluno = new TurmaAluno();
                turmaAluno.setIdTurmaAluno(rs.getLong("id_turma_aluno"));
                Turma turma = new TurmaDao().buscarPorId(rs.getLong("id_turma"));
                turmaAluno.setTurma(turma);
                Aluno aluno = new AlunoDao().buscarPorId(rs.getLong("id_aluno"));
                turmaAluno.setAluno(aluno);
                listTurmaAluno.add(turmaAluno);
            }
        } catch(SQLException e) {
        }
        return listTurmaAluno;
    }

    public List<TurmaAluno> buscarPorIdTurma(long idTurma) {
        conexao = new ConexaoMySQL();
        List<TurmaAluno> listTurmaAluno = new ArrayList();
        String sql = "SELECT * FROM turma_aluno WHERE id_turma=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idTurma);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                TurmaAluno turmaAluno = new TurmaAluno();
                turmaAluno.setIdTurmaAluno(rs.getLong("id_turma_aluno"));
                Turma turma = new TurmaDao().buscarPorId(rs.getLong("id_turma"));
                turmaAluno.setTurma(turma);
                Aluno aluno = new AlunoDao().buscarPorId(rs.getLong("id_aluno"));
                turmaAluno.setAluno(aluno);
                listTurmaAluno.add(turmaAluno);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        finally {
            conexao.closeConnection();
        }

        return listTurmaAluno;
    }

    public List<Aluno> buscarAlunoPorIdTurma(long idTurma) {

        conexao = new ConexaoMySQL();
        List<Aluno> listAluno = new ArrayList();
        String sql = "SELECT * FROM turma_aluno WHERE id_turma=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idTurma);
            ResultSet rs = st.executeQuery();

            while(rs.next()) {

                listAluno.add(new AlunoDao().buscarPorId(rs.getLong("id_aluno")));

            }

        } catch(SQLException e) {
        }

        return listAluno;

    }

    public List<Aluno> buscarAlunoPorIdTurmaSemTurma(long idTurma) {

        conexao = new ConexaoMySQL();
        List<Aluno> listAluno = new ArrayList();
        String sql = "SELECT * FROM turma_aluno WHERE id_turma=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idTurma);
            ResultSet rs = st.executeQuery();

            while(rs.next()) {

                listAluno.add(new AlunoDao().buscarPorIdSemTurma(rs.getLong("id_aluno")));

            }

        } catch(SQLException e) {
        }

        return listAluno;

    }

    public List<Turma> buscarTurmaPorIdAluno(long idAluno) {

        conexao = new ConexaoMySQL();
        List<Turma> listTurma = new ArrayList();
        String sql = "SELECT * FROM turma_aluno WHERE id_aluno=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idAluno);
            ResultSet rs = st.executeQuery();

            while(rs.next()) {

                listTurma.add(new TurmaDao().buscarPorId(rs.getLong("id_aluno")));

            }

        } catch(SQLException e) {
        }

        return listTurma;

    }

    public List<Turma> buscarTurmaPorIdAlunoSemAluno(long idAluno) {

        conexao = new ConexaoMySQL();
        List<Turma> listTurma = new ArrayList();
        String sql = "SELECT * FROM turma_aluno WHERE id_aluno=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idAluno);
            ResultSet rs = st.executeQuery();

            while(rs.next()) {

                listTurma.add(new TurmaDao().buscarPorIdSemAluno(rs.getLong("id_turma")));

            }

        } catch(SQLException e) {
        }

        return listTurma;

    }

}

