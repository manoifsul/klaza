package entities;

public class NotaTrabalho {

	private Long idNotaTrabalho;
	private float valor;
	private Trabalho trabalho;
	private Aluno aluno;
	
	public NotaTrabalho() {
		this.idNotaTrabalho = (long) 0;
		this.valor = 0;
		this.trabalho = new Trabalho();
		this.aluno = new Aluno();
	}

	public NotaTrabalho(Long idNotaTrabalho, float valor, Trabalho trabalho, Aluno aluno) {
		this.idNotaTrabalho = idNotaTrabalho;
		this.valor = valor;
		this.trabalho = trabalho;
		this.aluno = aluno;
	}

	public Long getIdNotaTrabalho() {
		return idNotaTrabalho;
	}

	public void setIdNotaTrabalho(Long idNotaTrabalho) {
		this.idNotaTrabalho = idNotaTrabalho;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public Trabalho getTrabalho() {
		return trabalho;
	}

	public void setTrabalho(Trabalho trabalho) {
		this.trabalho = trabalho;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	@Override
	public String toString() {
		return "NotaTrabalho [idNotaTrabalho=" + idNotaTrabalho + ", valor=" + valor + ", trabalho=" + trabalho
				+ ", aluno=" + aluno + "]";
	}

	
}
