package com.api.daos;

import com.api.entities.Turma;
import com.api.entities.TurmaAula;
import com.api.klaza.AulaController;
import com.api.util.ConversaoDeData;
import com.api.entities.Aula;
import com.api.entities.Materia;
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
public class AulaDao {

    private static final Logger log = LoggerFactory.getLogger(AulaDao.class);
    ConexaoMySQL conexao;

    public Aula adicionar(Aula aula) {
        conexao = new ConexaoMySQL();

        String sqlAula = "INSERT INTO aula VALUES(null, ?, ?, ?, ?, ?);";

        try{
            PreparedStatement st = conexao.getConexao().prepareStatement(sqlAula, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, aula.getNome());
            st.setLong(2, aula.getMateria().getIdMateria());
            st.setString(3, aula.getDescricao());
            Timestamp timestampInicio = ConversaoDeData.localDateTimeToTimestamp(aula.getInicio());
            st.setTimestamp(4, timestampInicio);
            st.setString(5, aula.getLink());
            st.executeUpdate();

            ResultSet rs = st.getGeneratedKeys();
            if(rs.next()) {

                aula.setIdAula(rs.getLong(1));

                Turma turma = new Turma();
                turma.setIdTurma(aula.getIdturma());

                new TurmaAulaDao().adicionar(new TurmaAula((long) -1, turma, aula));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.conexao.closeConnection();
        }
        return aula;
    }

    public void editar(Aula aula) {
        conexao = new ConexaoMySQL();

        String sql = "UPDATE aula SET nome=?, id_materia=?, descricao=?, inicio=?, link=? WHERE id_aula=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setString(1, aula.getNome());
            st.setLong(2, aula.getMateria().getIdMateria());
            st.setString(3, aula.getDescricao());
            Timestamp timestampInicio = ConversaoDeData.localDateTimeToTimestamp(aula.getInicio());
            st.setTimestamp(4, timestampInicio);
            st.setString(5, aula.getLink());
            st.setLong(6, aula.getIdAula());

            Turma turma = new Turma();
            turma.setIdTurma(aula.getIdturma());

            TurmaAula turmaAula = new TurmaAulaDao().buscarPorIdAula(aula.getIdAula());
            turmaAula.setTurma(turma);

            new TurmaAulaDao().editar(turmaAula);

            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
    }

    public void excluir(long idAula) {
        conexao = new ConexaoMySQL();
        String sql = "DELETE FROM aula WHERE id_aula=?;";

        try {

            new TurmaAulaDao().excluir(new TurmaAulaDao().buscarPorIdAula(idAula).getIdTurmaAula());

            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idAula);
            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
    }

    public Aula buscarPorId(long idAula) {
        conexao = new ConexaoMySQL();
        Aula aula = null;
        String sql = "SELECT * FROM aula WHERE id_aula=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idAula);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                aula = new Aula();
                aula.setIdAula(rs.getLong("id_aula"));
                aula.setNome(rs.getString("nome"));
                Materia materia = new MateriaDao().buscarPorId(rs.getLong("id_materia"));
                aula.setMateria(materia);
                aula.setDescricao(rs.getString("descricao"));
                LocalDateTime ldtInicio = ConversaoDeData.timestampToLocalDateTime(rs.getTimestamp("inicio"));
                aula.setInicio(ldtInicio);
                aula.setLink(rs.getString("link"));
                aula.setIdturma(new TurmaAulaDao().buscarTurmaPorIdAula(aula.getIdAula()).getIdTurma());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
        return aula;
    }

    public Aula buscarPorIdSemTurma(long idAula) {
        conexao = new ConexaoMySQL();
        Aula aula = null;
        String sql = "SELECT * FROM aula WHERE id_aula=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idAula);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                aula = new Aula();
                aula.setIdAula(rs.getLong("id_aula"));
                aula.setNome(rs.getString("nome"));
                aula.setMateria(new MateriaDao().buscarPorIdSemProfessor(rs.getLong("id_materia")));
                aula.setDescricao(rs.getString("descricao"));
                LocalDateTime ldtInicio = ConversaoDeData.timestampToLocalDateTime(rs.getTimestamp("inicio"));
                aula.setInicio(ldtInicio);
                aula.setLink(rs.getString("link"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
        return aula;
    }

    public List<Aula> buscar() {
        conexao = new ConexaoMySQL();
        List<Aula> listAula = new ArrayList();
        String sql = "SELECT * FROM aula;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {

                Aula aula = new Aula();
                aula.setIdAula(rs.getLong("id_aula"));
                aula.setNome(rs.getString("nome"));
                Materia materia = new MateriaDao().buscarPorId(rs.getLong("id_materia"));
                aula.setMateria(materia);
                aula.setDescricao(rs.getString("descricao"));
                LocalDateTime ldtInicio = ConversaoDeData.timestampToLocalDateTime(rs.getTimestamp("inicio"));
                aula.setInicio(ldtInicio);
                aula.setLink(rs.getString("link"));
                aula.setIdturma(new TurmaAulaDao().buscarTurmaPorIdAula(aula.getIdAula()).getIdTurma());

                listAula.add(aula);
            }
        } catch(SQLException e) {}
        finally {
            conexao.closeConnection();
        }
        return listAula;
    }

}
