package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Aula {

	private Long idAula;
	private String nome;
	private String materia;
	private String descricao;
	private LocalDateTime inicio;
	private String link;
	private List<Arquivo> arquivo;
	private List<Presenca> presenca;
	private List<TurmaAula> turmaAula;
	
	public Aula() {
	this.idAula = (long) 0;
	this.nome = "";
	this.materia = "";
	this.descricao = "";
	this.inicio = null;
	this.link = "";
	this.arquivo = new ArrayList<Arquivo>();
	this.presenca = new ArrayList<Presenca>();
	this.turmaAula = new ArrayList<TurmaAula>();
	}

	public Aula(Long idAula, String nome, String materia, String descricao, LocalDateTime inicio, String link,
			List<Arquivo> arquivo, List<Presenca> presenca, List<TurmaAula> turmaAula) {
		this.idAula = idAula;
		this.nome = nome;
		this.materia = materia;
		this.descricao = descricao;
		this.inicio = inicio;
		this.link = link;
		this.arquivo = arquivo;
		this.presenca = presenca;
		this.turmaAula = turmaAula;
	}

	public Long getIdAula() {
		return idAula;
	}

	public void setIdAula(Long idAula) {
		this.idAula = idAula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMateria() {
		return materia;
	}

	public void setMateria(String materia) {
		this.materia = materia;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDateTime getInicio() {
		return inicio;
	}

	public void setInicio(LocalDateTime inicio) {
		this.inicio = inicio;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public List<Arquivo> getArquivo() {
		return arquivo;
	}

	public void setArquivo(List<Arquivo> arquivo) {
		this.arquivo = arquivo;
	}

	public List<Presenca> getPresenca() {
		return presenca;
	}

	public void setPresenca(List<Presenca> presenca) {
		this.presenca = presenca;
	}

	public List<TurmaAula> getTurmaAula() {
		return turmaAula;
	}

	public void setTurmaAula(List<TurmaAula> turmaAula) {
		this.turmaAula = turmaAula;
	}

	@Override
	public String toString() {
		return "Aula [idAula=" + idAula + ", nome=" + nome + ", materia=" + materia + ", descricao=" + descricao
				+ ", inicio=" + inicio + ", link=" + link + ", arquivo=" + arquivo + ", presenca=" + presenca
				+ ", turmaAula=" + turmaAula + "]";
	}
}
