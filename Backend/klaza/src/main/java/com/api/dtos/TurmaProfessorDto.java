package com.api.dtos;

import com.api.entities.Professor;
import com.api.entities.Turma;

public class TurmaProfessorDto {

	private Long idTurmaProfessor;
	private Turma turma;
	private Professor professor;
	
	public TurmaProfessorDto() {
		this.idTurmaProfessor = (long) 0;
		this.turma = new Turma();
		this.professor = new Professor();
	}

	public TurmaProfessorDto(Long idTurmaProfessor, Turma turma, Professor professor) {
		this.idTurmaProfessor = idTurmaProfessor;
		this.turma = turma;
		this.professor = professor;
	}

	public Long getIdTurmaProfessor() {
		return idTurmaProfessor;
	}

	public void setIdTurmaProfessor(Long idTurmaProfessor) {
		this.idTurmaProfessor = idTurmaProfessor;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	@Override
	public String toString() {
		return "TurmaProfessor [idTurmaProfessor=" + idTurmaProfessor + ", turma=" + turma + ", professor=" + professor
				+ "]";
	}
}
