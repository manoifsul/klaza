package Daos;

import mysql.ConexaoMySQL;

import java.sql.ResultSet;


public class QuestaoCorretaDao {

    public void consultQuestaoCorreta() {
        try {
            ConexaoMySQL conexao = new ConexaoMySQL();
            ResultSet rs = conexao.getRs("select * from Questao");

            while(rs.next()) {
                System.out.println(
                        rs.getLong("id_questao_correta") + ", " +
                        rs.getString("id_questao") + ", " +
                        rs.getByte("id_questao_alternativa"));
            }
        } catch(java.sql.SQLException e) {

        }
    }
}
