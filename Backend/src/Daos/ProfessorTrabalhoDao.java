package Daos;

import mysql.ConexaoMySQL;

import java.sql.ResultSet;

public class ProfessorTrabalhoDao {

    public void consultProfessorTrabalho() {
        try {
            ConexaoMySQL conexao = new ConexaoMySQL();
            ResultSet rs = conexao.getRs("select * from Professor_Trabalho");

            while(rs.next()) {
                System.out.println(
                        rs.getLong("id_professor_trabalho") + ", " +
                        rs.getLong("id_professor") + ", " +
                        rs.getLong("id_trabalho"));
            }
        } catch(java.sql.SQLException e) {

        }
    }
}

