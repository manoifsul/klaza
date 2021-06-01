package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Trabalho {

	private Long idTrabalho;
	private String nome;
	private LocalDateTime prazo;
	private String descricao;
	private LocalDateTime inicio;
	private Long tempo;
	private byte tentativas;
	private byte tipo;
	private List<ProfessorTrabalho> professorTrabalho;
	private List<Arquivo> arquivo;
	private List<AdministradorTrabalho> administradorTrabalho;
	private List<Questao> questao;
	private Turma turma;
	
	public Trabalho() {
		this.idTrabalho = (long) 0;
		this.nome = "";
		this.prazo = null;
		this.descricao = "";
		this.inicio = null;
		this.tempo = (long) 0;
		this.tentativas = 0;
		this.tipo = 0;
		this.professorTrabalho = new ArrayList<ProfessorTrabalho>();
		this.arquivo = new ArrayList<Arquivo>();
		this.administradorTrabalho = new ArrayList<AdministradorTrabalho>();
		this.questao = new ArrayList<Questao>();
		this.turma = new Turma();
	}

	public Trabalho(Long idTrabalho, String nome, LocalDateTime prazo, String descricao, LocalDateTime inicio, Long tempo, byte tentativas, byte tipo,
					List<ProfessorTrabalho> professorTrabalho, List<Arquivo> arquivo, List<AdministradorTrabalho> administradorTrabalho, List<Questao> questao, Turma turma) {
		this.idTrabalho = idTrabalho;
		this.nome = nome;
		this.prazo = prazo;
		this.descricao = descricao;
		this.inicio = inicio;
		this.tempo = tempo;
		this.tentativas = tentativas;
		this.tipo = tipo;
		this.professorTrabalho = professorTrabalho;
		this.arquivo = arquivo;
		this.administradorTrabalho = administradorTrabalho;
		this.questao = questao;
		this.turma = turma;
	}

	public Long getIdTrabalho() {
		return idTrabalho;
	}

	public void setIdTrabalho(Long idTrabalho) {
		this.idTrabalho = idTrabalho;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDateTime getPrazo() {
		return prazo;
	}

	public void setPrazo(LocalDateTime prazo) {
		this.prazo = prazo;
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

	public Long getTempo() {
		return tempo;
	}

	public void setTempo(Long tempo) {
		this.tempo = tempo;
	}

	public byte getTentativas() {
		return tentativas;
	}

	public void setTentativas(byte tentativas) {
		this.tentativas = tentativas;
	}

	public byte getTipo() {
		return tipo;
	}

	public void setTipo(byte tipo) {
		this.tipo = tipo;
	}

	public List<ProfessorTrabalho> getProfessorTrabalho() {
		return professorTrabalho;
	}

	public void setProfessorTrabalho(List<ProfessorTrabalho> professorTrabalho) {
		this.professorTrabalho = professorTrabalho;
	}

	public List<Arquivo> getArquivo() {
		return arquivo;
	}

	public void setArquivo(List<Arquivo> arquivo) {
		this.arquivo = arquivo;
	}

	public List<AdministradorTrabalho> getAdministradorTrabalho() {
		return administradorTrabalho;
	}

	public void setAdministradorTrabalho(List<AdministradorTrabalho> administradorTrabalho) {
		this.administradorTrabalho = administradorTrabalho;
	}

	public List<Questao> getQuestao() {
		return questao;
	}

	public void setQuestao(List<Questao> questao) {
		this.questao = questao;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	@Override
	public String toString() {
		return "Trabalho{" +
				"idTrabalho=" + idTrabalho +
				", nome='" + nome + '\'' +
				", prazo=" + prazo +
				", descricao='" + descricao + '\'' +
				", inicio=" + inicio +
				", tempo=" + tempo +
				", tentativas=" + tentativas +
				", tipo=" + tipo +
				", professorTrabalho=" + professorTrabalho +
				", arquivo=" + arquivo +
				", administradorTrabalho=" + administradorTrabalho +
				", questao=" + questao +
				", turma=" + turma +
				'}';
	}
}
