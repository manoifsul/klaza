package Daos;

import mysql.ConexaoMySQL;

import java.sql.ResultSet;


public class AulaDao {

    public void consultAtividade() {
        try {
            ConexaoMySQL conexao = new ConexaoMySQL();
            ResultSet rs = conexao.getRs("select * from Aula");

            while(rs.next()) {
                System.out.println(
                        rs.getLong("id_aula") + ", " +
                        rs.getString("nome") + ", " +
                        rs.getString("materia") + ", " +
                        rs.getString("descricao") + ", " +
                        rs.getString("inicio") + ", " +
                        rs.getString("link"));
            }
        } catch(java.sql.SQLException e) {

        }
    }
}
