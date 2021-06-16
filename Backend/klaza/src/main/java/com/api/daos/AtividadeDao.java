package com.api.daos;

import com.api.entities.*;
import com.api.klaza.AtividadeController;
import com.api.util.ConversaoDeData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Repository
public class AtividadeDao {

    private static final Logger log = LoggerFactory.getLogger(AtividadeDao.class);
    ConexaoMySQL conexao;

    public Atividade adicionar(Atividade atividade) {
        conexao = new ConexaoMySQL();

        String sql = "INSERT INTO atividade VALUES(null, ?, ?, ?, ?);";
        try{
            PreparedStatement st = conexao.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, atividade.getNome());
            st.setString(2, atividade.getDescricao());
            Timestamp timestampInicio = ConversaoDeData.localDateTimeToTimestamp(atividade.getInicio());
            st.setTimestamp(3, timestampInicio);
            st.setLong(4, atividade.getIdTurma());
            st.executeUpdate();

            ResultSet rs = st.getGeneratedKeys();
            if(rs.next()) {

                atividade.setIdAtividade(rs.getLong(1));

                atividade.getProfessor().forEach(p -> new ProfessorAtividadeDao().adicionar(new ProfessorAtividade((long) -1, p, atividade)));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.conexao.closeConnection();
        }
        return atividade;
    }

    public void editar(Atividade atividade) {
        conexao = new ConexaoMySQL();

        String sql = "UPDATE atividade SET nome=?, descricao=?, inicio=?, id_turma=? WHERE id_atividade=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setString(1, atividade.getNome());
            st.setString(2, atividade.getDescricao());
            Timestamp timestampInicio = ConversaoDeData.localDateTimeToTimestamp(atividade.getInicio());
            st.setTimestamp(3, timestampInicio);
            st.setLong(4, atividade.getIdTurma());
            st.setLong(5, atividade.getIdAtividade());
            st.executeUpdate();

            List<ProfessorAtividade> professorAtividade = new ProfessorAtividadeDao().buscarPorIdAtividade(atividade.getIdAtividade());

            atividade.getProfessor().forEach(a -> { professorAtividade.forEach(pa -> { pa.setProfessor(a); new ProfessorAtividadeDao().editar(pa); }); } );

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
    }

    public void excluir(long idAtividade) {
        conexao = new ConexaoMySQL();
        String sql = "DELETE FROM atividade WHERE id_atividade=?;";

        try {

            new ProfessorAtividadeDao().buscarPorIdAtividade(idAtividade).forEach(pa -> new ProfessorAtividadeDao().excluir(pa.getIdProfessorAtividade()) );

            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idAtividade);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
    }

    public Atividade buscarPorId(long idAtividade) {
        conexao = new ConexaoMySQL();
        Atividade atividade = null;
        String sql = "SELECT * FROM atividade WHERE id_atividade=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idAtividade);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                atividade = new Atividade();
                atividade.setIdAtividade(rs.getLong("id_atividade"));
                atividade.setNome(rs.getString("nome"));
                atividade.setDescricao(rs.getString("descricao"));
                LocalDateTime ldtInicio = ConversaoDeData.timestampToLocalDateTime(rs.getTimestamp("inicio"));
                atividade.setInicio(ldtInicio);
                atividade.setIdTurma(rs.getLong("id_turma"));
                atividade.setProfessor(new ProfessorAtividadeDao().buscarProfessorPorIdAtividade(atividade.getIdAtividade()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
        return atividade;
    }

    public Atividade buscarPorIdSemTurma(long idAtividade) {
        conexao = new ConexaoMySQL();
        Atividade atividade = null;
        String sql = "SELECT * FROM atividade WHERE id_atividade=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idAtividade);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                atividade = new Atividade();
                atividade.setIdAtividade(rs.getLong("id_atividade"));
                atividade.setNome(rs.getString("nome"));
                atividade.setDescricao(rs.getString("descricao"));
                LocalDateTime ldtInicio = ConversaoDeData.timestampToLocalDateTime(rs.getTimestamp("inicio"));
                atividade.setInicio(ldtInicio);
                atividade.setIdTurma(rs.getLong("id_turma"));
                atividade.setMateria(new TurmaDao().buscarPorIdSemProfessor(atividade.getIdTurma()).getMateria());
                atividade.setProfessor(new ProfessorAtividadeDao().buscarProfessorPorIdAtividade(atividade.getIdAtividade()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
        return atividade;
    }

    public List<Atividade> buscar() {
        conexao = new ConexaoMySQL();
        List<Atividade> listAtividade = new ArrayList();
        String sql = "SELECT * FROM atividade;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                Atividade atividade = new Atividade();
                atividade.setIdAtividade(rs.getLong("id_atividade"));
                atividade.setNome(rs.getString("nome"));
                atividade.setDescricao(rs.getString("descricao"));
                LocalDateTime ldtInicio = ConversaoDeData.timestampToLocalDateTime(rs.getTimestamp("inicio"));
                atividade.setInicio(ldtInicio);
                atividade.setIdTurma(rs.getLong("id_turma"));
                atividade.setProfessor(new ProfessorAtividadeDao().buscarProfessorPorIdAtividade(atividade.getIdAtividade()));
                listAtividade.add(atividade);
            }
        } catch(SQLException e) {
        }
        return listAtividade;
    }

    public List<Atividade> buscarPorIdTurma(long idTurma) {

        conexao = new ConexaoMySQL();
        List<Atividade> listAtividade = new ArrayList();
        String sql = "SELECT * FROM atividade WHERE id_turma=?;";

        try {

            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idTurma);
            ResultSet rs = st.executeQuery();

            while(rs.next()) {

                listAtividade.add(new AtividadeDao().buscarPorId(rs.getLong("id_atividade")));

            }

        } catch(SQLException e) {}

        return listAtividade;

    }

    public List<Atividade> buscarPorIdTurmaSemTurma(long idTurma) {

        conexao = new ConexaoMySQL();
        List<Atividade> listAtividade = new ArrayList();
        String sql = "SELECT * FROM atividade WHERE id_turma=?;";

        try {

            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idTurma);
            ResultSet rs = st.executeQuery();

            while(rs.next()) {

                listAtividade.add(new AtividadeDao().buscarPorIdSemTurma(rs.getLong("id_atividade")));

            }

        } catch(SQLException e) {}

        return listAtividade;

    }

}
