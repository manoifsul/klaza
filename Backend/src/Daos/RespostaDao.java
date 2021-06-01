package Daos;

import mysql.ConexaoMySQL;

import java.sql.ResultSet;


public class RespostaDao {

    public void consultResposta() {
        try {
            ConexaoMySQL conexao = new ConexaoMySQL();
            ResultSet rs = conexao.getRs("select * from Resposta_Aluno");

            while(rs.next()) {
                System.out.println(
                        rs.getLong("id_resposta_aluno") + ", " +
                        rs.getString("resposta") + ", " +
                        rs.getLong("id_aluno") + ", " +
                        rs.getLong("id_questao"));
            }
        } catch(java.sql.SQLException e) {
        }
    }
}
