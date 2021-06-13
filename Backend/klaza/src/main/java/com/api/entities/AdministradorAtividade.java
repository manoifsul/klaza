package com.api.entities;

public class AdministradorAtividade {

	private long idAdministradorAtividade;
	private Administrador administrador;
	private Atividade atividade;
	
	public AdministradorAtividade() {
		this.idAdministradorAtividade = 0;
		this.administrador = new Administrador();
		this.atividade = new Atividade();
	}

	public AdministradorAtividade(long idAdministradorAtividade, Administrador administrador, Atividade atividade) {
		this.idAdministradorAtividade = idAdministradorAtividade;
		this.administrador = administrador;
		this.atividade = atividade;
	}

	public long getIdAdministradorAtividade() {
		return idAdministradorAtividade;
	}

	public void setIdAdministradorAtividade(long idAdministradorAtividade) {
		this.idAdministradorAtividade = idAdministradorAtividade;
	}

	public Administrador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}

	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}

	@Override
	public String toString() {
		return "AdministradorAtividade [idAdministradorAtividade=" + idAdministradorAtividade + ", administrador="
				+ administrador + ", atividade=" + atividade + "]";
	}
}
