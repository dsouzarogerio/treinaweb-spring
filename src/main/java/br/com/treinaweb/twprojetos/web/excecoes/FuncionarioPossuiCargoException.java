package br.com.treinaweb.twprojetos.web.excecoes;

public class FuncionarioPossuiCargoException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public FuncionarioPossuiCargoException(Long id) {
		super(String.format("Funcionario com ID %s possui cargo relacionado", id));
	}

	
}
