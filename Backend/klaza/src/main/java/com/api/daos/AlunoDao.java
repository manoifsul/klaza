package com.api.daos;

import com.api.entities.Aluno;
import com.api.entities.Professor;
import com.api.entities.Turma;
import com.api.entities.TurmaAluno;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class AlunoDao {

    private static final Logger log = LoggerFactory.getLogger(AlunoDao.class);
    private ConexaoMySQL conexao;

    public Aluno adicionar(Aluno aluno) {
        conexao = new ConexaoMySQL();
        String sql = "INSERT INTO aluno VALUES(null, ?, ?, ?, ?);";
        try{
            PreparedStatement st = conexao.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, aluno.getNome());
            st.setString(2, aluno.getMatricula());
            st.setString(3, aluno.getSenha());
            st.setString(4, aluno.getEmail());
            st.executeUpdate();

            ResultSet rs = st.getGeneratedKeys();
            if(rs.next()) {

                aluno.setIdAluno(rs.getLong(1));

                Turma turma = new Turma();

                aluno.getIdTurmas().forEach(t -> { turma.setIdTurma(t); new TurmaAlunoDao().adicionar(new TurmaAluno((long) -1, turma, aluno)); });

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

            Turma turma = new Turma();

            aluno.getIdTurmas().forEach(t -> { turma.setIdTurma(t); new TurmaAlunoDao().adicionar(new TurmaAluno((long) -1, turma, aluno)); } );

            log.info(aluno.getIdAluno()+"");

            new TurmaAlunoDao().buscarPorIdAluno(aluno.getIdAluno()).forEach(ta -> {

                log.info(ta.toString());

                if (!aluno.getIdTurmas().contains(ta.getTurma().getIdTurma())) { new TurmaAlunoDao().excluir(ta.getIdTurmaAluno()); }

            });

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
    }

    public void excluir(long idAluno) {
        conexao = new ConexaoMySQL();
        String sql = "DELETE FROM aluno WHERE id_aluno=?;";

        new TurmaAlunoDao().buscarPorIdAluno(idAluno).forEach(ta -> new TurmaAlunoDao().excluir(ta.getIdTurmaAluno()));

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
                aluno.setIdTurmas(new TurmaAlunoDao().buscarTurmaPorIdAlunoSemAluno(aluno.getIdAluno()).stream().map(t -> t.getIdTurma()).toList());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
        return aluno;
    }

    public Aluno buscarPorIdSemTurma(long idAluno) {
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
                aluno.setIdTurmas(new TurmaAlunoDao().buscarTurmaPorIdAluno(aluno.getIdAluno()).stream().map(t -> t.getIdTurma()).toList());
                listAluno.add(aluno);
            }
        } catch(SQLException e) {
        }
        return listAluno;
    }

    public List<Aluno> buscarSemTurma() {
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
                aluno.setIdTurmas(new TurmaAlunoDao().buscarTurmaPorIdAlunoSemAluno(aluno.getIdAluno()).stream().map(t -> t.getIdTurma()).toList());
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
//                aluno.setIdTurmas(new TurmaAlunoDao().buscarTurmaPorIdAluno(aluno.getIdAluno()).stream().map(t -> t.getIdTurma()).toList());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
        return aluno;
    }
}

