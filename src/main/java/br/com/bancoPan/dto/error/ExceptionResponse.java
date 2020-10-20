package br.com.bancoPan.dto.error;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Classe de serializacao de {@link ExceptionResponse}
 * 
 * @author andrei-lopes - 2020-10-19
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class ExceptionResponse {

	@JsonProperty("code")
	private Integer status;

	@JsonProperty("message")
	private String message;

	public ExceptionResponse() {
	}

	public ExceptionResponse(Integer status, String message) {
		this.status = status;
		this.message = message;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}