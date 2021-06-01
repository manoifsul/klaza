package Daos;

import mysql.ConexaoMySQL;

import java.sql.ResultSet;

public class ProfessorProvaDao {

    public void consultProfessorProva() {
        try {
            ConexaoMySQL conexao = new ConexaoMySQL();
            ResultSet rs = conexao.getRs("select * from Professor_Prova");

            while(rs.next()) {
                System.out.println(
                        rs.getLong("id_professor_prova") + ", " +
                        rs.getLong("id_professor") + ", " +
                        rs.getLong("id_prova"));
            }
        } catch(java.sql.SQLException e) {

        }
    }
}

