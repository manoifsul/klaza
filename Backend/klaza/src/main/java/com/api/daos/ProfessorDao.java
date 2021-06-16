package com.api.daos;

import com.api.entities.MateriaProfessor;
import com.api.entities.Professor;
import com.api.entities.Turma;
import com.api.entities.TurmaProfessor;
import com.api.klaza.ProfessorController;
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
public class ProfessorDao {

    private static final Logger log = LoggerFactory.getLogger(ProfessorDao.class);
    private ConexaoMySQL conexao;

    public Professor adicionar(Professor professor) {
        conexao = new ConexaoMySQL();

        String sql = "INSERT INTO professor VALUES(null, ?, ?, ?, ?);";
        try{
            PreparedStatement st = conexao.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, professor.getNome());
            st.setString(2, professor.getMatricula());
            st.setString(3, professor.getSenha());
            st.setString(4, professor.getEmail());
            st.executeUpdate();

            ResultSet rs = st.getGeneratedKeys();
            if(rs.next()) {

                professor.setIdProfessor(rs.getLong(1));

                Turma turma = new Turma();

                professor.getMateria().forEach(m -> { new MateriaProfessorDao().adicionar(new MateriaProfessor((long) -1, m, professor)); } );
                professor.getIdTurmas().forEach(t -> { turma.setIdTurma(t); new TurmaProfessorDao().adicionar(new TurmaProfessor((long) -1, turma, professor)); } );

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

            Turma turma = new Turma();
            MateriaProfessorDao materiaProfessorDao =  new MateriaProfessorDao();
            TurmaProfessorDao turmaProfessorDao = new TurmaProfessorDao();

            professor.getMateria().forEach(m -> { materiaProfessorDao.adicionar(new MateriaProfessor((long) -1, m, professor)); } );
            materiaProfessorDao.buscarPorIdProfessor(professor.getIdProfessor()).forEach(m -> {

                if (!professor.getMateria().stream().map(mId -> mId.getIdMateria()).toList().contains(m.getMateria().getIdMateria())) { new MateriaProfessorDao().excluir(m.getIdMateriaProfessor()); }

            });

            professor.getIdTurmas().forEach(t -> { turma.setIdTurma(t); new TurmaProfessorDao().adicionar(new TurmaProfessor((long) -1, turma, professor)); } );
            turmaProfessorDao.buscarPorIdProfessor(professor.getIdProfessor()).forEach(t -> {

                if (!professor.getIdTurmas().contains(t.getTurma().getIdTurma())) { new TurmaProfessorDao().excluir(t.getIdTurmaProfessor()); }

            });


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
    }

    public void excluir(long idProfessor) {
        conexao = new ConexaoMySQL();
        String sql = "DELETE FROM professor WHERE id_professor=?;";

        new MateriaProfessorDao().buscarPorIdProfessor(idProfessor).forEach(m -> new MateriaProfessorDao().excluir(m.getIdMateriaProfessor()));
        new TurmaProfessorDao().buscarPorIdProfessor(idProfessor).forEach(t -> new TurmaProfessorDao().excluir(t.getIdTurmaProfessor()));

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
                professor.setMateria(new MateriaProfessorDao().buscarMateriaPorIdProfessorSemProfessor(professor.getIdProfessor()));
                professor.setIdTurmas(new TurmaProfessorDao().buscarTurmaPorIdProfessorSemProfessor(professor.getIdProfessor()).stream().map(p -> p.getIdTurma()).toList());
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

            MateriaProfessorDao materiaProfessorDao = new MateriaProfessorDao();
            TurmaProfessorDao turmaProfessorDao = new TurmaProfessorDao();

            while(rs.next()) {
                Professor professor = new Professor();
                professor.setIdProfessor(rs.getLong("id_professor"));
                professor.setNome(rs.getString("nome"));
                professor.setMatricula(rs.getString("matricula"));
                professor.setSenha(rs.getString("senha"));
                professor.setEmail(rs.getString("email"));
                professor.setMateria(materiaProfessorDao.buscarMateriaPorIdProfessor(professor.getIdProfessor()));
                professor.setIdTurmas(turmaProfessorDao.buscarTurmaPorIdProfessorSemProfessor(professor.getIdProfessor()).stream().map(p -> p.getIdTurma()).toList());
                listProfessor.add(professor);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        finally {
            conexao.closeConnection();
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
                professor.setIdProfessor(rs.getLong("id_professor"));
                professor.setNome(rs.getString("nome"));
                professor.setMatricula(rs.getString("matricula"));
                professor.setSenha(rs.getString("senha"));
                professor.setEmail(rs.getString("email"));
                professor.setMateria(new MateriaProfessorDao().buscarMateriaPorIdProfessorSemProfessor(professor.getIdProfessor()));
                professor.setIdTurmas(new TurmaProfessorDao().buscarTurmaPorIdProfessorSemProfessor(professor.getIdProfessor()).stream().map(p -> p.getIdTurma()).toList());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
        return professor;
    }

    public Professor buscarPorIdSemTurma(long idProfessor) {
        conexao = new ConexaoMySQL();
        Professor professor = null;
        String sql = "SELECT * FROM professor WHERE id_professor=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idProfessor);
            ResultSet rs = st.executeQuery();

            MateriaProfessorDao materiaProfessorDao = new MateriaProfessorDao();

            if(rs.next()){
                professor = new Professor();
                professor.setIdProfessor(rs.getLong("id_professor"));
                professor.setNome(rs.getString("nome"));
                professor.setMatricula(rs.getString("matricula"));
                professor.setSenha(rs.getString("senha"));
                professor.setEmail(rs.getString("email"));
                professor.setMateria(materiaProfessorDao.buscarMateriaPorIdProfessorSemProfessor(professor.getIdProfessor()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
        return professor;
    }

}

