package Daos;

import mysql.ConexaoMySQL;

import java.sql.ResultSet;

public class MateriaProfessorDao {

    public void consultMateriaProfessor() {
        try {
            ConexaoMySQL conexao = new ConexaoMySQL();
            ResultSet rs = conexao.getRs("select * from Materia_Professor");

            while(rs.next()) {
                System.out.println(
                        rs.getLong("id_materia_professor") + ", " +
                        rs.getLong("id_materia") + ", " +
                        rs.getLong("id_professor"));
            }
        } catch(java.sql.SQLException e) {

        }
    }
}

