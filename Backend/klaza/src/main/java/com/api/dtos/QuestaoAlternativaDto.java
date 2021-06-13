package com.api.dtos;
public class QuestaoAlternativaDto {

	private long idQuestaoAlternativa;
	private String resposta;
	private long idQuestao;
	
	public QuestaoAlternativaDto() {
		this.idQuestaoAlternativa = 0;
		this.resposta = "";
		this.idQuestao = 0;
	}

	public QuestaoAlternativaDto(long idQuestaoAlternativa, String resposta, long idQuestao) {
		this.idQuestaoAlternativa = idQuestaoAlternativa;
		this.resposta = resposta;
		this.idQuestao = idQuestao;
	}

	public long getIdQuestaoAlternativa() {
		return idQuestaoAlternativa;
	}

	public void setIdQuestaoAlternativa(long idQuestaoAlternativa) {
		this.idQuestaoAlternativa = idQuestaoAlternativa;
	}

	public String getResposta() {
		return resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
	}

	public long getIdQuestao() {
		return idQuestao;
	}

	public void setIdQuestao(long idQuestao) {
		this.idQuestao = idQuestao;
	}

	@Override
	public String toString() {
		return "QuestaoAlternativa{" +
				"idQuestaoAlternativa=" + idQuestaoAlternativa +
				", resposta='" + resposta + '\'' +
				", idQuestao=" + idQuestao +
				'}';
	}
}
