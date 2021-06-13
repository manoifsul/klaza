package com.api.daos;

import com.api.util.ConversaoDeData;
import com.api.entities.Aluno;
import com.api.entities.AlunoTrabalho;
import com.api.entities.Trabalho;
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
public class AlunoTrabalhoDao {

    private ConexaoMySQL conexao;

    public AlunoTrabalho adicionar(AlunoTrabalho alunoTrabalho) {
        conexao = new ConexaoMySQL();

        String sql = "INSERT INTO aluno_trabalho VALUES(null, ?, ?, ?, ?, ?);";
        try{
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, alunoTrabalho.getTempo());
            Timestamp timestampInicio = ConversaoDeData.localDateTimeToTimestamp(alunoTrabalho.getInicio());
            st.setTimestamp(2, timestampInicio);
            Timestamp timestampFinalizada = ConversaoDeData.localDateTimeToTimestamp(alunoTrabalho.getFinalizada());
            st.setTimestamp(3, timestampFinalizada);
            st.setLong(4, alunoTrabalho.getAluno().getIdAluno());
            st.setLong(5, alunoTrabalho.getTrabalho().getIdTrabalho());
            st.executeUpdate();

            ResultSet rs = st.getGeneratedKeys();
            if(rs.next()) {
                alunoTrabalho = this.buscarPorId(rs.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.conexao.closeConnection();
        }
        return alunoTrabalho;
    }

    public void editar(AlunoTrabalho alunoTrabalho) {
        conexao = new ConexaoMySQL();

        String sql = "UPDATE aluno_trabalho SET tempo=?, inicio=?, finalizada=?, id_aluno=?, id_trabalho=? WHERE id_aluno_trabalho=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, alunoTrabalho.getTempo());
            Timestamp timestampInicio = ConversaoDeData.localDateTimeToTimestamp(alunoTrabalho.getInicio());
            st.setTimestamp(2, timestampInicio);
            Timestamp timestampFinalizada = ConversaoDeData.localDateTimeToTimestamp(alunoTrabalho.getFinalizada());
            st.setTimestamp(3, timestampFinalizada);
            st.setLong(4, alunoTrabalho.getAluno().getIdAluno());
            st.setLong(5, alunoTrabalho.getTrabalho().getIdTrabalho());
            st.setLong(6, alunoTrabalho.getIdAlunoTrabalho());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
    }

    public void excluir(long idAlunoTrabalho) {
        conexao = new ConexaoMySQL();
        String sql = "DELETE FROM aluno_trabalho WHERE id_aluno_trabalho=?;";

        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idAlunoTrabalho);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
    }

    public AlunoTrabalho buscarPorId(long idAlunoTrabalho) {
        conexao = new ConexaoMySQL();
        AlunoTrabalho alunoTrabalho = null;
        String sql = "SELECT * FROM aluno_trabalho WHERE id_aluno_trabalho=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idAlunoTrabalho);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                alunoTrabalho = new AlunoTrabalho();
                alunoTrabalho.setIdAlunoTrabalho(rs.getLong("id_aluno_trabalho"));
                alunoTrabalho.setTempo(rs.getLong("tempo"));
                LocalDateTime ldtInicio = ConversaoDeData.timestampToLocalDateTime(rs.getTimestamp("inicio"));
                alunoTrabalho.setInicio(ldtInicio);
                LocalDateTime ldtFinalizada = ConversaoDeData.timestampToLocalDateTime(rs.getTimestamp("finalizada"));
                alunoTrabalho.setInicio(ldtFinalizada);
                Aluno aluno = new AlunoDao().buscarPorId(rs.getLong("id_aluno"));
                alunoTrabalho.setAluno(aluno);
                Trabalho trabalho = new TrabalhoDao().buscarPorId(rs.getLong("id_trabalho"));
                alunoTrabalho.setTrabalho(trabalho);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
        return alunoTrabalho;
    }

    public List<AlunoTrabalho> buscar() {
        conexao = new ConexaoMySQL();
        List<AlunoTrabalho> listAlunoTrabalho = new ArrayList();
        String sql = "SELECT * FROM aluno_trabalho;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                AlunoTrabalho alunoTrabalho = new AlunoTrabalho();
                alunoTrabalho.setIdAlunoTrabalho(rs.getLong("id_aluno_trabalho"));
                alunoTrabalho.setTempo(rs.getLong("tempo"));
                LocalDateTime ldtInicio = ConversaoDeData.timestampToLocalDateTime(rs.getTimestamp("inicio"));
                alunoTrabalho.setInicio(ldtInicio);
                LocalDateTime ldtFinalizada = ConversaoDeData.timestampToLocalDateTime(rs.getTimestamp("finalizada"));
                alunoTrabalho.setInicio(ldtFinalizada);
                Aluno aluno = new AlunoDao().buscarPorId(rs.getLong("id_aluno"));
                alunoTrabalho.setAluno(aluno);
                Trabalho trabalho = new TrabalhoDao().buscarPorId(rs.getLong("id_trabalho"));
                alunoTrabalho.setTrabalho(trabalho);
                listAlunoTrabalho.add(alunoTrabalho);
            }
        } catch(SQLException e) {
        }
        return listAlunoTrabalho;
    }
}

