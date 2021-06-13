package com.api.dtos;

import com.api.entities.Administrador;
import com.api.entities.Trabalho;

public class AdministradorTrabalhoDto {

	private long idAdministradorTrabalho;
	private Administrador administrador;
	private Trabalho trabalho;
	
	public AdministradorTrabalhoDto() {
		this.idAdministradorTrabalho = 0;
		this.administrador = new Administrador();
		this.trabalho = new Trabalho();
	}

	public AdministradorTrabalhoDto(long idAdministradorTrabalho, Administrador administrador, Trabalho trabalho) {
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
