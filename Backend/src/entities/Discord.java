package entities;

public class Discord {

	private Long idDiscord;
	private String notificacoes;
	private String provasTrabalhos;
	private Turma turma;
	
	public Discord() {
		this.idDiscord = (long) 0;
		this.notificacoes = "";
		this.provasTrabalhos = "";
		this.turma = new Turma();
	}

	public Discord(Long idDiscord, String notificacoes, String provasTrabalhos, Turma turma) {
		super();
		this.idDiscord = idDiscord;
		this.notificacoes = notificacoes;
		this.provasTrabalhos = provasTrabalhos;
		this.turma = turma;
	}

	public Long getIdDiscord() {
		return idDiscord;
	}

	public void setIdDiscord(Long idDiscord) {
		this.idDiscord = idDiscord;
	}

	public String getNotificacoes() {
		return notificacoes;
	}

	public void setNotificacoes(String notificacoes) {
		this.notificacoes = notificacoes;
	}

	public String getProvasTrabalhos() {
		return provasTrabalhos;
	}

	public void setProvasTrabalhos(String provasTrabalhos) {
		this.provasTrabalhos = provasTrabalhos;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	@Override
	public String toString() {
		return "Discord [idDiscord=" + idDiscord + ", notificacoes=" + notificacoes + ", provasTrabalhos="
				+ provasTrabalhos + ", turma=" + turma + "]";
	}
}
