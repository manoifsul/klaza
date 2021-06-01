package entities;

import java.util.ArrayList;
import java.util.List;

public class Professor {
	
	private Long idProfessor;
	private String nome;
	private String matricula;
	private String senha;
	private String email;
	private List<TurmaProfessor> turmaProfessor;
	private List<ProfessorMateria> professorMateria;
	private List<ProfessorAtividade> professorAtividade;
	private List<ProfessorTrabalho> professorTrabalho;
	private List<ProfessorProva> professorProva;
	
	public Professor() {
		this.idProfessor = (long) 0;
		this.nome = "";
		this.matricula = "";
		this.senha = "";
		this.email = "";
		this.turmaProfessor = new ArrayList<TurmaProfessor>();
		this.professorMateria = new ArrayList<ProfessorMateria>();
		this.professorAtividade = new ArrayList<ProfessorAtividade>();
		this.professorTrabalho = new ArrayList<ProfessorTrabalho>();
		this.professorProva = new ArrayList<ProfessorProva>();
	}

	public Professor(Long idProfessor, String nome, String matricula, String senha, String email,
			List<TurmaProfessor> turmaProfessor, List<ProfessorMateria> professorMateria,
			List<ProfessorAtividade> professorAtividade, List<ProfessorTrabalho> professorTrabalho,
			List<ProfessorProva> professorProva) {
		this.idProfessor = idProfessor;
		this.nome = nome;
		this.matricula = matricula;
		this.senha = senha;
		this.email = email;
		this.turmaProfessor = turmaProfessor;
		this.professorMateria = professorMateria;
		this.professorAtividade = professorAtividade;
		this.professorTrabalho = professorTrabalho;
		this.professorProva = professorProva;
	}

	public Long getIdProfessor() {
		return idProfessor;
	}

	public void setIdProfessor(Long idProfessor) {
		this.idProfessor = idProfessor;
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

	public List<TurmaProfessor> getTurmaProfessor() {
		return turmaProfessor;
	}

	public void setTurmaProfessor(List<TurmaProfessor> turmaProfessor) {
		this.turmaProfessor = turmaProfessor;
	}

	public List<ProfessorMateria> getProfessorMateria() {
		return professorMateria;
	}

	public void setProfessorMateria(List<ProfessorMateria> professorMateria) {
		this.professorMateria = professorMateria;
	}

	public List<ProfessorAtividade> getProfessorAtividade() {
		return professorAtividade;
	}

	public void setProfessorAtividade(List<ProfessorAtividade> professorAtividade) {
		this.professorAtividade = professorAtividade;
	}

	public List<ProfessorTrabalho> getProfessorTrabalho() {
		return professorTrabalho;
	}

	public void setProfessorTrabalho(List<ProfessorTrabalho> professorTrabalho) {
		this.professorTrabalho = professorTrabalho;
	}

	public List<ProfessorProva> getProfessorProva() {
		return professorProva;
	}

	public void setProfessorProva(List<ProfessorProva> professorProva) {
		this.professorProva = professorProva;
	}

	public boolean autenticaSenha(String senha) {
		return this.senha == senha;
	}
	
	@Override
	public String toString() {
		return "Professor [idProfessor=" + idProfessor + ", nome=" + nome + ", matricula=" + matricula + ", senha="
				+ senha + ", email=" + email + ", turmaProfessor=" + turmaProfessor + ", professorMateria="
				+ professorMateria + ", professorAtividade=" + professorAtividade + ", professorTrabalho="
				+ professorTrabalho + ", professorProva=" + professorProva + "]";
	}
}
