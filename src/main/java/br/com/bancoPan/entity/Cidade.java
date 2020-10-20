package br.com.bancoPan.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class Cidade {

	@JsonProperty("codigo")
	private int codigo;

	@JsonProperty("uf")
	private String uf;

	@JsonProperty("nome")
	private String nome;

	public Cidade() {
		super();
	}

	public Cidade(int codigo, String uf, String nome) {
		super();
		this.codigo = codigo;
		this.uf = uf;
		this.nome = nome;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}