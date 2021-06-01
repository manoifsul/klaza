package Daos;

import mysql.ConexaoMySQL;
import java.sql.ResultSet;


public class AdministradorDao {

    public void consultAdministrador() {
        try {
            ConexaoMySQL conexao = new ConexaoMySQL();
            ResultSet rs = conexao.getRs("select * from Administrador");

            while(rs.next()) {
                System.out.println(
                        rs.getLong("id_administrador") + ", " +
                        rs.getString("nome") + ", " +
                        rs.getString("login") + ", " +
                        rs.getString("senha") + ", " +
                        rs.getString("email"));
            }
        } catch(java.sql.SQLException e) {
        }
    }
}
