package com.api.dtos;

import com.api.entities.Administrador;
import com.api.entities.Arquivo;
import com.api.entities.Materia;
import com.api.entities.Professor;
import com.api.entities.Questao;
import com.api.entities.Resposta;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TrabalhoDto {

	private long idTrabalho;
	private String nome;
	private LocalDateTime prazo;
	private String descricao;
	private LocalDateTime inicio;
	private long tempo;
	private byte tentativas;
	private byte tipo;
	private List<Professor> professor;
	private List<Administrador> administrador;
	private List<Arquivo> arquivo;
	private List<Questao> questao;
	private Materia materia;
	private List<Resposta> resposta;
	private long idTurma;
	
	public TrabalhoDto() {
		this.idTrabalho = 0;
		this.nome = "";
		this.prazo = null;
		this.descricao = "";
		this.inicio = null;
		this.tempo = 0;
		this.tentativas = 0;
		this.tipo = 0;
		this.professor = new ArrayList<Professor>();
		this.arquivo = new ArrayList<Arquivo>();
		this.administrador = new ArrayList<Administrador>();
		this.questao = new ArrayList<Questao>();
		this.materia = new Materia();
		this.resposta = new ArrayList<Resposta>();
		this.idTurma = 0;
	}

	public TrabalhoDto(long idTrabalho, String nome, LocalDateTime prazo, String descricao, LocalDateTime inicio, long tempo, byte tentativas, byte tipo, List<Professor> professor,
					   List<Administrador> administrador, List<Arquivo> arquivo, List<Questao> questao, Materia materia, List<Resposta> resposta, long idTurma) {
		this.idTrabalho = idTrabalho;
		this.nome = nome;
		this.prazo = prazo;
		this.descricao = descricao;
		this.inicio = inicio;
		this.tempo = tempo;
		this.tentativas = tentativas;
		this.tipo = tipo;
		this.professor = professor;
		this.administrador = administrador;
		this.arquivo = arquivo;
		this.questao = questao;
		this.materia = materia;
		this.resposta = resposta;
		this.idTurma = idTurma;
	}

	public long getIdTrabalho() {
		return idTrabalho;
	}

	public void setIdTrabalho(long idTrabalho) {
		this.idTrabalho = idTrabalho;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDateTime getPrazo() {
		return prazo;
	}

	public void setPrazo(LocalDateTime prazo) {
		this.prazo = prazo;
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

	public long getTempo() {
		return tempo;
	}

	public void setTempo(long tempo) {
		this.tempo = tempo;
	}

	public byte getTentativas() {
		return tentativas;
	}

	public void setTentativas(byte tentativas) {
		this.tentativas = tentativas;
	}

	public byte getTipo() {
		return tipo;
	}

	public void setTipo(byte tipo) {
		this.tipo = tipo;
	}

	public List<Professor> getProfessor() {
		return professor;
	}

	public void setProfessor(List<Professor> professor) {
		this.professor = professor;
	}

	public List<Administrador> getAdministrador() {
		return administrador;
	}

	public void setAdministrador(List<Administrador> administrador) {
		this.administrador = administrador;
	}

	public List<Arquivo> getArquivo() {
		return arquivo;
	}

	public void setArquivo(List<Arquivo> arquivo) {
		this.arquivo = arquivo;
	}

	public List<Questao> getQuestao() {
		return questao;
	}

	public void setQuestao(List<Questao> questao) {
		this.questao = questao;
	}

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	public List<Resposta> getResposta() {
		return resposta;
	}

	public void setResposta(List<Resposta> resposta) {
		this.resposta = resposta;
	}

	public long getIdTurma() {
		return idTurma;
	}

	public void setIdTurma(long idTurma) {
		this.idTurma = idTurma;
	}

	@Override
	public String toString() {
		return "Trabalho{" +
				"idTrabalho=" + idTrabalho +
				", nome='" + nome + '\'' +
				", prazo=" + prazo +
				", descricao='" + descricao + '\'' +
				", inicio=" + inicio +
				", tempo=" + tempo +
				", tentativas=" + tentativas +
				", tipo=" + tipo +
				", professor=" + professor +
				", administrador=" + administrador +
				", arquivo=" + arquivo +
				", questao=" + questao +
				", materia=" + materia +
				", resposta=" + resposta +
				", idTurma=" + idTurma +
				'}';
	}
}
