package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Prova {

	private Long idProva;
	private String nome;
	private LocalDateTime prazo;
	private String descricao;
	private LocalDateTime inicio;
	private Long tempo;
	private int tentativas;
	private List<Arquivo> arquivo;
	private List<AdministradorProva> administradorProva;
	private List<ProfessorProva> professorProva;
	private List<AlunoProva> alunoProva;
	private List<Questao> questao;



	private Turma turma;


	public Prova() {
		this.idProva = (long) 0;
		this.nome = "";
		this.prazo = null;
		this.descricao = "";
		this.inicio = null;
		this.tempo = (long) 0;
		this.tentativas = 0;
		this.arquivo = new ArrayList<Arquivo>();
		this.administradorProva = new ArrayList<AdministradorProva>();
		this.professorProva = new ArrayList<ProfessorProva>();
		this.alunoProva = new ArrayList<AlunoProva>();
		this.questao = new ArrayList<Questao>();
		this.turma = new Turma();
	}

	public Prova(Long idProva, String nome, LocalDateTime prazo, String descricao, LocalDateTime inicio, Long tempo, int tentativas, List<Arquivo> arquivo, List<AdministradorProva> administradorProva,
				 List<ProfessorProva> professorProva, List<AlunoProva> alunoProva, List<Questao> questao, Turma turma) {
		this.idProva = idProva;
		this.nome = nome;
		this.prazo = prazo;
		this.descricao = descricao;
		this.inicio = inicio;
		this.tempo = tempo;
		this.tentativas = tentativas;
		this.arquivo = arquivo;
		this.administradorProva = administradorProva;
		this.professorProva = professorProva;
		this.alunoProva = alunoProva;
		this.questao = questao;
		this.turma = turma;
	}

	public Long getIdProva() {
		return idProva;
	}

	public void setIdProva(Long idProva) {
		this.idProva = idProva;
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

	public int getTentativas() {
		return tentativas;
	}

	public void setTentativas(int tentativas) {
		this.tentativas = tentativas;
	}

	public List<Arquivo> getArquivo() {
		return arquivo;
	}

	public void setArquivo(List<Arquivo> arquivo) {
		this.arquivo = arquivo;
	}

	public List<AdministradorProva> getAdministradorProva() {
		return administradorProva;
	}

	public void setAdministradorProva(List<AdministradorProva> administradorProva) {
		this.administradorProva = administradorProva;
	}

	public List<ProfessorProva> getProfessorProva() {
		return professorProva;
	}

	public void setProfessorProva(List<ProfessorProva> professorProva) {
		this.professorProva = professorProva;
	}

	public List<AlunoProva> getAlunoProva() {
		return alunoProva;
	}

	public void setAlunoProva(List<AlunoProva> alunoProva) {
		this.alunoProva = alunoProva;
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
		return "Prova{" +
				"idProva=" + idProva +
				", nome='" + nome + '\'' +
				", prazo=" + prazo +
				", descricao='" + descricao + '\'' +
				", inicio=" + inicio +
				", tempo=" + tempo +
				", tentativas=" + tentativas +
				", arquivo=" + arquivo +
				", administradorProva=" + administradorProva +
				", professorProva=" + professorProva +
				", alunoProva=" + alunoProva +
				", questao=" + questao +
				", turma=" + turma +
				'}';
	}
}
