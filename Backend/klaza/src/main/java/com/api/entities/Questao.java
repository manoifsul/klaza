package com.api.entities;

import java.util.ArrayList;
import java.util.List;

public class Questao {

	private long idQuestao;
	private String pergunta;
	private byte tipo;
	private List<QuestaoCorreta> questaoCorreta;
	private List<QuestaoAlternativa> questaoAlternativa;

	public Questao() {
		this.idQuestao = 0;
		this.pergunta = "";
		this.tipo = 0;
		this.questaoCorreta = new ArrayList<QuestaoCorreta>();
		this.questaoAlternativa = new ArrayList<QuestaoAlternativa>();
	}

	public Questao(long idQuestao, String pergunta, byte tipo, List<QuestaoCorreta> questaoCorreta, List<QuestaoAlternativa> questaoAlternativa) {
		this.idQuestao = idQuestao;
		this.pergunta = pergunta;
		this.tipo = tipo;
		this.questaoCorreta = questaoCorreta;
		this.questaoAlternativa = questaoAlternativa;
	}

	public long getIdQuestao() {
		return idQuestao;
	}

	public void setIdQuestao(long idQuestao) {
		this.idQuestao = idQuestao;
	}

	public String getPergunta() {
		return pergunta;
	}

	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}

	public byte getTipo() {
		return tipo;
	}

	public void setTipo(byte tipo) {
		this.tipo = tipo;
	}

	public List<QuestaoCorreta> getQuestaoCorreta() {
		return questaoCorreta;
	}

	public void setQuestaoCorreta(List<QuestaoCorreta> questaoCorreta) {
		this.questaoCorreta = questaoCorreta;
	}

	public List<QuestaoAlternativa> getQuestaoAlternativa() {
		return questaoAlternativa;
	}

	public void setQuestaoAlternativa(List<QuestaoAlternativa> questaoAlternativa) {
		this.questaoAlternativa = questaoAlternativa;
	}

	@Override
	public String toString() {
		return "Questao{" +
				"idQuestao=" + idQuestao +
				", pergunta='" + pergunta + '\'' +
				", tipo=" + tipo +
				", questaoCorreta=" + questaoCorreta +
				", questaoAlternativa=" + questaoAlternativa +
				'}';
	}
}
