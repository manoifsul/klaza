package com.api.entities;

import java.util.ArrayList;
import java.util.List;

public class Turma {
	
	private long idTurma;
	private String nome;
	private List<Aula> aula;
	private List<Trabalho> trabalho;
	private List<Atividade> atividade;
	private List<Prova> prova;
	private List<Aluno> aluno;
	private Materia materia;
	private Discord discord;

	
	public Turma() {
		this.idTurma = 0;
		this.nome = "";
		this.aula = new ArrayList<Aula>();
		this.trabalho = new ArrayList<Trabalho>();
		this.atividade = new ArrayList<Atividade>();
		this.prova = new ArrayList<Prova>();
		this.aluno = new ArrayList<Aluno>();
		this.materia = new Materia();
		this.discord = new Discord();
	}

	public Turma(long idTurma, String nome, List<Aula> aula, List<Trabalho> trabalho, List<Atividade> atividade, List<Prova> prova, List<Aluno> aluno, Materia materia, Discord discord) {
		this.idTurma = idTurma;
		this.nome = nome;
		this.aula = aula;
		this.trabalho = trabalho;
		this.atividade = atividade;
		this.prova = prova;
		this.aluno = aluno;
		this.materia = materia;
		this.discord = discord;
	}

	public long getIdTurma() {
		return idTurma;
	}

	public void setIdTurma(long idTurma) {
		this.idTurma = idTurma;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Aula> getAula() {
		return aula;
	}

	public void setAula(List<Aula> aula) {
		this.aula = aula;
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

	public List<Aluno> getAluno() {
		return aluno;
	}

	public void setAluno(List<Aluno> aluno) {
		this.aluno = aluno;
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
		return "Turma{" +
				"idTurma=" + idTurma +
				", nome='" + nome + '\'' +
				", aula=" + aula +
				", trabalho=" + trabalho +
				", atividade=" + atividade +
				", prova=" + prova +
				", aluno=" + aluno +
				", materia=" + materia +
				", discord=" + discord +
				'}';
	}
}
