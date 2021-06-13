package com.api.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Atividade {

	private long idAtividade;
	private String nome;
	private String descricao;
	private LocalDateTime inicio;
	private List<Arquivo> arquivo;
	private List<Administrador> administrador;
	private List<Professor> professor;
	private List<Resposta> resposta;
	private Materia materia;
	private long idTurma;
	
	public Atividade() {
		this.idAtividade = (long) 0;
		this.nome = "";
		this.descricao = "";
		this.inicio = null;
		this.arquivo = new ArrayList<Arquivo>();
		this.administrador = new ArrayList<Administrador>();
		this.professor = new ArrayList<Professor>();
		this.resposta = new ArrayList<Resposta>();
		this.materia = new Materia();
		this.idTurma = 0;
	}

	public Atividade(long idAtividade, String nome, String descricao, LocalDateTime inicio, List<Arquivo> arquivo, List<Administrador> administrador,
					 List<Professor> professor, List<Resposta> resposta, Materia materia, long idTurma) {
		this.idAtividade = idAtividade;
		this.nome = nome;
		this.descricao = descricao;
		this.inicio = inicio;
		this.arquivo = arquivo;
		this.administrador = administrador;
		this.professor = professor;
		this.resposta = resposta;
		this.materia = materia;
		this.idTurma = idTurma;
	}

	public long getIdAtividade() {
		return idAtividade;
	}

	public void setIdAtividade(long idAtividade) {
		this.idAtividade = idAtividade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDateTime getInicio() {
		return inicio;
	}

	public void setInicio(LocalDateTime inicio) {
		this.inicio = inicio;
	}

	public List<Arquivo> getArquivo() {
		return arquivo;
	}

	public void setArquivo(List<Arquivo> arquivo) {
		this.arquivo = arquivo;
	}

	public List<Administrador> getAdministrador() {
		return administrador;
	}

	public void setAdministrador(List<Administrador> administrador) {
		this.administrador = administrador;
	}

	public List<Professor> getProfessor() {
		return professor;
	}

	public void setProfessor(List<Professor> professor) {
		this.professor = professor;
	}

	public List<Resposta> getResposta() {
		return resposta;
	}

	public void setResposta(List<Resposta> resposta) {
		this.resposta = resposta;
	}

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	public long getIdTurma() {
		return idTurma;
	}

	public void setIdTurma(long idTurma) {
		this.idTurma = idTurma;
	}

	@Override
	public String toString() {
		return "Atividade{" +
				"idAtividade=" + idAtividade +
				", nome='" + nome + '\'' +
				", descricao='" + descricao + '\'' +
				", inicio=" + inicio +
				", arquivo=" + arquivo +
				", administrador=" + administrador +
				", professor=" + professor +
				", resposta=" + resposta +
				", materia=" + materia +
				", idTurma=" + idTurma +
				'}';
	}
}
