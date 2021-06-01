package Daos;

import mysql.ConexaoMySQL;

import java.sql.ResultSet;

public class ProfessorAtividadeDao {

    public void consultProfessorAtividade() {
        try {
            ConexaoMySQL conexao = new ConexaoMySQL();
            ResultSet rs = conexao.getRs("select * from Professor_Atividade");

            while(rs.next()) {
                System.out.println(
                        rs.getLong("id_professor_atividade") + ", " +
                        rs.getLong("id_professor") + ", " +
                        rs.getLong("id_atividade"));
            }
        } catch(java.sql.SQLException e) {

        }
    }
}
