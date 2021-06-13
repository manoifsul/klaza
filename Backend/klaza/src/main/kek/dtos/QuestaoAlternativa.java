package entities;
public class QuestaoAlternativa {

	private long idQuestaoAlternativa;
	private String resposta;
	private long idQuestao;
	
	public QuestaoAlternativa() {
		this.idQuestaoAlternativa = 0;
		this.resposta = "";
		this.idQuestao = 0;
	}

	public QuestaoAlternativa(long idQuestaoAlternativa, String resposta, long idQuestao) {
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
