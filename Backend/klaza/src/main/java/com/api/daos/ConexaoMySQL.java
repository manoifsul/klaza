package com.api.daos;



import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.*;
@Service
@Repository
public class ConexaoMySQL {
	private Connection conexao;
	private Statement st;
	private ResultSet rs;


	public ConexaoMySQL() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/klaza";
			String usuario = "root";
			String senha = "";


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

	public void closeConnection() {
		try {
			if(!conexao.isClosed()) {
				conexao.close();
			}
		} catch (SQLException e) {

		}


	}

	public Connection getConexao() {
		return conexao;
	}


	public Statement getSt() {
		return st;
	}

	public ResultSet getRs(String query) {
		try{
			rs = st.executeQuery(query);
			return rs;
		} catch(SQLException e) {
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


		} catch(SQLException e) {

		}
	}
}

