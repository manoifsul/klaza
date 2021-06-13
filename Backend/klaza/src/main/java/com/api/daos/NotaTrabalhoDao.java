package com.api.daos;

import com.api.entities.NotaTrabalho;
import com.api.entities.Trabalho;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Service
@Repository
public class NotaTrabalhoDao {

    private ConexaoMySQL conexao;

    public NotaTrabalho adicionar(NotaTrabalho notaTrabalho) {
        conexao = new ConexaoMySQL();

        String sql = "INSERT INTO nota_trabalho VALUES(null, ?, ?, ?);";
        try{
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setFloat(1, notaTrabalho.getValor());
            st.setLong(2, notaTrabalho.getTrabalho().getIdTrabalho());
            st.setLong(3, notaTrabalho.getIdAluno());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.conexao.closeConnection();
        }
        return notaTrabalho;
    }

    public void editar(NotaTrabalho notaTrabalho) {
        conexao = new ConexaoMySQL();

        String sql = "UPDATE nota_trabalho SET valor=?, id_trabalho=?, id_aluno=? WHERE id_nota_trabalho=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setFloat(1, notaTrabalho.getValor());
            st.setLong(2, notaTrabalho.getTrabalho().getIdTrabalho());
            st.setLong(3, notaTrabalho.getIdAluno());
            st.setLong(4, notaTrabalho.getIdNotaTrabalho());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
    }

    public void excluir(long idNotaTrabalho) {
        conexao = new ConexaoMySQL();
        String sql = "DELETE FROM nota_trabalho WHERE id_nota_trabalho=?;";

        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idNotaTrabalho);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
    }

    public NotaTrabalho buscarPorId(long idNotaTrabalho) {
        conexao = new ConexaoMySQL();
        NotaTrabalho notaTrabalho = null;
        String sql = "SELECT * FROM nota_trabalho WHERE id_nota_trabalho=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idNotaTrabalho);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                notaTrabalho = new NotaTrabalho();
                notaTrabalho.setIdNotaTrabalho(rs.getLong("id_nota_trabalho"));
                notaTrabalho.setValor(rs.getFloat("valor"));
                Trabalho trabalho = new TrabalhoDao().buscarPorId(rs.getLong("id_prova"));
                notaTrabalho.setTrabalho(trabalho);
                notaTrabalho.setIdAluno(rs.getLong("id_aluno"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
        return notaTrabalho;
    }

    public List<NotaTrabalho> buscar() {
        conexao = new ConexaoMySQL();
        List<NotaTrabalho> listNotaTrabalho = new ArrayList();
        String sql = "SELECT * FROM nota_prova;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                NotaTrabalho notaTrabalho = new NotaTrabalho();
                notaTrabalho.setIdNotaTrabalho(rs.getLong("id_nota_trabalho"));
                notaTrabalho.setValor(rs.getFloat("valor"));
                Trabalho trabalho = new TrabalhoDao().buscarPorId(rs.getLong("id_prova"));
                notaTrabalho.setTrabalho(trabalho);
                notaTrabalho.setIdAluno(rs.getLong("id_aluno"));
                listNotaTrabalho.add(notaTrabalho);
            }
        } catch(SQLException e) {
        }
        return listNotaTrabalho;
    }
}


