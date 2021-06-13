package com.api.daos;

import com.api.entities.Administrador;
import com.api.entities.AdministradorAtividade;
import com.api.entities.Atividade;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@Repository
public class AdministradorAtividadeDao {

    private ConexaoMySQL conexao;

    public AdministradorAtividade adicionar(AdministradorAtividade administradorAtividade) {
        conexao = new ConexaoMySQL();

        String sql = "INSERT INTO administrador_atividade VALUES(null, ?, ?);";
        try{
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, administradorAtividade.getAdministrador().getIdAdministrador());
            st.setLong(2, administradorAtividade.getAtividade().getIdAtividade());
            st.executeUpdate();

            ResultSet rs = st.getGeneratedKeys();
            if(rs.next()) {
                administradorAtividade = this.buscarPorId(rs.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.conexao.closeConnection();
        }
        return administradorAtividade;
    }

    public void editar(AdministradorAtividade administradorAtividade) {
        conexao = new ConexaoMySQL();

        String sql = "UPDATE administrador_atividade SET id_administrador=?, id_atividade=? WHERE id_administrador_atividade=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, administradorAtividade.getAdministrador().getIdAdministrador());
            st.setLong(2, administradorAtividade.getAtividade().getIdAtividade());
            st.setLong(3, administradorAtividade.getIdAdministradorAtividade());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
    }

    public void excluir(long idAdministradorAtividade) {
        conexao = new ConexaoMySQL();
        String sql = "DELETE FROM administrador_atividade WHERE id_administrador_atividade=?;";

        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idAdministradorAtividade);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
    }

    public AdministradorAtividade buscarPorId(long idAdministradorAtividade) {
        conexao = new ConexaoMySQL();
        AdministradorAtividade administradorAtividade = null;
        String sql = "SELECT * FROM administrador_atividade WHERE id_administrador_atividade=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idAdministradorAtividade);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                administradorAtividade = new AdministradorAtividade();
                administradorAtividade.setIdAdministradorAtividade(rs.getLong("id_administrador_atividade"));
                Administrador administrador = new AdministradorDao().buscarPorId(rs.getLong("id_administrador"));
                administradorAtividade.setAdministrador(administrador);
                Atividade atividade = new AtividadeDao().buscarPorId(rs.getLong("id_atividade"));
                administradorAtividade.setAtividade(atividade);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
        return administradorAtividade;
    }

    public List<AdministradorAtividade> buscar() {
        conexao = new ConexaoMySQL();
        List<AdministradorAtividade> listAdministradorAtividade = new ArrayList();
        String sql = "SELECT * FROM administrador_atividade;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                AdministradorAtividade administradorAtividade = new AdministradorAtividade();
                administradorAtividade.setIdAdministradorAtividade(rs.getLong("id_administrador_atividade"));
                Administrador administrador = new AdministradorDao().buscarPorId(rs.getLong("id_administrador"));
                administradorAtividade.setAdministrador(administrador);
                Atividade atividade = new AtividadeDao().buscarPorId(rs.getLong("id_atividade"));
                administradorAtividade.setAtividade(atividade);
                listAdministradorAtividade.add(administradorAtividade);
            }
        } catch(SQLException e) {
        }
        return listAdministradorAtividade;
    }
}
