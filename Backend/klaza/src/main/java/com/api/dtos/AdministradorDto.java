package com.api.dtos;

public class AdministradorDto {

	private long idAdministrador;
	private String nome;
	private String login;
	private String senha;
	private String email;
	
	public AdministradorDto() {
		this.idAdministrador = 0;
		this.nome = "";
		this.login = "";
		this.senha = "";
		this.email = "";
	}

	public AdministradorDto(long idAdministrador, String nome, String login, String senha, String email) {
		this.idAdministrador = idAdministrador;
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.email = email;
	}

	public long getIdAdministrador() {
		return idAdministrador;
	}

	public void setIdAdministrador(long idAdministrador) {
		this.idAdministrador = idAdministrador;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
