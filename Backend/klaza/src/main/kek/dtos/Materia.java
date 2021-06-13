package entities;

import java.util.ArrayList;
import java.util.List;

public class Materia {

	private long idMateria;
	private String nome;
	private List<Long> idProfessor;
	
	public Materia() {
		this.idMateria = 0;
		this.nome = "";
		this.idProfessor = new ArrayList<Long>();
	}

	public Materia(long idMateria, String nome, List<Long> idProfessor) {
		this.idMateria = idMateria;
		this.nome = nome;
		this.idProfessor = idProfessor;
	}

	public long getIdMateria() {
		return idMateria;
	}

	public void setIdMateria(long idMateria) {
		this.idMateria = idMateria;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Long> getIdProfessor() {
		return idProfessor;
	}

	public void setIdProfessor(List<Long> idProfessor) {
		this.idProfessor = idProfessor;
	}

	@Override
	public String toString() {
		return "Materia{" +
				"idMateria=" + idMateria +
				", nome='" + nome + '\'' +
				", idProfessor=" + idProfessor +
				'}';
	}
}
