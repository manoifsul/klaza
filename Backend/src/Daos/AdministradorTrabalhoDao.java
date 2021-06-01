package Daos;

import mysql.ConexaoMySQL;

import java.sql.ResultSet;

public class AdministradorTrabalhoDao {

    public void consultAdministradorTrabalho() {
        try {
            ConexaoMySQL conexao = new ConexaoMySQL();
            ResultSet rs = conexao.getRs("select * from Administrador_Atividade");

            while(rs.next()) {
                System.out.println(
                        rs.getLong("id_administrador_atividade") + ", " +
                        rs.getLong("id_administrador") + ", " +
                        rs.getLong("id_atividade"));
            }
        } catch(java.sql.SQLException e) {

        }
    }
}
