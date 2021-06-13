package com.api.daos;

import com.api.entities.Administrador;
import com.api.entities.AdministradorTrabalho;
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
public class AdministradorTrabalhoDao {

    private ConexaoMySQL conexao;

    public AdministradorTrabalho adicionar(AdministradorTrabalho administradorTrabalho) {
        conexao = new ConexaoMySQL();

        String sql = "INSERT INTO administrador_trabalho VALUES(null, ?, ?);";
        try{
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, administradorTrabalho.getAdministrador().getIdAdministrador());
            st.setLong(2, administradorTrabalho.getTrabalho().getIdTrabalho());
            st.executeUpdate();

            ResultSet rs = st.getGeneratedKeys();
            if(rs.next()) {
                administradorTrabalho = this.buscarPorId(rs.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.conexao.closeConnection();
        }
        return administradorTrabalho;
    }

    public void editar(AdministradorTrabalho administradorTrabalho) {
        conexao = new ConexaoMySQL();

        String sql = "UPDATE administrador_trabalho SET id_administrador=?, id_trabalho=? WHERE id_administador_trabalho=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, administradorTrabalho.getAdministrador().getIdAdministrador());
            st.setLong(2, administradorTrabalho.getTrabalho().getIdTrabalho());
            st.setLong(3, administradorTrabalho.getIdAdministradorTrabalho());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
    }

    public void excluir(long idAdministradorTrabalho) {
        conexao = new ConexaoMySQL();
        String sql = "DELETE FROM administrador_trabalho WHERE id_administrador_trabalho=?;";

        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idAdministradorTrabalho);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
    }

    public AdministradorTrabalho buscarPorId(long idAdministradorTrabalho) {
        conexao = new ConexaoMySQL();
        AdministradorTrabalho administradorTrabalho = null;
        String sql = "SELECT * FROM administrador_trabalho WHERE id_administradortrabalho=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idAdministradorTrabalho);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                administradorTrabalho = new AdministradorTrabalho();
                administradorTrabalho.setIdAdministradorTrabalho(rs.getLong("administrador_trabalho"));
                Administrador administrador = new AdministradorDao().buscarPorId(rs.getLong("id_administrador"));
                administradorTrabalho.setAdministrador(administrador);
                Trabalho trabalho = new TrabalhoDao().buscarPorId(rs.getLong("id_trabalho"));
                administradorTrabalho.setTrabalho(trabalho);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
        return administradorTrabalho;
    }

    public List<AdministradorTrabalho> buscar() {
        conexao = new ConexaoMySQL();
        List<AdministradorTrabalho> listAdministradorTrabalho = new ArrayList();
        String sql = "SELECT * FROM administrador_trabalho;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                AdministradorTrabalho administradorTrabalho = new AdministradorTrabalho();
                administradorTrabalho.setIdAdministradorTrabalho(rs.getLong("administrador_trabalho"));
                Administrador administrador = new AdministradorDao().buscarPorId(rs.getLong("id_administrador"));
                administradorTrabalho.setAdministrador(administrador);
                Trabalho trabalho = new TrabalhoDao().buscarPorId(rs.getLong("id_trabalho"));
                administradorTrabalho.setTrabalho(trabalho);
                listAdministradorTrabalho.add(administradorTrabalho);
            }
        } catch(SQLException e) {
        }
        return listAdministradorTrabalho;
    }
}
