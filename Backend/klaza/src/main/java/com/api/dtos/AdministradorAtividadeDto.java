package com.api.dtos;

import com.api.entities.Administrador;
import com.api.entities.Atividade;

public class AdministradorAtividadeDto {

	private long idAdministradorAtividade;
	private Administrador administrador;
	private Atividade atividade;
	
	public AdministradorAtividadeDto() {
		this.idAdministradorAtividade = 0;
		this.administrador = new Administrador();
		this.atividade = new Atividade();
	}

	public AdministradorAtividadeDto(long idAdministradorAtividade, Administrador administrador, Atividade atividade) {
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

}
