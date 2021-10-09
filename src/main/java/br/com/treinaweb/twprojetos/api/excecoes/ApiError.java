package br.com.treinaweb.twprojetos.api.excecoes;

import java.time.LocalDateTime;

/**
 * classe que representa as informacoes a serem exbidas 
 * quando ocorrer algum tipo de excecao na aplicacao
 * 
 * @author dsouzarogerio
 *
 */
public class ApiError {
	
	private int erro;
	private String status;
	private LocalDateTime timestamp;
	private String mensagem;
	
	public ApiError() {
		super();
	}

	public ApiError(int erro, String status, LocalDateTime timestamp, String mensagem) {
		super();
		this.erro = erro;
		this.status = status;
		this.timestamp = timestamp;
		this.mensagem = mensagem;
	}

	public int getErro() {
		return erro;
	}

	public void setErro(int erro) {
		this.erro = erro;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
}
