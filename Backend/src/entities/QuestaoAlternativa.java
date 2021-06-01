package entities;
public class QuestaoAlternativa {

	private Long idQuestaoAlternativa;
	private String resposta;
	
	public QuestaoAlternativa() {
		this.idQuestaoAlternativa = (long) 0;
		this.resposta = "";
	}

	public QuestaoAlternativa(Long idQuestaoAlternativa, String resposta) {
		this.idQuestaoAlternativa = idQuestaoAlternativa;
		this.resposta = resposta;
	}

	public Long getIdQuestaoAlternativa() {
		return idQuestaoAlternativa;
	}

	public void setIdQuestaoAlternativa(Long idQuestaoAlternativa) {
		this.idQuestaoAlternativa = idQuestaoAlternativa;
	}

	public String getResposta() {
		return resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
	}

	@Override
	public String toString() {
		return "QuestaoAlternativa{" +
				"idQuestaoAlternativa=" + idQuestaoAlternativa +
				", resposta='" + resposta + '\'' +
				'}';
	}
}
