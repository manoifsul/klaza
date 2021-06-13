package com.api.daos;

import com.api.util.ConversaoDeData;
import com.api.entities.Aluno;
import com.api.entities.AlunoProva;
import com.api.entities.Prova;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Repository
public class AlunoProvaDao {

    private ConexaoMySQL conexao;

    public AlunoProva adicionar(AlunoProva alunoProva) {
        conexao = new ConexaoMySQL();

        String sql = "INSERT INTO aluno_prova VALUES(null, ?, ?, ?, ?, ?);";
        try{
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, alunoProva.getTempo());
            Timestamp timestampInicio = ConversaoDeData.localDateTimeToTimestamp(alunoProva.getInicio());
            st.setTimestamp(2, timestampInicio);
            Timestamp timestampFinalizada = ConversaoDeData.localDateTimeToTimestamp(alunoProva.getFinalizada());
            st.setTimestamp(3, timestampFinalizada);
            st.setLong(4, alunoProva.getAluno().getIdAluno());
            st.setLong(5, alunoProva.getProva().getIdProva());
            st.executeUpdate();

            ResultSet rs = st.getGeneratedKeys();
            if(rs.next()) {
                alunoProva = this.buscarPorId(rs.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.conexao.closeConnection();
        }
        return alunoProva;
    }

    public void editar(AlunoProva alunoProva) {
        conexao = new ConexaoMySQL();

        String sql = "UPDATE aluno_prova SET tempo=?, inicio=?, finalizada=?, id_aluno=?, id_prova=? WHERE id_aluno_prova=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, alunoProva.getTempo());
            Timestamp timestampInicio = ConversaoDeData.localDateTimeToTimestamp(alunoProva.getInicio());
            st.setTimestamp(2, timestampInicio);
            Timestamp timestampFinalizada = ConversaoDeData.localDateTimeToTimestamp(alunoProva.getFinalizada());
            st.setTimestamp(3, timestampFinalizada);
            st.setLong(4, alunoProva.getAluno().getIdAluno());
            st.setLong(5, alunoProva.getProva().getIdProva());
            st.setLong(6, alunoProva.getIdAlunoProva());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
    }

    public void excluir(long idAlunoProva) {
        conexao = new ConexaoMySQL();
        String sql = "DELETE FROM aluno_prova WHERE id_aluno_prova=?;";

        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idAlunoProva);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
    }

    public AlunoProva buscarPorId(long idAlunoProva) {
        conexao = new ConexaoMySQL();
        AlunoProva alunoProva = null;
        String sql = "SELECT * FROM aluno_prova WHERE id_aluno_prova=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idAlunoProva);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                alunoProva = new AlunoProva();
                alunoProva.setIdAlunoProva(rs.getLong("id_aluno_prova"));
                alunoProva.setTempo(rs.getLong("tempo"));
                LocalDateTime ldtInicio = ConversaoDeData.timestampToLocalDateTime(rs.getTimestamp("inicio"));
                alunoProva.setInicio(ldtInicio);
                LocalDateTime ldtFinalizada = ConversaoDeData.timestampToLocalDateTime(rs.getTimestamp("finalizada"));
                alunoProva.setInicio(ldtFinalizada);
                Aluno aluno = new AlunoDao().buscarPorId(rs.getLong("id_aluno"));
                alunoProva.setAluno(aluno);
                Prova prova = new ProvaDao().buscarPorId(rs.getLong("id_prova"));
                alunoProva.setProva(prova);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
        return alunoProva;
    }

    public List<AlunoProva> buscar() {
        conexao = new ConexaoMySQL();
        List<AlunoProva> listAlunoProva = new ArrayList();
        String sql = "SELECT * FROM aluno_prova;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                AlunoProva alunoProva = new AlunoProva();
                alunoProva.setIdAlunoProva(rs.getLong("id_aluno_prova"));
                alunoProva.setTempo(rs.getLong("tempo"));
                LocalDateTime ldtInicio = ConversaoDeData.timestampToLocalDateTime(rs.getTimestamp("inicio"));
                alunoProva.setInicio(ldtInicio);
                LocalDateTime ldtFinalizada = ConversaoDeData.timestampToLocalDateTime(rs.getTimestamp("finalizada"));
                alunoProva.setInicio(ldtFinalizada);
                Aluno aluno = new AlunoDao().buscarPorId(rs.getLong("id_aluno"));
                alunoProva.setAluno(aluno);
                Prova prova = new ProvaDao().buscarPorId(rs.getLong("id_prova"));
                alunoProva.setProva(prova);
                listAlunoProva.add(alunoProva);
            }
        } catch(SQLException e) {
        }
        return listAlunoProva;
    }
}

