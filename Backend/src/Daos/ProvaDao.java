package Daos;

import mysql.ConexaoMySQL;

import java.sql.ResultSet;


public class ProvaDao {

    public void consultProva() {
        try {
            ConexaoMySQL conexao = new ConexaoMySQL();
            ResultSet rs = conexao.getRs("select * from Prova");

            while(rs.next()) {
                System.out.println(
                        rs.getLong("id_professor") + ", " +
                        rs.getString("nome") + ", " +
                        rs.getString("prazo") + ", " +
                        rs.getString("descricao") + ", " +
                        rs.getString("inicio") + ", " +
                        rs.getLong("tempo") + ", " +
                        rs.getInt("tentativos") + ", " +
                        rs.getLong("id_turma"));
            }
        } catch(java.sql.SQLException e) {
        }
    }

    public void consultQuestaoProva() {
        try {
            ConexaoMySQL conexao = new ConexaoMySQL();
            ResultSet rs = conexao.getRs("select * from Questao_Prova");

            while(rs.next()) {
                System.out.println(
                        rs.getLong("id_questao_prova") + ", " +
                        rs.getLong("id_questao") + ", " +
                        rs.getLong("id_prova"));
            }
        } catch(java.sql.SQLException e) {
        }
    }
}
