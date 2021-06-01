package Daos;

import mysql.ConexaoMySQL;

import java.sql.ResultSet;

public class ArquivoDao {

    public void consultArquivoAula() {
        try {
            ConexaoMySQL conexao = new ConexaoMySQL();
            ResultSet rs = conexao.getRs("select * from Arquivo_Aula");

            while(rs.next()) {
                System.out.println(
                        rs.getLong("id_arquivo_aula") + ", " +
                        rs.getLong("id_aula") + ", " +
                        rs.getString("link"));
            }
        } catch(java.sql.SQLException e) {

        }
    }

    public void consultArquivoTrabalho() {
        try {
            ConexaoMySQL conexao = new ConexaoMySQL();
            ResultSet rs = conexao.getRs("select * from Arquivo_Trabalho");

            while(rs.next()) {
                System.out.println(
                        rs.getLong("id_arquivo_trabalho") + ", " +
                        rs.getLong("id_trabalho") + ", " +
                        rs.getString("link"));
            }
        } catch(java.sql.SQLException e) {

        }
    }

    public void consultArquivoProva() {
        try {
            ConexaoMySQL conexao = new ConexaoMySQL();
            ResultSet rs = conexao.getRs("select * from Arquivo_Prova");

            while(rs.next()) {
                System.out.println(
                        rs.getLong("id_arquivo_prova") + ", " +
                        rs.getLong("id_prova") + ", " +
                        rs.getString("link"));
            }
        } catch(java.sql.SQLException e) {

        }
    }

}

