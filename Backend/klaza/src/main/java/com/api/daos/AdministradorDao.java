package com.api.daos;
import com.api.entities.Administrador;
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
public class AdministradorDao {

    private ConexaoMySQL conexao;

    public AdministradorDao() {
    }

    public Administrador adicionar(Administrador administrador) {
        conexao = new ConexaoMySQL();

        String sql = "INSERT INTO administrador VALUES(null, ?, ?, ?, ?);";
        try{
            PreparedStatement st = conexao.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, administrador.getNome());
            st.setString(2, administrador.getLogin());
            st.setString(3, administrador.getSenha());
            st.setString(4, administrador.getEmail());
            st.executeUpdate();

            ResultSet rs = st.getGeneratedKeys();
            if(rs.next()) {
                administrador = this.buscarPorId(rs.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.conexao.closeConnection();
        }
        return administrador;
    }

    public void editar(Administrador administrador) {
        conexao = new ConexaoMySQL();

        String sql = "UPDATE administrador SET nome=?, login=?, senha=?, email=? WHERE id_administrador=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setString(1, administrador.getNome());
            st.setString(2, administrador.getLogin());
            st.setString(3, administrador.getSenha());
            st.setString(4, administrador.getEmail());
            st.setLong(5, administrador.getIdAdministrador());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
    }

    public void excluir(long idAdministrador) {
        conexao = new ConexaoMySQL();
        String sql = "DELETE FROM administrador WHERE id_administrador=?;";

        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idAdministrador);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
    }

    public Administrador buscarPorId(long idAdministrador) {
        conexao = new ConexaoMySQL();
        Administrador administrador = null;
        String sql = "SELECT * FROM administrador WHERE id_administrador=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idAdministrador);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                administrador = new Administrador();
                administrador.setIdAdministrador(rs.getLong("id_administrador"));
                administrador.setNome(rs.getString("nome"));
                administrador.setLogin(rs.getString("login"));
                administrador.setSenha(rs.getString("senha"));
                administrador.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
        return administrador;
    }

    public List<Administrador> buscar() {
        conexao = new ConexaoMySQL();
        List<Administrador> listAdministrador = new ArrayList();
        String sql = "SELECT * FROM administrador;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                Administrador administrador = new Administrador();
                administrador.setIdAdministrador(rs.getLong("id_administrador"));
                administrador.setNome(rs.getString("nome"));
                administrador.setLogin(rs.getString("login"));
                administrador.setSenha(rs.getString("senha"));
                administrador.setEmail(rs.getString("email"));
                listAdministrador.add(administrador);
            }
        } catch(SQLException e) {
        }
        return listAdministrador;
    }

    public Administrador buscarPorLogin(String login) {
        conexao = new ConexaoMySQL();
        Administrador administrador = null;
        String sql = "SELECT * FROM administrador WHERE login=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setString(1, login);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                administrador = new Administrador();
                administrador.setIdAdministrador(rs.getLong("id_administrador"));
                administrador.setNome(rs.getString("nome"));
                administrador.setLogin(rs.getString("login"));
                administrador.setSenha(rs.getString("senha"));
                administrador.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
        return administrador;
    }
}

