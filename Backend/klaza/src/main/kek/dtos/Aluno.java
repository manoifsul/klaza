package entities;

import java.util.ArrayList;
import java.util.List;

public class Aluno {

	private long idAluno;
	private String nome;
	private String matricula;
	private String senha;
	private String email;
	private List<NotaTrabalho> notaTrabalho;
	private List<NotaProva> notaProva;
	private List<Long> idTurmas;
	
	public Aluno() {
		this.idAluno = 0;
		this.nome = "";
		this.matricula = "";
		this.senha = "";
		this.email = "";
		this.notaTrabalho = new ArrayList<NotaTrabalho>();
		this.notaProva = new ArrayList<NotaProva>();
		this.idTurmas = new ArrayList<Long>();
	}

	public Aluno(long idAluno, String nome, String matricula, String senha, String email, List<NotaTrabalho> notaTrabalho, List<NotaProva> notaProva, List<Long> idTurmas) {
		this.idAluno = idAluno;
		this.nome = nome;
		this.matricula = matricula;
		this.senha = senha;
		this.email = email;
		this.notaTrabalho = notaTrabalho;
		this.notaProva = notaProva;
		this.idTurmas = idTurmas;
	}

	public long getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(long idAluno) {
		this.idAluno = idAluno;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<NotaTrabalho> getNotaTrabalho() {
		return notaTrabalho;
	}

	public void setNotaTrabalho(List<NotaTrabalho> notaTrabalho) {
		this.notaTrabalho = notaTrabalho;
	}

	public List<NotaProva> getNotaProva() {
		return notaProva;
	}

	public void setNotaProva(List<NotaProva> notaProva) {
		this.notaProva = notaProva;
	}

	public List<Long> getIdTurmas() {
		return idTurmas;
	}

	public void setIdTurmas(List<Long> idTurmas) {
		this.idTurmas = idTurmas;
	}

	@Override
	public String toString() {
		return "Aluno{" +
				"idAluno=" + idAluno +
				", nome='" + nome + '\'' +
				", matricula='" + matricula + '\'' +
				", senha='" + senha + '\'' +
				", email='" + email + '\'' +
				", notaTrabalho=" + notaTrabalho +
				", notaProva=" + notaProva +
				", idTurmas=" + idTurmas +
				'}';
	}
}
