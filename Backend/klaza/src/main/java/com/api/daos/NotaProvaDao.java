package com.api.daos;

import com.api.entities.NotaProva;
import com.api.entities.Prova;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Service
@Repository
public class NotaProvaDao {

    private ConexaoMySQL conexao;

    public NotaProva adicionar(NotaProva notaProva) {
        conexao = new ConexaoMySQL();

        String sql = "INSERT INTO nota_prova VALUES(null, ?, ?, ?);";
        try{
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setFloat(1, notaProva.getValor());
            st.setLong(2, notaProva.getProva().getIdProva());
            st.setLong(3, notaProva.getIdAluno());
            st.executeUpdate();

            ResultSet rs = st.getGeneratedKeys();
            if(rs.next()) {
                notaProva = this.buscarPorId(rs.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.conexao.closeConnection();
        }
        return notaProva;
    }

    public void editar(NotaProva notaProva) {
        conexao = new ConexaoMySQL();

        String sql = "UPDATE nota_prova SET valor=?, id_prova=?, id_aluno=? WHERE id_nota_prova=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setFloat(1, notaProva.getValor());
            st.setLong(2, notaProva.getProva().getIdProva());
            st.setLong(3, notaProva.getIdAluno());
            st.setLong(4, notaProva.getIdNotaProva());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
    }

    public void excluir(long idNotaProva) {
        conexao = new ConexaoMySQL();
        String sql = "DELETE FROM nota_prova WHERE id_nota_prova=?;";

        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idNotaProva);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
    }

    public NotaProva buscarPorId(long idNotaProva) {
        conexao = new ConexaoMySQL();
        NotaProva notaProva = null;
        String sql = "SELECT * FROM nota_prova WHERE id_nota_prova=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idNotaProva);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                notaProva = new NotaProva();
                notaProva.setIdNotaProva(rs.getLong("id_nota_prova"));
                notaProva.setValor(rs.getFloat("valor"));
                Prova prova = new ProvaDao().buscarPorId(rs.getLong("id_prova"));
                notaProva.setProva(prova);
                notaProva.setIdAluno(rs.getLong("id_aluno"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
        return notaProva;
    }

    public List<NotaProva> buscar() {
        conexao = new ConexaoMySQL();
        List<NotaProva> listNotaProva = new ArrayList();
        String sql = "SELECT * FROM nota_prova;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                NotaProva notaProva = new NotaProva();
                notaProva.setIdNotaProva(rs.getLong("id_nota_prova"));
                notaProva.setValor(rs.getFloat("valor"));
                Prova prova = new ProvaDao().buscarPorId(rs.getLong("id_prova"));
                notaProva.setProva(prova);
                notaProva.setIdAluno(rs.getLong("id_aluno"));
                listNotaProva.add(notaProva);
            }
        } catch(SQLException e) {
        }
        return listNotaProva;
    }
}

