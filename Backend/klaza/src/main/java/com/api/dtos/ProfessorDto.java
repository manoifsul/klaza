package com.api.dtos;

import com.api.entities.Materia;

import java.util.ArrayList;
import java.util.List;

public class ProfessorDto {
	
	private long idProfessor;
	private String nome;
	private String matricula;
	private String senha;
	private String email;
	private List<Materia> materia;
	private List<Long> idTurmas;
	
	public ProfessorDto() {
		this.idProfessor = 0;
		this.nome = "";
		this.matricula = "";
		this.senha = "";
		this.email = "";
		this.materia = new ArrayList<Materia>();
		this.idTurmas = new ArrayList<Long>();
	}

	public ProfessorDto(long idProfessor, String nome, String matricula, String senha, String email, List<Materia> materia, List<Long> idTurmas) {
		this.idProfessor = idProfessor;
		this.nome = nome;
		this.matricula = matricula;
		this.senha = senha;
		this.email = email;
		this.materia = materia;
		this.idTurmas = idTurmas;
	}

	public long getIdProfessor() {
		return idProfessor;
	}

	public void setIdProfessor(long idProfessor) {
		this.idProfessor = idProfessor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Materia> getMateria() {
		return materia;
	}

	public void setMateria(List<Materia> materia) {
		this.materia = materia;
	}

	public List<Long> getIdTurmas() {
		return idTurmas;
	}

	public void setIdTurmas(List<Long> idTurmas) {
		this.idTurmas = idTurmas;
	}

	@Override
	public String toString() {
		return "Professor{" +
				"idProfessor=" + idProfessor +
				", nome='" + nome + '\'' +
				", matricula='" + matricula + '\'' +
				", senha='" + senha + '\'' +
				", email='" + email + '\'' +
				", materia=" + materia +
				", idTurmas=" + idTurmas +
				'}';
	}
}
