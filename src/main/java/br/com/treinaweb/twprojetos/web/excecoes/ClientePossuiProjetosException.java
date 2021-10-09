package br.com.treinaweb.twprojetos.web.excecoes;

public class ClientePossuiProjetosException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ClientePossuiProjetosException(Long id) {
		super(String.format("Cliente com ID %s possui projetos relacionados", id));
	}
	
	

}
