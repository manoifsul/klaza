package entities;

import java.util.ArrayList;
import java.util.List;

public class Materia {

	private Long idMateria;
	private String nome;
	private List<ProfessorMateria> professorMateria;
	private List<Turma> turma;
	
	public Materia() {
		this.idMateria = (long) 0;
		this.nome = "";
		this.professorMateria = new ArrayList<ProfessorMateria>();
		this.turma = new ArrayList<Turma>();
	}

	public Materia(Long idMateria, String nome, List<ProfessorMateria> professorMateria, List<Turma> turma) {
		this.idMateria = idMateria;
		this.nome = nome;
		this.professorMateria = professorMateria;
		this.turma = turma;
	}

	public Long getIdMateria() {
		return idMateria;
	}

	public void setIdMateria(Long idMateria) {
		this.idMateria = idMateria;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<ProfessorMateria> getProfessorMateria() {
		return professorMateria;
	}

	public void setProfessorMateria(List<ProfessorMateria> professorMateria) {
		this.professorMateria = professorMateria;
	}

	public List<Turma> getTurma() {
		return turma;
	}

	public void setTurma(List<Turma> turma) {
		this.turma = turma;
	}

	@Override
	public String toString() {
		return "Materia [idMateria=" + idMateria + ", nome=" + nome + ", professorMateria=" + professorMateria
				+ ", turma=" + turma + "]";
	}
}
