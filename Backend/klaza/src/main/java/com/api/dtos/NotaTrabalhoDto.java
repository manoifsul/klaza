package com.api.dtos;

import com.api.entities.Trabalho;

public class NotaTrabalhoDto {

	private long idNotaTrabalho;
	private float valor;
	private Trabalho trabalho;
	private long idAluno;
	
	public NotaTrabalhoDto() {
		this.idNotaTrabalho = 0;
		this.valor = 0;
		this.trabalho = new Trabalho();
		this.idAluno = 0;
	}

	public NotaTrabalhoDto(long idNotaTrabalho, float valor, Trabalho trabalho, long idAluno) {
		this.idNotaTrabalho = idNotaTrabalho;
		this.valor = valor;
		this.trabalho = trabalho;
		this.idAluno = idAluno;
	}

	public long getIdNotaTrabalho() {
		return idNotaTrabalho;
	}

	public void setIdNotaTrabalho(long idNotaTrabalho) {
		this.idNotaTrabalho = idNotaTrabalho;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public Trabalho getTrabalho() {
		return trabalho;
	}

	public void setTrabalho(Trabalho trabalho) {
		this.trabalho = trabalho;
	}

	public long getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(long idAluno) {
		this.idAluno = idAluno;
	}

	@Override
	public String toString() {
		return "NotaTrabalho{" +
				"idNotaTrabalho=" + idNotaTrabalho +
				", valor=" + valor +
				", trabalho=" + trabalho +
				", idAluno=" + idAluno +
				'}';
	}
}
