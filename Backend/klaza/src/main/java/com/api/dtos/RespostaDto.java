package com.api.dtos;

import com.api.entities.Questao;

public class RespostaDto {
	
	private long idResposta;
	private Questao questao;
	private String resposta;
	private int nroTentativa;
	private long idAluno;
	
	public RespostaDto() {
		this.idResposta = 0;
		this.questao = new Questao();
		this.resposta = "";
		this.nroTentativa = 0;
		this.idAluno = 0;
	}

	public RespostaDto(long idResposta, Questao questao, String resposta, int nroTentativa, long idAluno) {
		this.idResposta = idResposta;
		this.questao = questao;
		this.resposta = resposta;
		this.nroTentativa = nroTentativa;
		this.idAluno = idAluno;
	}

	public long getIdResposta() {
		return idResposta;
	}

	public void setIdResposta(long idResposta) {
		this.idResposta = idResposta;
	}

	public Questao getQuestao() {
		return questao;
	}

	public void setQuestao(Questao questao) {
		this.questao = questao;
	}

	public String getResposta() {
		return resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
	}

	public int getNroTentativa() {
		return nroTentativa;
	}

	public void setNroTentativa(int nroTentativa) {
		this.nroTentativa = nroTentativa;
	}

	public long getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(long idAluno) {
		this.idAluno = idAluno;
	}

	@Override
	public String toString() {
		return "Resposta{" +
				"idResposta=" + idResposta +
				", questao=" + questao +
				", resposta='" + resposta + '\'' +
				", nroTentativa=" + nroTentativa +
				", idAluno=" + idAluno +
				'}';
	}
}
