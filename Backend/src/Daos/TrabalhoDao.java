package Daos;

import mysql.ConexaoMySQL;

import java.sql.ResultSet;


public class TrabalhoDao {

    public void consultTrabalho() {
        try {
            ConexaoMySQL conexao = new ConexaoMySQL();
            ResultSet rs = conexao.getRs("select * from Trabalho");

            while(rs.next()) {
                System.out.println(
                        rs.getLong("id_trabalho") + ", " +
                        rs.getString("nome") + ", " +
                        rs.getString("prazo") + ", " +
                        rs.getString("descricao") + ", " +
                        rs.getString("inicio") + ", " +
                        rs.getLong("tempo") + ", " +
                        rs.getByte("tentativos") + ", " +
                        rs.getByte("tipo") + ", " +
                        rs.getLong("id_turma"));
            }
        } catch(java.sql.SQLException e) {
        }
    }

    public void consultQuestaoTrabalho() {
        try {
            ConexaoMySQL conexao = new ConexaoMySQL();
            ResultSet rs = conexao.getRs("select * from Questao_Trabalho");

            while(rs.next()) {
                System.out.println(
                        rs.getLong("id_questao_trabalho") + ", " +
                        rs.getLong("id_questao") + ", " +
                        rs.getLong("id_trabalho"));
            }
        } catch(java.sql.SQLException e) {
        }
    }
}
