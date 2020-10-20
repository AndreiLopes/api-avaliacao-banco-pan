package br.com.bancoPan.exception;

import org.springframework.http.HttpStatus;

/**
 * Classe de tratamento de excecao {@link ClienteException}
 * 
 * @author andrei-lopes - 2020-10-19
 */
public class ClienteException extends Throwable {

	private static final long serialVersionUID = 1L;

	private HttpStatus httpStatus;

	public ClienteException(String error) {
		super(error);
	}

	public ClienteException(Throwable error) {
		super(error);
	}

	public ClienteException(String error, HttpStatus httpStatus) {
		super(error);
		this.httpStatus = httpStatus;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

}
