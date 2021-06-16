package com.api.daos;

import com.api.entities.*;
import com.api.klaza.TurmaController;
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
public class TurmaDao {

    private static final Logger log = LoggerFactory.getLogger(TurmaDao.class);
    private ConexaoMySQL conexao;

    public Turma adicionar(Turma turma) {
        conexao = new ConexaoMySQL();

        String sql = "INSERT INTO turma VALUES(null, ?, ?, ?);";
        try{
            PreparedStatement st = conexao.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, turma.getNome());
            st.setLong(2, turma.getMateria().getIdMateria());
            st.setLong(3, turma.getDiscord().getIdDiscord());
            st.executeUpdate();

            ResultSet rs = st.getGeneratedKeys();
            if(rs.next()) {

                turma.setIdTurma(rs.getLong(1));

                turma.getProfessor().forEach(p -> new TurmaProfessorDao().adicionar(new TurmaProfessor((long) -1, turma, p)));

                turma.getAluno().forEach(a -> new TurmaAlunoDao().adicionar(new TurmaAluno((long) -1, turma, a)));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.conexao.closeConnection();
        }
        return turma;
    }

    public void editar(Turma turma) {
        conexao = new ConexaoMySQL();

        String sql = "UPDATE turma SET nome=?, id_materia=?, id_discord=? WHERE id_turma=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setString(1, turma.getNome());
            st.setLong(2, turma.getMateria().getIdMateria());
            st.setLong(3, turma.getDiscord().getIdDiscord());
            st.setLong(4, turma.getIdTurma());
            st.executeUpdate();

            turma.getProfessor().forEach(p -> new TurmaProfessorDao().adicionar(new TurmaProfessor((long) -1, turma, p)));
            new TurmaProfessorDao().buscarPorIdTurma(turma.getIdTurma()).forEach(tp -> {

                if (!turma.getProfessor().stream().map(p -> p.getIdProfessor()).toList().contains(tp.getProfessor().getIdProfessor())) { new TurmaProfessorDao().excluir(tp.getIdTurmaProfessor()); }

            });

            log.info(turma.getAluno().toString());

            turma.getAluno().forEach(a -> new TurmaAlunoDao().adicionar(new TurmaAluno((long) -1, turma, a)));
            new TurmaAlunoDao().buscarPorIdTurma(turma.getIdTurma()).forEach(ta -> {

                if (!turma.getAluno().stream().map(a -> a.getIdAluno()).toList().contains(ta.getAluno().getIdAluno())) { new TurmaAlunoDao().excluir(ta.getIdTurmaAluno());}

            });

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
    }

    public void excluir(long idTurma) {
        conexao = new ConexaoMySQL();
        String sql = "DELETE FROM turma WHERE id_turma=?;";

        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idTurma);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
    }

    public Turma buscarPorId(long idTurma) {
        conexao = new ConexaoMySQL();
        Turma turma = null;
        String sql = "SELECT * FROM turma WHERE id_turma=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idTurma);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                turma = new Turma();
                turma.setIdTurma(rs.getLong("id_turma"));
                turma.setNome(rs.getString("nome"));
                Materia materia = new MateriaDao().buscarPorId(rs.getLong("id_materia"));
                turma.setMateria(materia);
                Discord discord = new DiscordDao().buscarPorId(rs.getLong("id_discord"));
                turma.setDiscord(discord);
                turma.setProfessor(new TurmaProfessorDao().buscarProfessorPorIdTurmaSemTurma(turma.getIdTurma()));
                turma.setAluno(new TurmaAlunoDao().buscarAlunoPorIdTurma(turma.getIdTurma()));
                turma.setAula(new TurmaAulaDao().buscarAulaPorIdTurmaSemTurma(turma.getIdTurma()));
                turma.setAtividade(new AtividadeDao().buscarPorIdTurmaSemTurma(turma.getIdTurma()));
                turma.setTrabalho(new TrabalhoDao().buscarPorIdTurmaSemTurma(turma.getIdTurma()));
                turma.setProva(new ProvaDao().buscarPorIdTurmaSemTurma(turma.getIdTurma()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
        return turma;
    }

    public List<Turma> buscar() {
        conexao = new ConexaoMySQL();
        List<Turma> listTurma = new ArrayList();
        String sql = "SELECT * FROM turma;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                Turma turma = new Turma();
                turma.setIdTurma(rs.getLong("id_turma"));
                turma.setNome(rs.getString("nome"));
                Materia materia = new MateriaDao().buscarPorId(rs.getLong("id_materia"));
                turma.setMateria(materia);
                Discord discord = new DiscordDao().buscarPorId(rs.getLong("id_discord"));
                turma.setDiscord(discord);
                turma.setProfessor(new TurmaProfessorDao().buscarProfessorPorIdTurmaSemTurma(turma.getIdTurma()));
                turma.setAluno(new TurmaAlunoDao().buscarAlunoPorIdTurmaSemTurma(turma.getIdTurma()));
                turma.setAula(new TurmaAulaDao().buscarAulaPorIdTurmaSemTurma(turma.getIdTurma()));
                turma.setAtividade(new AtividadeDao().buscarPorIdTurmaSemTurma(turma.getIdTurma()));
                turma.setTrabalho(new TrabalhoDao().buscarPorIdTurmaSemTurma(turma.getIdTurma()));
                turma.setProva(new ProvaDao().buscarPorIdTurmaSemTurma(turma.getIdTurma()));
                listTurma.add(turma);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        finally {
            this.conexao.closeConnection();
        }
        return listTurma;
    }

    public Turma buscarPorIdSemProfessor(long idTurma) {
        conexao = new ConexaoMySQL();
        Turma turma = null;
        String sql = "SELECT * FROM turma WHERE id_turma=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idTurma);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                turma = new Turma();
                turma.setIdTurma(rs.getLong("id_turma"));
                turma.setNome(rs.getString("nome"));
                Materia materia = new MateriaDao().buscarPorIdSemProfessor(rs.getLong("id_materia"));
                turma.setMateria(materia);
                Discord discord = new DiscordDao().buscarPorId(rs.getLong("id_discord"));
                turma.setDiscord(discord);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
        return turma;
    }

    public Turma buscarPorIdSemAluno(long idTurma) {
        conexao = new ConexaoMySQL();
        Turma turma = null;
        String sql = "SELECT * FROM turma WHERE id_turma=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idTurma);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                turma = new Turma();
                turma.setIdTurma(rs.getLong("id_turma"));
                turma.setNome(rs.getString("nome"));
                Materia materia = new MateriaDao().buscarPorIdSemProfessor(rs.getLong("id_materia"));
                turma.setMateria(materia);
                Discord discord = new DiscordDao().buscarPorId(rs.getLong("id_discord"));
                turma.setDiscord(discord);
                turma.setAluno(new TurmaAlunoDao().buscarAlunoPorIdTurmaSemTurma(idTurma));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
        return turma;
    }

}
