package entities;

public class ProfessorMateria {

	private Long idProfessorProva;
	private Professor professor;
	private Materia materia;
	
	public ProfessorMateria() {
		this.idProfessorProva = (long) 0;
		this.professor = new Professor();
		this.materia = new Materia();
	}

	public ProfessorMateria(Long idProfessorProva, Professor professor, Materia materia) {
		this.idProfessorProva = idProfessorProva;
		this.professor = professor;
		this.materia = materia;
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

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	@Override
	public String toString() {
		return "ProfessorMateria [idProfessorProva=" + idProfessorProva + ", professor=" + professor + ", materia="
				+ materia + "]";
	}
}
