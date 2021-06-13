package com.api.entities;

public class TurmaAluno {

	private Long idTurmaAluno;
	private Turma turma;
	private Aluno aluno;
	
	public TurmaAluno() {
		this.idTurmaAluno = (long) 0;
		this.turma = new Turma();
		this.aluno = new Aluno();
	}

	public TurmaAluno(Long idTurmaAluno, Turma turma, Aluno aluno) {
		this.idTurmaAluno = idTurmaAluno;
		this.turma = turma;
		this.aluno = aluno;
	}

	public Long getIdTurmaAluno() {
		return idTurmaAluno;
	}

	public void setIdTurmaAluno(Long idTurmaAluno) {
		this.idTurmaAluno = idTurmaAluno;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	@Override
	public String toString() {
		return "TurmaAluno [idTurmaAluno=" + idTurmaAluno + ", turma=" + turma + ", aluno=" + aluno + "]";
	}
}
