package com.api.entities;

public class MateriaProfessor {

	private Long idMateriaProfessor;
	private Materia materia;
	private Professor professor;
	
	public MateriaProfessor() {
		this.idMateriaProfessor = (long) 0;
		this.materia = new Materia();
		this.professor = new Professor();
	}

	public MateriaProfessor(Long idMateriaProfessor, Materia materia, Professor professor) {
		this.idMateriaProfessor = idMateriaProfessor;
		this.materia = materia;
		this.professor = professor;
	}

	public Long getIdMateriaProfessor() {
		return idMateriaProfessor;
	}

	public void setIdMateriaProfessor(Long idMateriaProfessor) {
		this.idMateriaProfessor = idMateriaProfessor;
	}

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	@Override
	public String toString() {
		return "MateriaProfessor [idMateriaProfessor=" + idMateriaProfessor + ", materia=" + materia + ", professor="
				+ professor + "]";
	}
}
