package com.api.dtos;

import com.api.entities.Aula;
import com.api.entities.Turma;

public class TurmaAula {
	private Long idTurmaAula;
	private Turma turma;
	private Aula aula;
	
	public TurmaAula() {
		this.idTurmaAula = (long) 0;
		this.turma = new Turma();
		this.aula = new Aula();
	}

	public TurmaAula(Long idTurmaAula, Turma turma, Aula aula) {
		this.idTurmaAula = idTurmaAula;
		this.turma = turma;
		this.aula = aula;
	}

	public Long getIdTurmaAula() {
		return idTurmaAula;
	}

	public void setIdTurmaAula(Long idTurmaAula) {
		this.idTurmaAula = idTurmaAula;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public Aula getAula() {
		return aula;
	}

	public void setAula(Aula aula) {
		this.aula = aula;
	}

	@Override
	public String toString() {
		return "TurmaAula [idTurmaAula=" + idTurmaAula + ", turma=" + turma + ", aula=" + aula + "]";
	}
}
