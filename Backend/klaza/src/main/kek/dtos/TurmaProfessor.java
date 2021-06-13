package entities;

public class TurmaProfessor {

	private Long idTurmaProfessor;
	private Turma turma;
	private Professor professor;
	
	public TurmaProfessor() {
		this.idTurmaProfessor = (long) 0;
		this.turma = new Turma();
		this.professor = new Professor();
	}

	public TurmaProfessor(Long idTurmaProfessor, Turma turma, Professor professor) {
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
