package Daos;

import mysql.ConexaoMySQL;

import java.sql.ResultSet;


public class AtividadeDao {

    public void consultAtividade() {
        try {
            ConexaoMySQL conexao = new ConexaoMySQL();
            ResultSet rs = conexao.getRs("select * from Atividade");

            while(rs.next()) {
                System.out.println(
                        rs.getLong("id_atividade") + ", " +
                        rs.getString("nome") + ", " +
                        rs.getString("descricao") + ", " +
                        rs.getString("inicio") + ", " +
                        rs.getLong("id_turma"));
            }
        } catch(java.sql.SQLException e) {

        }
    }
}
