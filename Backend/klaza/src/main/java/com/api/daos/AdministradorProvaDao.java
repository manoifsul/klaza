package com.api.daos;

import com.api.entities.Administrador;
import com.api.entities.AdministradorProva;
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
public class AdministradorProvaDao {

    private ConexaoMySQL conexao;

    public AdministradorProva adicionar(AdministradorProva administradorProva) {
        conexao = new ConexaoMySQL();

        String sql = "INSERT INTO administrador_prova VALUES(null, ?, ?);";
        try{
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, administradorProva.getAdministrador().getIdAdministrador());
            st.setLong(2, administradorProva.getProva().getIdProva());
            st.executeUpdate();

            ResultSet rs = st.getGeneratedKeys();
            if(rs.next()) {
                administradorProva = this.buscarPorId(rs.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.conexao.closeConnection();
        }
        return administradorProva;
    }

    public void editar(AdministradorProva administradorProva) {
        conexao = new ConexaoMySQL();

        String sql = "UPDATE administrador_prova SET id_administrador=?, id_prova=? WHERE id_administrador_prova=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, administradorProva.getAdministrador().getIdAdministrador());
            st.setLong(2, administradorProva.getProva().getIdProva());
            st.setLong(3, administradorProva.getIdAdministradorProva());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
    }

    public void excluir(long idAdministradorProva) {
        conexao = new ConexaoMySQL();
        String sql = "DELETE FROM administrador_prova WHERE id_administrador_prova=?;";

        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idAdministradorProva);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
    }

    public AdministradorProva buscarPorId(long idAdministradorProva) {
        conexao = new ConexaoMySQL();
        AdministradorProva administradorProva = null;
        String sql = "SELECT * FROM administrador_prova WHERE id_administrador_prova=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idAdministradorProva);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                administradorProva = new AdministradorProva();
                administradorProva.setIdAdministradorProva(rs.getLong("id_administrador_prova"));
                Administrador administrador = new AdministradorDao().buscarPorId(rs.getLong("id_administrador"));
                administradorProva.setAdministrador(administrador);
                Prova prova = new ProvaDao().buscarPorId(rs.getLong("id_prova"));
                administradorProva.setProva(prova);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
        return administradorProva;
    }

    public List<AdministradorProva> buscar() {
        conexao = new ConexaoMySQL();
        List<AdministradorProva> listAdministradorProva = new ArrayList();
        String sql = "SELECT * FROM administrador_prova;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                AdministradorProva administradorProva = new AdministradorProva();
                administradorProva.setIdAdministradorProva(rs.getLong("id_administrador_prova"));
                Administrador administrador = new AdministradorDao().buscarPorId(rs.getLong("id_administrador"));
                administradorProva.setAdministrador(administrador);
                Prova prova = new ProvaDao().buscarPorId(rs.getLong("id_prova"));
                administradorProva.setProva(prova);
                listAdministradorProva.add(administradorProva);
            }
        } catch(SQLException e) {
        }
        return listAdministradorProva;
    }
}

