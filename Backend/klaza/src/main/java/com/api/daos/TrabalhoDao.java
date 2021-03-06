package com.api.daos;

import com.api.entities.Atividade;
import com.api.entities.ProfessorAtividade;
import com.api.entities.ProfessorTrabalho;
import com.api.klaza.TurmaController;
import com.api.util.ConversaoDeData;
import com.api.entities.Trabalho;
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
public class TrabalhoDao {

    private static final Logger log = LoggerFactory.getLogger(TrabalhoDao.class);
    private ConexaoMySQL conexao;

    public Trabalho adicionar(Trabalho trabalho) {
        conexao = new ConexaoMySQL();

        String sql = "INSERT INTO trabalho VALUES(null, ?, ?, ?, ?, ?, ?, ?, ?);";
        try{
            PreparedStatement st = conexao.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, trabalho.getNome());
            Timestamp timestampPrazo = ConversaoDeData.localDateTimeToTimestamp(trabalho.getPrazo());
            st.setTimestamp(2, timestampPrazo);
            st.setString(3, trabalho.getDescricao());
            Timestamp timestampInicio = ConversaoDeData.localDateTimeToTimestamp(trabalho.getInicio());
            st.setTimestamp(4, timestampInicio);
            st.setLong(5, trabalho.getTempo());
            st.setByte(6, trabalho.getTentativas());
            st.setByte(7, trabalho.getTipo());
            st.setLong(8, trabalho.getIdTurma());
            st.executeUpdate();

            ResultSet rs = st.getGeneratedKeys();
            if(rs.next()) {

               trabalho.setIdTrabalho(rs.getLong(1));

               trabalho.getProfessor().forEach(p -> new ProfessorTrabalhoDao().adicionar(new ProfessorTrabalho((long) -1, p, trabalho)));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.conexao.closeConnection();
        }
        return trabalho;
    }

    public void editar(Trabalho trabalho) {
        conexao = new ConexaoMySQL();

        String sql = "UPDATE trabalho SET nome=?, prazo=?, descricao=?, inicio=?, tempo=?, tentativas=?, tipo=?, id_turma=? WHERE id_trabalho=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setString(1, trabalho.getNome());
            Timestamp timestampPrazo = ConversaoDeData.localDateTimeToTimestamp(trabalho.getPrazo());
            st.setTimestamp(2, timestampPrazo);
            st.setString(3, trabalho.getDescricao());
            Timestamp timestampInicio = ConversaoDeData.localDateTimeToTimestamp(trabalho.getInicio());
            st.setTimestamp(4, timestampInicio);
            st.setLong(5, trabalho.getTempo());
            st.setByte(6, trabalho.getTentativas());
            st.setByte(7, trabalho.getTipo());
            st.setLong(8, trabalho.getIdTurma());
            st.setLong(9, trabalho.getIdTrabalho());
            st.executeUpdate();

            List<ProfessorTrabalho> professorTrabalho = new ProfessorTrabalhoDao().buscarPorIdTrabalho(trabalho.getIdTrabalho());

            trabalho.getProfessor().forEach(a -> { professorTrabalho.forEach(pt -> { pt.setProfessor(a); new ProfessorTrabalhoDao().editar(pt); }); } );

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
    }

    public void excluir(long idTrabalho) {
        conexao = new ConexaoMySQL();
        String sql = "DELETE FROM trabalho WHERE id_trabalho=?;";

        try {

//            log.info(new ProfessorTrabalhoDao().buscarPorIdTrabalho(idTrabalho).toString());

            new ProfessorTrabalhoDao().buscarPorIdTrabalho(idTrabalho).forEach(pt -> new ProfessorTrabalhoDao().excluir(pt.getIdProfessorTrabalho()) );

            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idTrabalho);
            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
    }

    public Trabalho buscarPorId(long idTrabalho) {
        conexao = new ConexaoMySQL();
        Trabalho trabalho = null;
        String sql = "SELECT * FROM trabalho WHERE id_trabalho=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idTrabalho);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                trabalho = new Trabalho();
                trabalho.setIdTrabalho(rs.getLong("id_trabalho"));
                trabalho.setNome(rs.getString("nome"));
                LocalDateTime ldtPrazo = ConversaoDeData.timestampToLocalDateTime(rs.getTimestamp("prazo"));
                trabalho.setPrazo(ldtPrazo);
                trabalho.setDescricao(rs.getString("descricao"));
                LocalDateTime ldtInicio = ConversaoDeData.timestampToLocalDateTime(rs.getTimestamp("inicio"));
                trabalho.setInicio(ldtInicio);
                trabalho.setTempo(rs.getLong("tempo"));
                trabalho.setTentativas(rs.getByte("tentativas"));
                trabalho.setTipo(rs.getByte("tipo"));
                trabalho.setIdTurma(rs.getLong("id_turma"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
        return trabalho;
    }

    public Trabalho buscarPorIdSemTurma(long idTrabalho) {
        conexao = new ConexaoMySQL();
        Trabalho trabalho = null;
        String sql = "SELECT * FROM trabalho WHERE id_trabalho=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idTrabalho);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                trabalho = new Trabalho();
                trabalho.setIdTrabalho(rs.getLong("id_trabalho"));
                trabalho.setNome(rs.getString("nome"));
                LocalDateTime ldtPrazo = ConversaoDeData.timestampToLocalDateTime(rs.getTimestamp("prazo"));
                trabalho.setPrazo(ldtPrazo);
                trabalho.setDescricao(rs.getString("descricao"));
                LocalDateTime ldtInicio = ConversaoDeData.timestampToLocalDateTime(rs.getTimestamp("inicio"));
                trabalho.setInicio(ldtInicio);
                trabalho.setTempo(rs.getLong("tempo"));
                trabalho.setTentativas(rs.getByte("tentativas"));
                trabalho.setTipo(rs.getByte("tipo"));
                trabalho.setIdTurma(rs.getLong("id_turma"));
                trabalho.setMateria(new TurmaDao().buscarPorIdSemProfessor(trabalho.getIdTurma()).getMateria());
                trabalho.setProfessor(new ProfessorTrabalhoDao().buscarProfessorPorIdTrabalho(trabalho.getIdTrabalho()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
        return trabalho;
    }

    public List<Trabalho> buscar() {
        conexao = new ConexaoMySQL();
        List<Trabalho> listTrabalho = new ArrayList();
        String sql = "SELECT * FROM trabalho;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                Trabalho trabalho = new Trabalho();
                trabalho.setIdTrabalho(rs.getLong("id_trabalho"));
                trabalho.setNome(rs.getString("nome"));
                LocalDateTime ldtPrazo = ConversaoDeData.timestampToLocalDateTime(rs.getTimestamp("prazo"));
                trabalho.setPrazo(ldtPrazo);
                trabalho.setDescricao(rs.getString("descricao"));
                LocalDateTime ldtInicio = ConversaoDeData.timestampToLocalDateTime(rs.getTimestamp("inicio"));
                trabalho.setInicio(ldtInicio);
                trabalho.setTempo(rs.getLong("tempo"));
                trabalho.setTentativas(rs.getByte("tentativas"));
                trabalho.setTipo(rs.getByte("tipo"));
                trabalho.setIdTurma(rs.getLong("id_turma"));
                listTrabalho.add(trabalho);
            }
        } catch(SQLException e) {
        }
        return listTrabalho;
    }

    public List<Trabalho> buscarPorIdTurma(long idTurma) {

        conexao = new ConexaoMySQL();
        List<Trabalho> listTrabalho = new ArrayList();
        String sql = "SELECT * FROM trabalho WHERE id_turma=?;";

        try {

            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idTurma);
            ResultSet rs = st.executeQuery();

            while(rs.next()) {

                listTrabalho.add(new TrabalhoDao().buscarPorId(rs.getLong("id_trabalho")));

            }

        } catch(SQLException e) {}

        return listTrabalho;

    }

    public List<Trabalho> buscarPorIdTurmaSemTurma(long idTurma) {

        conexao = new ConexaoMySQL();
        List<Trabalho> listTrabalho = new ArrayList();
        String sql = "SELECT * FROM trabalho WHERE id_turma=?;";

        try {

            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idTurma);
            ResultSet rs = st.executeQuery();

            while(rs.next()) {

                listTrabalho.add(new TrabalhoDao().buscarPorIdSemTurma(rs.getLong("id_trabalho")));

            }

        } catch(SQLException e) {
            e.printStackTrace();
        }
        finally {
            conexao.closeConnection();
        }

        return listTrabalho;

    }

}
