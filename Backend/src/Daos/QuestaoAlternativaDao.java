package Daos;

import mysql.ConexaoMySQL;

import java.sql.ResultSet;


public class QuestaoAlternativaDao {

    public void consultQuestaoAlternativa() {
        try {
            ConexaoMySQL conexao = new ConexaoMySQL();
            ResultSet rs = conexao.getRs("select * from Questao_Alternativa");

            while(rs.next()) {
                System.out.println(
                        rs.getLong("id_questao_alternativa") + ", " +
                        rs.getString("resposta") + ", " +
                        rs.getByte("id_questao"));
            }
        } catch(java.sql.SQLException e) {

        }
    }
}
