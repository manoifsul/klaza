package Daos;

import mysql.ConexaoMySQL;

import java.sql.ResultSet;

public class AlunoProvaDao {

    public void consultAlunoProva() {
        try {
            ConexaoMySQL conexao = new ConexaoMySQL();
            ResultSet rs = conexao.getRs("select * from Administrador_Prova");

            while(rs.next()) {
                System.out.println(
                        rs.getLong("id_administrador_prova") + ", " +
                        rs.getLong("id_administrador") + ", " +
                        rs.getLong("id_prova"));
            }
        } catch(java.sql.SQLException e) {

        }
    }
}

