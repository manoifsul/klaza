package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AlunoProva {

	private long idAlunoProva;
	private long tempo;
	private LocalDateTime inicio;
	private LocalDateTime finalizada;
	private List<Resposta> resposta;
	private Aluno aluno;
	private Prova prova;
	
	public AlunoProva() {
		this.idAlunoProva = 0;
		this.tempo = 0;
		this.inicio = LocalDateTime.now();
		this.finalizada = null;
		this.resposta = new ArrayList<Resposta>();
		this.aluno = new Aluno();
		this.prova = new Prova();
	}

	public AlunoProva(long idAlunoProva, long tempo, LocalDateTime inicio, LocalDateTime finalizada,
			List<Resposta> resposta, Aluno aluno, Prova prova) {
		this.idAlunoProva = idAlunoProva;
		this.tempo = tempo;
		this.inicio = inicio;
		this.finalizada = finalizada;
		this.resposta = resposta;
		this.aluno = aluno;
		this.prova = prova;
	}

	public long getIdAlunoProva() {
		return idAlunoProva;
	}

	public void setIdAlunoProva(long idAlunoProva) {
		this.idAlunoProva = idAlunoProva;
	}

	public long getTempo() {
		return tempo;
	}

	public void setTempo(long tempo) {
		this.tempo = tempo;
	}

	public LocalDateTime getInicio() {
		return inicio;
	}

	public void setInicio(LocalDateTime inicio) {
		this.inicio = inicio;
	}

	public LocalDateTime getFinalizada() {
		return finalizada;
	}

	public void setFinalizada(LocalDateTime finalizada) {
		this.finalizada = finalizada;
	}

	public List<Resposta> getResposta() {
		return resposta;
	}

	public void setResposta(List<Resposta> resposta) {
		this.resposta = resposta;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Prova getProva() {
		return prova;
	}

	public void setProva(Prova prova) {
		this.prova = prova;
	}

	@Override
	public String toString() {
		return "AlunoProva [idAlunoProva=" + idAlunoProva + ", tempo=" + tempo + ", inicio=" + inicio + ", finalizada="
				+ finalizada + ", resposta=" + resposta + ", aluno=" + aluno + ", prova=" + prova + "]";
	}
}
