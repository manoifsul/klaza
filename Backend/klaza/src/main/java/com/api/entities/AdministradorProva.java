package com.api.entities;

public class AdministradorProva {

	private long idAdministradorProva;
	private Administrador administrador;
	private Prova prova;
	
	public AdministradorProva() {
		this.idAdministradorProva = 0;
		this.administrador = new Administrador();
		this.prova = new Prova();
	}

	public AdministradorProva(long idAdministradorProva, Administrador administrador, Prova prova) {
		this.idAdministradorProva = idAdministradorProva;
		this.administrador = administrador;
		this.prova = prova;
	}

	public long getIdAdministradorProva() {
		return idAdministradorProva;
	}

	public void setIdAdministradorProva(long idAdministradorProva) {
		this.idAdministradorProva = idAdministradorProva;
	}

	public Administrador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}

	public Prova getProva() {
		return prova;
	}

	public void setProva(Prova prova) {
		this.prova = prova;
	}

	@Override
	public String toString() {
		return "AdministradorProva [idAdministradorProva=" + idAdministradorProva + ", administrador=" + administrador
				+ ", prova=" + prova + "]";
	}
}
