package Daos;

import mysql.ConexaoMySQL;

import java.sql.ResultSet;


public class AlunoDao {

    public void consultAluno() {
        try {
            ConexaoMySQL conexao = new ConexaoMySQL();
            ResultSet rs = conexao.getRs("select * from Aluno");

            while(rs.next()) {
                System.out.println(
                        rs.getLong("id_aluno") + ", " +
                        rs.getString("nome") + ", " +
                        rs.getString("matricula") + ", " +
                        rs.getString("senha") + ", " +
                        rs.getString("email"));
            }
        } catch(java.sql.SQLException e) {

        }
    }
}
