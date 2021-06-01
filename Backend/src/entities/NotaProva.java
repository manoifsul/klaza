package entities;

public class NotaProva {

	private Long idNotaProva;
	private float valor;
	private Prova prova;
	private Aluno aluno;
	
	public NotaProva() {
		this.idNotaProva = (long) 0;
		this.valor = 0;
		this.prova = new Prova();
		this.aluno = new Aluno();
	}

	public NotaProva(Long idNotaProva, float valor, Prova prova, Aluno aluno) {
		this.idNotaProva = idNotaProva;
		this.valor = valor;
		this.prova = prova;
		this.aluno = aluno;
	}

	public Long getIdNotaProva() {
		return idNotaProva;
	}

	public void setIdNotaProva(Long idNotaProva) {
		this.idNotaProva = idNotaProva;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public Prova getProva() {
		return prova;
	}

	public void setProva(Prova prova) {
		this.prova = prova;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	@Override
	public String toString() {
		return "NotaProva [idNotaProva=" + idNotaProva + ", valor=" + valor + ", prova=" + prova + ", aluno=" + aluno
				+ "]";
	}
}
