package com.api.daos;


import com.api.entities.Discord;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@Repository
public class DiscordDao {

    private ConexaoMySQL conexao;

    public Discord adicionar(Discord discord) {
        conexao = new ConexaoMySQL();

        String sql = "INSERT INTO discord VALUES(null, ?, ?);";
        try{
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setString(1, discord.getNotificacoes());
            st.setString(2, discord.getProvasTrabalhos());
            st.executeUpdate();

            ResultSet rs = st.getGeneratedKeys();
            if(rs.next()) {
                discord = this.buscarPorId(rs.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.conexao.closeConnection();
        }
        return discord;
    }

    public void editar(Discord discord) {
        conexao = new ConexaoMySQL();

        String sql = "UPDATE discord SET notificacoes=?, provas_trabalhos=? WHERE id_discord=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setString(1, discord.getNotificacoes());
            st.setString(2, discord.getProvasTrabalhos());
            st.setLong(3, discord.getIdDiscord());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
    }

    public void excluir(long idDiscord) {
        conexao = new ConexaoMySQL();
        String sql = "DELETE FROM discord WHERE id_discord=?;";

        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idDiscord);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
    }

    public Discord buscarPorId(long idDiscord) {
        conexao = new ConexaoMySQL();
        Discord discord = null;
        String sql = "SELECT * FROM discord WHERE id_discord=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idDiscord);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                discord = new Discord();
                discord.setIdDiscord(rs.getLong("id_discord"));
                discord.setNotificacoes(rs.getString("notificacoes"));
                discord.setProvasTrabalhos(rs.getString("provas_trabalhos"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
        return discord;
    }

    public List<Discord> buscar() {
        conexao = new ConexaoMySQL();
        List<Discord> listDiscord = new ArrayList();
        String sql = "SELECT * FROM discord;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                Discord discord = new Discord();
                discord.setIdDiscord(rs.getLong("id_discord"));
                discord.setNotificacoes(rs.getString("notificacoes"));
                discord.setProvasTrabalhos(rs.getString("provas_trabalhos"));
                listDiscord.add(discord);
            }
        } catch(SQLException e) {
        }
        return listDiscord;
    }
}
