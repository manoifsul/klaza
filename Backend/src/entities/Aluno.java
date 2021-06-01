package entities;

import java.util.ArrayList;
import java.util.List;

public class Aluno {

	private long idAluno;
	private String nome;
	private String matricula;
	private String senha;
	private String email;
	private List<TurmaAluno> turmaAluno;
	private List<NotaTrabalho> notasTrabalho;
	private List<NotaProva> notasProva;
	
	public Aluno() {
		this.idAluno = 0;
		this.nome = "";
		this.matricula = "";
		this.senha = "";
		this.email = "";
		this.turmaAluno = new ArrayList<TurmaAluno>();
		this.notasTrabalho = new ArrayList<NotaTrabalho>();
		this.notasProva = new ArrayList<NotaProva>();
	}

	public Aluno(long idAluno, String nome, String matricula, String senha, String email, List<TurmaAluno> turmaAluno, List<NotaTrabalho> notasTrabalho, List<NotaProva> notasProva) {
		this.idAluno = idAluno;
		this.nome = nome;
		this.matricula = matricula;
		this.senha = senha;
		this.email = email;
		this.turmaAluno = turmaAluno;
		this.notasTrabalho = notasTrabalho;
		this.notasProva = notasProva;
	}

	public long getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(Long idAluno) {
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

	public List<TurmaAluno> getTurmaAluno() {
		return turmaAluno;
	}

	public void setTurmaAluno(List<TurmaAluno> turmaAluno) {
		this.turmaAluno = turmaAluno;
	}

	public List<NotaTrabalho> getNotasTrabalho() {
		return notasTrabalho;
	}

	public void setNotasTrabalho(List<NotaTrabalho> notasTrabalho) {
		this.notasTrabalho = notasTrabalho;
	}

	public List<NotaProva> getNotasProva() {
		return notasProva;
	}

	public void setNotasProva(List<NotaProva> notasProva) {
		this.notasProva = notasProva;
	}

	@Override
	public String toString() {
		return "Aluno{" +
				"idAluno=" + idAluno +
				", nome='" + nome + '\'' +
				", matricula='" + matricula + '\'' +
				", senha='" + senha + '\'' +
				", email='" + email + '\'' +
				", turmaAluno=" + turmaAluno +
				", notasTrabalho=" + notasTrabalho +
				", notasProva=" + notasProva +
				'}';
	}
}
