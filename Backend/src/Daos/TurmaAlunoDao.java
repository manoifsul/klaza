package Daos;

import mysql.ConexaoMySQL;

import java.sql.ResultSet;

public class TurmaAlunoDao {

    public void consultTurmaAluno() {
        try {
            ConexaoMySQL conexao = new ConexaoMySQL();
            ResultSet rs = conexao.getRs("select * from Turma_Aluno");

            while(rs.next()) {
                System.out.println(
                        rs.getLong("id_turma_aluno") + ", " +
                        rs.getLong("id_turma") + ", " +
                        rs.getLong("id_aluno"));
            }
        } catch(java.sql.SQLException e) {

        }
    }
}

