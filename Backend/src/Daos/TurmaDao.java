package Daos;

import mysql.ConexaoMySQL;

import java.sql.ResultSet;


public class TurmaDao {

    public void consultTurma() {
        try {
            ConexaoMySQL conexao = new ConexaoMySQL();
            ResultSet rs = conexao.getRs("select * from Turma");

            while(rs.next()) {
                System.out.println(
                        rs.getLong("id_turma") + ", " +
                        rs.getString("nome") + ", " +
                        rs.getLong("id_materia") + ", " +
                        rs.getLong("id_discord"));
            }
        } catch(java.sql.SQLException e) {
        }
    }
}
