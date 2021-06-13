package com.api.entities;

public class ProfessorProva {

	private Long idProfessorProva;
	private Professor professor;
	private Prova prova;
	
	public ProfessorProva() {
		this.idProfessorProva = (long) 0;
		this.professor = new Professor();
		this.prova = new Prova();
	}

	public ProfessorProva(Long idProfessorProva, Professor professor, Prova prova) {
		this.idProfessorProva = idProfessorProva;
		this.professor = professor;
		this.prova = prova;
	}

	public Long getIdProfessorProva() {
		return idProfessorProva;
	}

	public void setIdProfessorProva(Long idProfessorProva) {
		this.idProfessorProva = idProfessorProva;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Prova getProva() {
		return prova;
	}

	public void setProva(Prova prova) {
		this.prova = prova;
	}

	@Override
	public String toString() {
		return "ProfessorProva [idProfessorProva=" + idProfessorProva + ", professor=" + professor + ", prova=" + prova
				+ "]";
	}
}
