package entities;

public class ProfessorTrabalho {

	private Long idProfessorTrabalho;
	private Professor professor;
	private Atividade atividade;
	
	public ProfessorTrabalho() {
		this.idProfessorTrabalho = (long) 0;
		this.professor = new Professor();
		this.atividade = new Atividade();
	}

	public ProfessorTrabalho(Long idProfessorTrabalho, Professor professor, Atividade atividade) {
		this.idProfessorTrabalho = idProfessorTrabalho;
		this.professor = professor;
		this.atividade = atividade;
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

	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}

	@Override
	public String toString() {
		return "ProfessorTrabalho [idProfessorTrabalho=" + idProfessorTrabalho + ", professor=" + professor
				+ ", atividade=" + atividade + "]";
	}
}
