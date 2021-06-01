package Daos;

import mysql.ConexaoMySQL;

import java.sql.ResultSet;


public class QuestaoDao {

    public void consultQuestao() {
        try {
            ConexaoMySQL conexao = new ConexaoMySQL();
            ResultSet rs = conexao.getRs("select * from Questao");

            while(rs.next()) {
                System.out.println(
                        rs.getLong("id_questao") + ", " +
                        rs.getString("pergunta") + ", " +
                        rs.getByte("tipo"));
            }
        } catch(java.sql.SQLException e) {

        }
    }
}
