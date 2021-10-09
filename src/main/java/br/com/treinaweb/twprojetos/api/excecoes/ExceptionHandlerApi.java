package br.com.treinaweb.twprojetos.api.excecoes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Classe gerenciadora de excecoes da aplicacao
 * 
 * @author dsouzarogerio
 *
 */

@ControllerAdvice(annotations = RestController.class)
public class ExceptionHandlerApi extends ResponseEntityExceptionHandler {
	
	//metodo para entidade nao encontrada
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ApiError> handleEntityNotFoundException(EntityNotFoundException exception){
		HttpStatus httpStatus = HttpStatus.NOT_FOUND;
		
		//montado a tratativa de erro de entidade na ApiError
		ApiError apiError = new ApiError(
				httpStatus.value(),
				httpStatus.getReasonPhrase(),
				LocalDateTime.now(),
				exception.getLocalizedMessage()
				);
		
		return new ResponseEntity<>(apiError, httpStatus);
	}

	//metodo sobrescrito para tratar erros de validacao
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		//montando os campos de erro
		List<CampoError> erros = new ArrayList<>();
		exception.getBindingResult().getFieldErrors().forEach(fieldErro -> {
			CampoError campoError = new CampoError(
					fieldErro.getField(),
					fieldErro.getDefaultMessage()
					);
			erros.add(campoError);
			});
		
		//montando a tratativa de erro dos campos da entidade
		ValidacaoApiError validacaoApiError = new ValidacaoApiError(
				status.value(),
				status.getReasonPhrase(),
				LocalDateTime.now(),
				"Houveram erros de validação",
				erros
				);
		
		return new ResponseEntity<>(validacaoApiError, status);
		}
}
