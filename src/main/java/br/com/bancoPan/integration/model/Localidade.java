package br.com.bancoPan.integration.model;

/**
 * Classe que representa o model Localidade na Integracao.
 * 
 * @author andrei
 *
 */
public class Localidade {

	private Integer id;
	private String sigla;
	private String nome;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSigla() {
		return this.sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
