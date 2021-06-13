package com.api.entities;

public class AdministradorTrabalho {

	private long idAdministradorTrabalho;
	private Administrador administrador;
	private Trabalho trabalho;
	
	public AdministradorTrabalho() {
		this.idAdministradorTrabalho = 0;
		this.administrador = new Administrador();
		this.trabalho = new Trabalho();
	}

	public AdministradorTrabalho(long idAdministradorTrabalho, Administrador administrador, Trabalho trabalho) {
		this.idAdministradorTrabalho = idAdministradorTrabalho;
		this.administrador = administrador;
		this.trabalho = trabalho;
	}

	public long getIdAdministradorTrabalho() {
		return idAdministradorTrabalho;
	}

	public void setIdAdministradorTrabalho(long idAdministradorTrabalho) {
		this.idAdministradorTrabalho = idAdministradorTrabalho;
	}

	public Administrador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}

	public Trabalho getTrabalho() {
		return trabalho;
	}

	public void setTrabalho(Trabalho trabalho) {
		this.trabalho = trabalho;
	}

	@Override
	public String toString() {
		return "AdministradorTrabalho [idAdministradorTrabalho=" + idAdministradorTrabalho + ", administrador="
				+ administrador + ", trabalho=" + trabalho + "]";
	}
}
