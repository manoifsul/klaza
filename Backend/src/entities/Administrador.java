package entities;

import java.util.ArrayList;
import java.util.List;

public class Administrador {

	private long idAdministrador;
	private String nome;
	private String login;
	private String senha;
	private String email;
	private List<AdministradorAtividade> administradorAtividade;
	private List<AdministradorTrabalho> administradorTrabalho;
	private List<AdministradorProva> administradorProva;
	
	public Administrador() {
		this.idAdministrador = 0;
		this.nome = "";
		this.login = "";
		this.senha = "";
		this.email = "";
		this.administradorAtividade = new ArrayList<AdministradorAtividade>();
		this.administradorTrabalho = new ArrayList<AdministradorTrabalho>();
		this.administradorProva = new ArrayList<AdministradorProva>(); 
	}

	public Administrador(long id_administrador, String nome, String login, String senha, String email,
			List<AdministradorAtividade> administradorAtividade, List<AdministradorTrabalho> administradorTrabalho,
			List<AdministradorProva> administradorProva) {
		this.idAdministrador = id_administrador;
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.email = email;
		this.administradorAtividade = administradorAtividade;
		this.administradorTrabalho = administradorTrabalho;
		this.administradorProva = administradorProva;
	}

	public long getId_administrador() {
		return idAdministrador;
	}

	public void setId_administrador(long id_administrador) {
		this.idAdministrador = id_administrador;
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

	public List<AdministradorAtividade> getAdministradorAtividade() {
		return administradorAtividade;
	}

	public void setAdministradorAtividade(List<AdministradorAtividade> administradorAtividade) {
		this.administradorAtividade = administradorAtividade;
	}

	public List<AdministradorTrabalho> getAdministradorTrabalho() {
		return administradorTrabalho;
	}

	public void setAdministradorTrabalho(List<AdministradorTrabalho> administradorTrabalho) {
		this.administradorTrabalho = administradorTrabalho;
	}

	public List<AdministradorProva> getAdministradorProva() {
		return administradorProva;
	}

	public void setAdministradorProva(List<AdministradorProva> administradorProva) {
		this.administradorProva = administradorProva;
	}
	
	public boolean autenticaSenha(String senha) {
		return this.senha == senha;
	}

	@Override
	public String toString() {
		return "Administrador [idAdministrador=" + idAdministrador + ", nome=" + nome + ", login=" + login + ", senha="
				+ senha + ", email=" + email + ", administradorAtividade=" + administradorAtividade
				+ ", administradorTrabalho=" + administradorTrabalho + ", administradorProva=" + administradorProva
				+ "]";
	}
}
