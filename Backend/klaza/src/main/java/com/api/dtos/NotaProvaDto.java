package com.api.dtos;

import com.api.entities.Prova;

public class NotaProvaDto {

	private long idNotaProva;
	private float valor;
	private Prova prova;
	private long idAluno;
	
	public NotaProvaDto() {
		this.idNotaProva = 0;
		this.valor = 0;
		this.prova = new Prova();
		this.idAluno = 0;
	}

	public NotaProvaDto(long idNotaProva, float valor, Prova prova, long idAluno) {
		this.idNotaProva = idNotaProva;
		this.valor = valor;
		this.prova = prova;
		this.idAluno = idAluno;
	}

	public long getIdNotaProva() {
		return idNotaProva;
	}

	public void setIdNotaProva(long idNotaProva) {
		this.idNotaProva = idNotaProva;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public Prova getProva() {
		return prova;
	}

	public void setProva(Prova prova) {
		this.prova = prova;
	}

	public long getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(long idAluno) {
		this.idAluno = idAluno;
	}

	@Override
	public String toString() {
		return "NotaProva{" +
				"idNotaProva=" + idNotaProva +
				", valor=" + valor +
				", prova=" + prova +
				", idAluno=" + idAluno +
				'}';
	}
}
