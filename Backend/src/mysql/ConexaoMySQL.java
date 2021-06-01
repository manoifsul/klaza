package mysql;

import com.sun.jdi.event.ExceptionEvent;
import org.junit.Test;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexaoMySQL {
	private Connection conexao;
	private Statement st;
	private ResultSet rs;


	public ConexaoMySQL() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/klaza";
			String usuario = "root";
			String senha = "root";


		    this.conexao = DriverManager.getConnection(url, usuario, senha);
		     
		    this.st = conexao.createStatement();
		     
		    this.conexao.setAutoCommit(true);

		}catch(SQLException sqle){

		    sqle.printStackTrace();
		     
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void closeConnection() {
		try {

			if(!conexao.isClosed()) {

				System.out.println("a");
			}
		} catch (java.sql.SQLException e) {

		}


	}


	public Statement getSt() {
		return st;
	}

	public ResultSet getRs(String query) {
		try{
			rs = st.executeQuery(query);
			return rs;
		} catch(java.sql.SQLException e) {
			return null;
		}
	}

	public void getTable() {
		try {
			ResultSet getTable = st.executeQuery("select * from aluno");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void verSeTaConectado() {
		try{ if(!conexao.isClosed()) {
			System.out.println("aaaa");
		}


		} catch(java.sql.SQLException e) {

		}
	}
}

