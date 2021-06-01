package Daos;

import mysql.ConexaoMySQL;

import java.sql.ResultSet;

public class TurmaAulaDao {

    public void consultTurmaAula() {
        try {
            ConexaoMySQL conexao = new ConexaoMySQL();
            ResultSet rs = conexao.getRs("select * from Turma_Aula");

            while(rs.next()) {
                System.out.println(
                        rs.getLong("id_turma_aula") + ", " +
                        rs.getLong("id_turma") + ", " +
                        rs.getLong("id_aula"));
            }
        } catch(java.sql.SQLException e) {

        }
    }
}

