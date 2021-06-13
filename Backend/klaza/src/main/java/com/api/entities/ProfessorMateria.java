package com.api.entities;

public class ProfessorMateria {

	private long idProfessorMateria;
	private Professor professor;
	private Materia materia;
	
	public ProfessorMateria() {
		this.idProfessorMateria = 0;
		this.professor = new Professor();
		this.materia = new Materia();
	}

	public ProfessorMateria(long idProfessorMateria, Professor professor, Materia materia) {
		this.idProfessorMateria = idProfessorMateria;
		this.professor = professor;
		this.materia = materia;
	}

	public long getIdProfessorMateria() {
		return idProfessorMateria;
	}

	public void setIdProfessorMateria(long idProfessorMateria) {
		this.idProfessorMateria = idProfessorMateria;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	@Override
	public String toString() {
		return "ProfessorMateria{" +
				"idProfessorMateria=" + idProfessorMateria +
				", professor=" + professor +
				", materia=" + materia +
				'}';
	}
}
