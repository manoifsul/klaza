package entities;

import java.util.ArrayList;
import java.util.List;

public class Questao {

	private Long idQuestao;
	private String pergunta;
	private byte tipo;
	private List<QuestaoCorreta> questaoCorreta;
	private List<QuestaoAlternativa> questaoAlternativa;
	private Prova prova;
	private Trabalho trabalho;

	public Questao() {
		this.idQuestao = (long) 0;
		this.pergunta = "";
		this.tipo = 0;
		this.questaoCorreta = new ArrayList<QuestaoCorreta>();
		this.questaoAlternativa = new ArrayList<QuestaoAlternativa>();
		this.prova = new Prova();
		this.trabalho = new Trabalho();
	}

	public Questao(Long idQuestao, String pergunta, byte tipo, List<QuestaoCorreta> questaoCorreta, List<QuestaoAlternativa> questaoAlternativa, Prova prova, Trabalho trabalho) {
		this.idQuestao = idQuestao;
		this.pergunta = pergunta;
		this.tipo = tipo;
		this.questaoCorreta = questaoCorreta;
		this.questaoAlternativa = questaoAlternativa;
		this.prova = prova;
		this.trabalho = trabalho;
	}

	public Long getIdQuestao() {
		return idQuestao;
	}

	public void setIdQuestao(Long idQuestao) {
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

	public Prova getProva() {
		return prova;
	}

	public void setProva(Prova prova) {
		this.prova = prova;
	}

	public Trabalho getTrabalho() {
		return trabalho;
	}

	public void setTrabalho(Trabalho trabalho) {
		this.trabalho = trabalho;
	}

	@Override
	public String toString() {
		return "Questao{" +
				"idQuestao=" + idQuestao +
				", pergunta='" + pergunta + '\'' +
				", tipo=" + tipo +
				", questaoCorreta=" + questaoCorreta +
				", questaoAlternativa=" + questaoAlternativa +
				", prova=" + prova +
				", trabalho=" + trabalho +
				'}';
	}
}
