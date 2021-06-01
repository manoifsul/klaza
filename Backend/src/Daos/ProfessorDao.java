package Daos;

import mysql.ConexaoMySQL;

import java.sql.ResultSet;


public class ProfessorDao {

    public void consultProfessor() {
        try {
            ConexaoMySQL conexao = new ConexaoMySQL();
            ResultSet rs = conexao.getRs("select * from Professor");

            while(rs.next()) {
                System.out.println(
                        rs.getLong("id_professor") + ", " +
                        rs.getString("nome") + ", " +
                        rs.getString("matricula") + ", " +
                        rs.getString("senha") + ", " +
                        rs.getString("email"));
            }
        } catch(java.sql.SQLException e) {
        }
    }
}
