package entities;

import java.util.ArrayList;
import java.util.List;

public class Turma {
	
	private Long idTurma;
	private String nome;
	private List<Arquivo> arquivo;
	private List<TurmaAluno> turmaAluno;
	private List<TurmaAula> turmaAula;
	private List<TurmaProfessor> turmaProfessor;
	private List<Trabalho> trabalho;
	private List<Atividade> atividade;

	private Materia materia;
	private Discord discord;


	private List<Prova> prova;

	
	public Turma() {
		this.idTurma = (long) 0;
		this.nome = "";
		this.arquivo = new ArrayList<Arquivo>();
		this.turmaAluno = new ArrayList<TurmaAluno>();
		this.turmaAula = new ArrayList<TurmaAula>();
		this.turmaProfessor = new ArrayList<TurmaProfessor>();
		this.trabalho = new ArrayList<Trabalho>();
		this.atividade = new ArrayList<Atividade>();
		this.prova = new ArrayList<Prova>();
		this.materia = new Materia();
		this.discord = new Discord();
	}

	public Turma(Long idTurma, String nome, List<Arquivo> arquivo, List<TurmaAluno> turmaAluno,
			List<TurmaAula> turmaAula, List<TurmaProfessor> turmaProfessor, List<Trabalho> trabalho,
			List<Atividade> atividade, List<Prova> prova, Materia materia, Discord discord) {
		this.idTurma = idTurma;
		this.nome = nome;
		this.arquivo = arquivo;
		this.turmaAluno = turmaAluno;
		this.turmaAula = turmaAula;
		this.turmaProfessor = turmaProfessor;
		this.trabalho = trabalho;
		this.atividade = atividade;
		this.prova = prova;
		this.materia = materia;
		this.discord = discord;
	}

	public Long getIdTurma() {
		return idTurma;
	}

	public void setIdTurma(Long idTurma) {
		this.idTurma = idTurma;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Arquivo> getArquivo() {
		return arquivo;
	}

	public void setArquivo(List<Arquivo> arquivo) {
		this.arquivo = arquivo;
	}

	public List<TurmaAluno> getTurmaAluno() {
		return turmaAluno;
	}

	public void setTurmaAluno(List<TurmaAluno> turmaAluno) {
		this.turmaAluno = turmaAluno;
	}

	public List<TurmaAula> getTurmaAula() {
		return turmaAula;
	}

	public void setTurmaAula(List<TurmaAula> turmaAula) {
		this.turmaAula = turmaAula;
	}

	public List<TurmaProfessor> getTurmaProfessor() {
		return turmaProfessor;
	}

	public void setTurmaProfessor(List<TurmaProfessor> turmaProfessor) {
		this.turmaProfessor = turmaProfessor;
	}

	public List<Trabalho> getTrabalho() {
		return trabalho;
	}

	public void setTrabalho(List<Trabalho> trabalho) {
		this.trabalho = trabalho;
	}

	public List<Atividade> getAtividade() {
		return atividade;
	}

	public void setAtividade(List<Atividade> atividade) {
		this.atividade = atividade;
	}

	public List<Prova> getProva() {
		return prova;
	}

	public void setProva(List<Prova> prova) {
		this.prova = prova;
	}

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	public Discord getDiscord() {
		return discord;
	}

	public void setDiscord(Discord discord) {
		this.discord = discord;
	}

	@Override
	public String toString() {
		return "Turma [idTurma=" + idTurma + ", nome=" + nome + ", arquivo=" + arquivo + ", turmaAluno=" + turmaAluno
				+ ", turmaAula=" + turmaAula + ", turmaProfessor=" + turmaProfessor + ", trabalho=" + trabalho
				+ ", atividade=" + atividade + ", prova=" + prova + ", materia=" + materia + ", discord=" + discord
				+ "]";
	}
}
