package com.api.dtos;

import com.api.entities.Professor;
import com.api.entities.Trabalho;

public class ProfessorTrabalhoDto {

	private Long idProfessorTrabalho;
	private Professor professor;
	private Trabalho trabalho;
	
	public ProfessorTrabalhoDto() {
		this.idProfessorTrabalho = (long) 0;
		this.professor = new Professor();
		this.trabalho = new Trabalho();
	}

	public ProfessorTrabalhoDto(Long idProfessorTrabalho, Professor professor, Trabalho trabalho) {
		this.idProfessorTrabalho = idProfessorTrabalho;
		this.professor = professor;
		this.trabalho = trabalho;
	}

	public Long getIdProfessorTrabalho() {
		return idProfessorTrabalho;
	}

	public void setIdProfessorTrabalho(Long idProfessorTrabalho) {
		this.idProfessorTrabalho = idProfessorTrabalho;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Trabalho getTrabalho() {
		return trabalho;
	}

	public void setTrabalho(Trabalho trabalho) {
		this.trabalho = trabalho;
	}

	@Override
	public String toString() {
		return "ProfessorTrabalho{" +
				"idProfessorTrabalho=" + idProfessorTrabalho +
				", professor=" + professor +
				", trabalho=" + trabalho +
				'}';
	}
}
