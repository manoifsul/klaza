package Daos;

import mysql.ConexaoMySQL;

import java.sql.ResultSet;


public class MateriaDao {

    public void consultMateria() {
        try {
            ConexaoMySQL conexao = new ConexaoMySQL();
            ResultSet rs = conexao.getRs("select * from Materia");

            while(rs.next()) {
                System.out.println(
                        rs.getLong("id_materia") + ", " +
                        rs.getString("nome"));
            }
        } catch(java.sql.SQLException e) {

        }
    }
}
