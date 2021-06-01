package Daos;

import mysql.ConexaoMySQL;

import java.sql.ResultSet;

public class PresencaDao {

    public void consultPresenca() {
        try {
            ConexaoMySQL conexao = new ConexaoMySQL();
            ResultSet rs = conexao.getRs("select * from Presenca");

            while(rs.next()) {
                System.out.println(
                        rs.getLong("id_presenca") + ", " +
                        rs.getFloat("valor") + ", " +
                        rs.getLong("id_aula") + ", " +
                        rs.getLong("id_aluno"));
            }
        } catch(java.sql.SQLException e) {

        }
    }
}

