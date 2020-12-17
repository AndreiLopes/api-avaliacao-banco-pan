package br.com.bancoPan.model;

/**
 * 
 * Classe que representa o enum Tipo Telefone.
 * 
 * @author andrei
 */
public enum TIPO_TELEFONE {

	RESIDENCIAL("Residencial"), COMERCIAL("Comercial"), RECADO("Recado"), CELULAR("Celular");

	private String tipo;

	TIPO_TELEFONE(String tipo) {
		this.setTipo(tipo);
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
