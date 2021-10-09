package br.com.treinaweb.twprojetos.web.excecoes;

public class FuncionarioEhLiderDeProjetoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FuncionarioEhLiderDeProjetoException(Long id) {
		super(String.format("Funcionario com ID %s é lider de projeto(s).", id));
	}
	
	

}
