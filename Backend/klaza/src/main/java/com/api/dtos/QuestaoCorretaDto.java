package com.api.dtos;

import com.api.entities.QuestaoAlternativa;

import java.util.ArrayList;
import java.util.List;

public class QuestaoCorretaDto {

	private long idQuestaoCorreta;
	private List<QuestaoAlternativa> questaoAlternativa;
	private long idQuestao;
	
	public QuestaoCorretaDto() {
		this.idQuestaoCorreta = 0;
		this.questaoAlternativa = new ArrayList<QuestaoAlternativa>();
		this.idQuestao = 0;
	}

	public QuestaoCorretaDto(long idQuestaoCorreta, List<QuestaoAlternativa> questaoAlternativa, long idQuestao) {
		this.idQuestaoCorreta = idQuestaoCorreta;
		this.questaoAlternativa = questaoAlternativa;
		this.idQuestao = idQuestao;
	}

	public long getIdQuestaoCorreta() {
		return idQuestaoCorreta;
	}

	public void setIdQuestaoCorreta(long idQuestaoCorreta) {
		this.idQuestaoCorreta = idQuestaoCorreta;
	}

	public List<QuestaoAlternativa> getQuestaoAlternativa() {
		return questaoAlternativa;
	}

	public void setQuestaoAlternativa(List<QuestaoAlternativa> questaoAlternativa) {
		this.questaoAlternativa = questaoAlternativa;
	}

	public long getIdQuestao() {
		return idQuestao;
	}

	public void setIdQuestao(long idQuestao) {
		this.idQuestao = idQuestao;
	}

	@Override
	public String toString() {
		return "QuestaoCorreta{" +
				"idQuestaoCorreta=" + idQuestaoCorreta +
				", questaoAlternativa=" + questaoAlternativa +
				", idQuestao=" + idQuestao +
				'}';
	}
}
