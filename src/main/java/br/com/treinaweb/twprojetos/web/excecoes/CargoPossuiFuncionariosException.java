package br.com.treinaweb.twprojetos.web.excecoes;

public class CargoPossuiFuncionariosException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CargoPossuiFuncionariosException(Long id) {
		super(String.format("Cargo com ID %s possui funcionario(s) relacionado(s)", id));
	}
	
	

}
