package entities;

public class ProfessorAtividade {
	
	private Long idProfessorAtividade;
	private Professor professor;
	private Atividade atividade;
	
	public ProfessorAtividade() {
		this.idProfessorAtividade = (long) 0;
		this.professor = new Professor();
		this.atividade = new Atividade();
	}

	public ProfessorAtividade(Long idProfessorAtividade, Professor professor, Atividade atividade) {
		this.idProfessorAtividade = idProfessorAtividade;
		this.professor = professor;
		this.atividade = atividade;
	}

	public Long getIdProfessorAtividade() {
		return idProfessorAtividade;
	}

	public void setIdProfessorAtividade(Long idProfessorAtividade) {
		this.idProfessorAtividade = idProfessorAtividade;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}

	@Override
	public String toString() {
		return "ProfessorAtividade [idProfessorAtividade=" + idProfessorAtividade + ", professor=" + professor
				+ ", atividade=" + atividade + "]";
	}
}
