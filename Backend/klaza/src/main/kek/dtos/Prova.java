package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Prova {

	private long idProva;
	private String nome;
	private LocalDateTime prazo;
	private String descricao;
	private LocalDateTime inicio;
	private long tempo;
	private int tentativas;
	private List<Arquivo> arquivo;
	private List<Administrador> administrador;
	private List<Professor> professor;
	private List<Questao> questao;
	private List<Resposta> resposta;
	private Materia materia;
	private long idTurma;

	public Prova() {
		this.idProva = 0;
		this.nome = "";
		this.prazo = null;
		this.descricao = "";
		this.inicio = null;
		this.tempo = 0;
		this.tentativas = 0;
		this.arquivo = new ArrayList<Arquivo>();
		this.administrador = new ArrayList<Administrador>();
		this.professor = new ArrayList<Professor>();
		this.questao = new ArrayList<Questao>();
		this.resposta = new ArrayList<Resposta>();
		this.materia = new Materia();
		this.idTurma = 0;
	}

	public Prova(long idProva, String nome, LocalDateTime prazo, String descricao, LocalDateTime inicio, long tempo, int tentativas, List<Arquivo> arquivo,
				 List<Administrador> administrador, List<Professor> professor, List<Questao> questao, List<Resposta> resposta, Materia materia, long idTurma) {
		this.idProva = idProva;
		this.nome = nome;
		this.prazo = prazo;
		this.descricao = descricao;
		this.inicio = inicio;
		this.tempo = tempo;
		this.tentativas = tentativas;
		this.arquivo = arquivo;
		this.administrador = administrador;
		this.professor = professor;
		this.questao = questao;
		this.resposta = resposta;
		this.materia = materia;
		this.idTurma = idTurma;
	}

	public long getIdProva() {
		return idProva;
	}

	public void setIdProva(long idProva) {
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

	public long getTempo() {
		return tempo;
	}

	public void setTempo(long tempo) {
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

	public List<Administrador> getAdministrador() {
		return administrador;
	}

	public void setAdministrador(List<Administrador> administrador) {
		this.administrador = administrador;
	}

	public List<Professor> getProfessor() {
		return professor;
	}

	public void setProfessor(List<Professor> professor) {
		this.professor = professor;
	}

	public List<Questao> getQuestao() {
		return questao;
	}

	public void setQuestao(List<Questao> questao) {
		this.questao = questao;
	}

	public List<Resposta> getResposta() {
		return resposta;
	}

	public void setResposta(List<Resposta> resposta) {
		this.resposta = resposta;
	}

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	public long getIdTurma() {
		return idTurma;
	}

	public void setIdTurma(long idTurma) {
		this.idTurma = idTurma;
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
				", administrador=" + administrador +
				", professor=" + professor +
				", questao=" + questao +
				", resposta=" + resposta +
				", materia=" + materia +
				", idTurma=" + idTurma +
				'}';
	}
}
