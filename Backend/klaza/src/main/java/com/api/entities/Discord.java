package com.api.entities;

public class Discord {

	private long idDiscord;
	private String notificacoes;
	private String provasTrabalhos;
	
	public Discord() {
		this.idDiscord = 0;
		this.notificacoes = "";
		this.provasTrabalhos = "";
	}

	public Discord(long idDiscord, String notificacoes, String provasTrabalhos) {
		this.idDiscord = idDiscord;
		this.notificacoes = notificacoes;
		this.provasTrabalhos = provasTrabalhos;
	}

	public long getIdDiscord() {
		return idDiscord;
	}

	public void setIdDiscord(long idDiscord) {
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

	@Override
	public String toString() {
		return "Discord{" +
				"idDiscord=" + idDiscord +
				", notificacoes='" + notificacoes + '\'' +
				", provasTrabalhos='" + provasTrabalhos + '\'' +
				'}';
	}
}
