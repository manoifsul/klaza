package Daos;

import mysql.ConexaoMySQL;

import java.sql.ResultSet;

public class NotaTrabalhoDao {

    public void consultNotaTrabalho() {
        try {
            ConexaoMySQL conexao = new ConexaoMySQL();
            ResultSet rs = conexao.getRs("select * from Nota_Trabalho");

            while(rs.next()) {
                System.out.println(
                        rs.getLong("id_nota_trabalho") + ", " +
                        rs.getFloat("valor") + ", " +
                        rs.getLong("id_trabalho") + ", " +
                        rs.getLong("id_aluno"));
            }
        } catch(java.sql.SQLException e) {

        }
    }
}

