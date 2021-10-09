package br.com.treinaweb.twprojetos.web.excecoes;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class FuncionarioNaoEncontradoException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public FuncionarioNaoEncontradoException(Long id) {
		super(String.format("Funcionario com ID %s não encontrado.", id));
	}
	
	

}
