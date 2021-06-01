package entities;

public class Resposta {
	
	private Long idResposta;
	private Aluno aluno;
	private Questao questao;
	private String resposta;
	private int nroTentativa; // TERMINAR DE ARRUMAR DPS
	
	public Resposta() {
		this.idResposta = (long) 0;
		this.aluno = new Aluno();
		this.questao = new Questao();
		this.resposta = "";
	}

	public Resposta(Long idResposta, Aluno aluno, Questao questao, String resposta) {
		this.idResposta = idResposta;
		this.aluno = aluno;
		this.questao = questao;
		this.resposta = resposta;
	}

	public Long getIdResposta() {
		return idResposta;
	}

	public void setIdResposta(Long idResposta) {
		this.idResposta = idResposta;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
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

	@Override
	public String toString() {
		return "Resposta [idResposta=" + idResposta + ", aluno=" + aluno + ", questao=" + questao + ", resposta="
				+ resposta + "]";
	}
}
