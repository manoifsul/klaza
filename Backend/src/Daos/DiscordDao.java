package Daos;

import mysql.ConexaoMySQL;

import java.sql.ResultSet;


public class DiscordDao {

    public void consultAtividade() {
        try {
            ConexaoMySQL conexao = new ConexaoMySQL();
            ResultSet rs = conexao.getRs("select * from Discord");

            while(rs.next()) {
                System.out.println(
                        rs.getLong("id_discord") + ", " +
                        rs.getString("notificacoes") + ", " +
                        rs.getString("provas_trabalho"));
            }
        } catch(java.sql.SQLException e) {

        }
    }
}
