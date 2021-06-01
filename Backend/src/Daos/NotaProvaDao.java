package Daos;

import mysql.ConexaoMySQL;

import java.sql.ResultSet;

public class NotaProvaDao {

    public void consultNotaProva() {
        try {
            ConexaoMySQL conexao = new ConexaoMySQL();
            ResultSet rs = conexao.getRs("select * from Nota_Prova");

            while(rs.next()) {
                System.out.println(
                        rs.getLong("id_nota_prova") + ", " +
                        rs.getFloat("valor") + ", " +
                        rs.getLong("id_prova") + ", " +
                        rs.getLong("id_aluno"));
            }
        } catch(java.sql.SQLException e) {

        }
    }
}

