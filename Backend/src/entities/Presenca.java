package entities;

public class Presenca {

	private Long idPresenca;
	private int valor;
	
	public Presenca() {
		this.idPresenca = (long) 0;
		this.valor = 0;
	}

	public Presenca(Long idPresenca, int valor) {
		this.idPresenca = idPresenca;
		this.valor = valor;
	}

	public Long getIdPresenca() {
		return idPresenca;
	}

	public void setIdPresenca(Long idPresenca) {
		this.idPresenca = idPresenca;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "Presenca [idPresenca=" + idPresenca + ", valor=" + valor + "]";
	}
}
