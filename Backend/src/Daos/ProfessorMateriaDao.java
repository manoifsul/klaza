package Daos;

import mysql.ConexaoMySQL;

import java.sql.ResultSet;

public class ProfessorMateriaDao {

    public void consultProfessorMateria() {
        try {
            ConexaoMySQL conexao = new ConexaoMySQL();
            ResultSet rs = conexao.getRs("select * from Professor_Materia");

            while(rs.next()) {
                System.out.println(
                        rs.getLong("id_professor_materia") + ", " +
                        rs.getLong("id_professor") + ", " +
                        rs.getLong("id_materia"));
            }
        } catch(java.sql.SQLException e) {

        }
    }
}

