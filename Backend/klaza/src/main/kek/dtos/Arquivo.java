package entities;

public class Arquivo {

	private long idArquivo;
	private String link;
	
	public Arquivo() {
		this.idArquivo = (long) 0;
		this.link = "";
	}

	public Arquivo(long idArquivo, String link) {
		this.idArquivo = idArquivo;
		this.link = link;
	}

	public long getIdArquivo() {
		return idArquivo;
	}

	public void setIdArquivo(long idArquivo) {
		this.idArquivo = idArquivo;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@Override
	public String toString() {
		return "Arquivo [idArquivo=" + idArquivo + ", link=" + link + "]";
	}
}
