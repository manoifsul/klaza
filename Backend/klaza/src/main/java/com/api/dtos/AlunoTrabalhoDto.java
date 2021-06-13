package com.api.dtos;

import com.api.entities.Aluno;
import com.api.entities.Resposta;
import com.api.entities.Trabalho;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AlunoTrabalhoDto {

	private long idAlunoTrabalho;
	private long tempo;
	private LocalDateTime inicio;
	private LocalDateTime finalizada;
	private Aluno aluno;
	private Trabalho trabalho;
	private List<Resposta> resposta;
	
	public AlunoTrabalhoDto() {
		this.idAlunoTrabalho = 0;
		this.tempo = 0;
		this.inicio = LocalDateTime.now();
		this.finalizada = null;
		this.aluno = new Aluno();
		this.trabalho = new Trabalho();
		this.resposta = new ArrayList<Resposta>();
	}

	public AlunoTrabalhoDto(long idAlunoTrabalho, long tempo, LocalDateTime inicio, LocalDateTime finalizada, Aluno aluno,
							Trabalho trabalho, List<Resposta> resposta) {
		this.idAlunoTrabalho = idAlunoTrabalho;
		this.tempo = tempo;
		this.inicio = inicio;
		this.finalizada = finalizada;
		this.aluno = aluno;
		this.trabalho = trabalho;
		this.resposta = resposta;
	}

	public long getIdAlunoTrabalho() {
		return idAlunoTrabalho;
	}

	public void setIdAlunoTrabalho(long idAlunoTrabalho) {
		this.idAlunoTrabalho = idAlunoTrabalho;
	}

	public long getTempo() {
		return tempo;
	}

	public void setTempo(long tempo) {
		this.tempo = tempo;
	}

	public LocalDateTime getInicio() {
		return inicio;
	}

	public void setInicio(LocalDateTime inicio) {
		this.inicio = inicio;
	}

	public LocalDateTime getFinalizada() {
		return finalizada;
	}

	public void setFinalizada(LocalDateTime finalizada) {
		this.finalizada = finalizada;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Trabalho getTrabalho() {
		return trabalho;
	}

	public void setTrabalho(Trabalho trabalho) {
		this.trabalho = trabalho;
	}

	public List<Resposta> getResposta() {
		return resposta;
	}

	public void setResposta(List<Resposta> resposta) {
		this.resposta = resposta;
	}

	@Override
	public String toString() {
		return "AlunoTrabalho [idAlunoTrabalho=" + idAlunoTrabalho + ", tempo=" + tempo + ", inicio=" + inicio
				+ ", finalizada=" + finalizada + ", aluno=" + aluno + ", trabalho=" + trabalho + ", resposta="
				+ resposta + "]";
	}
}
