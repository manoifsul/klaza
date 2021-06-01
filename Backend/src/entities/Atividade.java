package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Atividade {

	private Long idAtividade;
	private String nome;
	private String descricao;
	private LocalDateTime inicio;
	private List<Arquivo> arquivo;
	private List<AdministradorAtividade> administradorAtividade;
	private List<ProfessorAtividade> professorAtividade;
	private Turma turma;
	
	public Atividade() {
		this.idAtividade = (long) 0;
		this.nome = "";
		this.descricao = "";
		this.inicio = null;
		this.arquivo = new ArrayList<Arquivo>();
		this.administradorAtividade = new ArrayList<AdministradorAtividade>();
		this.professorAtividade = new ArrayList<ProfessorAtividade>();
		this.turma = new Turma();
	}

	public Atividade(Long idAtividade, String nome, String descricao, LocalDateTime inicio, List<Arquivo> arquivo,
			List<AdministradorAtividade> administradorAtividade, List<ProfessorAtividade> professorAtividade,
			Turma turma) {
		this.idAtividade = idAtividade;
		this.nome = nome;
		this.descricao = descricao;
		this.inicio = inicio;
		this.arquivo = arquivo;
		this.administradorAtividade = administradorAtividade;
		this.professorAtividade = professorAtividade;
		this.turma = turma;
	}

	public Long getIdAtividade() {
		return idAtividade;
	}

	public void setIdAtividade(Long idAtividade) {
		this.idAtividade = idAtividade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public List<Arquivo> getArquivo() {
		return arquivo;
	}

	public void setArquivo(List<Arquivo> arquivo) {
		this.arquivo = arquivo;
	}

	public List<AdministradorAtividade> getAdministradorAtividade() {
		return administradorAtividade;
	}

	public void setAdministradorAtividade(List<AdministradorAtividade> administradorAtividade) {
		this.administradorAtividade = administradorAtividade;
	}

	public List<ProfessorAtividade> getProfessorAtividade() {
		return professorAtividade;
	}

	public void setProfessorAtividade(List<ProfessorAtividade> professorAtividade) {
		this.professorAtividade = professorAtividade;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	@Override
	public String toString() {
		return "Atividade [idAtividade=" + idAtividade + ", nome=" + nome + ", descricao=" + descricao + ", inicio="
				+ inicio + ", arquivo=" + arquivo + ", administradorAtividade=" + administradorAtividade
				+ ", professorAtividade=" + professorAtividade + ", turma=" + turma + "]";
	}
}
