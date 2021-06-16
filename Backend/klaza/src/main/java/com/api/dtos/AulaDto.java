package com.api.dtos;

import com.api.entities.Arquivo;
import com.api.entities.Materia;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AulaDto {

	private long idAula;
	private String nome;
	private Materia materia;
	private String descricao;
	private LocalDateTime inicio;
	private String link;
	private List<Arquivo> arquivo;
	private long idTurma;
	
	public AulaDto() {
	this.idAula = 0;
	this.nome = "";
	this.materia = new Materia();
	this.descricao = "";
	this.inicio = null;
	this.link = "";
	this.arquivo = new ArrayList<Arquivo>();
	this.idTurma = 0;
	}

	public AulaDto(long idAula, String nome, Materia materia, String descricao, LocalDateTime inicio, String link, List<Arquivo> arquivo, long idturma) {
		this.idAula = idAula;
		this.nome = nome;
		this.materia = materia;
		this.descricao = descricao;
		this.inicio = inicio;
		this.link = link;
		this.arquivo = arquivo;
		this.idTurma = idturma;
	}

	public long getIdAula() {
		return idAula;
	}

	public void setIdAula(long idAula) {
		this.idAula = idAula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
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

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public List<Arquivo> getArquivo() {
		return arquivo;
	}

	public void setArquivo(List<Arquivo> arquivo) {
		this.arquivo = arquivo;
	}

	public long getIdTurma() {
		return idTurma;
	}

	public void setIdTurma(long idTurma) {
		this.idTurma = idTurma;
	}

	@Override
	public String toString() {
		return "Aula{" +
				"idAula=" + idAula +
				", nome='" + nome + '\'' +
				", materia=" + materia +
				", descricao='" + descricao + '\'' +
				", inicio=" + inicio +
				", link='" + link + '\'' +
				", arquivo=" + arquivo +
				", idturma=" + idTurma +
				'}';
	}
}
