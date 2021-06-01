package entities;

import java.util.ArrayList;
import java.util.List;

public class QuestaoCorreta {

	private Long idQuestaoCorreta;
	private List<QuestaoAlternativa> questaoAlternativa;
	
	public QuestaoCorreta() {
		this.idQuestaoCorreta = (long) 0;
		this.questaoAlternativa = new ArrayList<QuestaoAlternativa>();
	}

	public QuestaoCorreta(Long idQuestaoCorreta, List<QuestaoAlternativa> questaoAlternativa) {
		this.idQuestaoCorreta = idQuestaoCorreta;
		this.questaoAlternativa = questaoAlternativa;
	}

	public Long getIdQuestaoCorreta() {
		return idQuestaoCorreta;
	}

	public void setIdQuestaoCorreta(Long idQuestaoCorreta) {
		this.idQuestaoCorreta = idQuestaoCorreta;
	}

	public List<QuestaoAlternativa> getQuestaoAlternativa() {
		return questaoAlternativa;
	}

	public void setQuestaoAlternativa(List<QuestaoAlternativa> questaoAlternativa) {
		this.questaoAlternativa = questaoAlternativa;
	}

	@Override
	public String toString() {
		return "QuestaoCorreta [idQuestaoCorreta=" + idQuestaoCorreta + ", questaoAlternativa=" + questaoAlternativa
				+ "]";
	}
}
