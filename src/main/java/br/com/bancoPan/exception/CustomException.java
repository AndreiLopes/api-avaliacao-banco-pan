package br.com.bancoPan.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

/**
 * Classe de tratamento de excecao {@link CustomException}
 * 
 * @author andrei-lopes - 2020-02-22
 */
public class CustomException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private HttpStatus errorStatus;
	private String errorMessage;
	private List<String> errorDetails = new ArrayList<String>();

	public CustomException(HttpStatus errorStatusParam, String errorMessageParam, List<String> errorDetailsParam) {
		this.errorStatus = errorStatusParam;
		this.errorMessage = errorMessageParam;
		this.errorDetails.addAll(errorDetailsParam);
	}

	/**
	 * Gets the error message.
	 *
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * Gets the error details.
	 *
	 * @return the errorDetails
	 */
	public List<String> getErrorDetails() {
		return errorDetails;
	}

	/**
	 * Gets the error status.
	 *
	 * @return the errorStatus
	 */
	public HttpStatus getErrorStatus() {
		return errorStatus;
	}

}