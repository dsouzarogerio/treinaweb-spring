package br.com.treinaweb.twprojetos.api.excecoes;

/**
 * Classe que validada o campo(atributo da entidade) com erro 
 * e a mensagem de informacao de erro
 * 
 * @author dsouzarogerio
 *
 */
public class CampoError {
	private String campo;
	private String mensagem;
	
	public CampoError() {	}

	public CampoError(String campo, String mensagem) {
		super();
		this.campo = campo;
		this.mensagem = mensagem;
	}

	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
}
