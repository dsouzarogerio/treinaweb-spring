package br.com.treinaweb.twprojetos.api.excecoes;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Classe para conter uma lista de erros nos campos das entidades
 * 
 * @author dsouzarogerio
 *
 */
public class ValidacaoApiError extends ApiError{
	
	private List<CampoError> erros;

	public ValidacaoApiError() {	}

	public ValidacaoApiError(int erro, String status, LocalDateTime timestamp, String mensagem,
			List<CampoError> erros) {
		super(erro, status, timestamp, mensagem);
		this.erros = erros;
	}

	public List<CampoError> getErros() {
		return erros;
	}

	public void setErros(List<CampoError> erros) {
		this.erros = erros;
	}

}
