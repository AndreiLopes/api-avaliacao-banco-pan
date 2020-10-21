package br.com.bancoPan.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class Estado {

	@JsonProperty("codigo")
	private int codigo;

	@JsonProperty("nome")
	private String nome;

	@JsonProperty("sigla")
	private String sigla;

	public Estado() {
		super();
	}

	public Estado(int codigo, String nome, String sigla) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.sigla = sigla;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public List<Estado> classificarEstados(List<Estado> estados) {

		List<Estado> ordenadoSPeRJ = new ArrayList<Estado>();
		List<Estado> ordenadoOutros = new ArrayList<Estado>();

		for (int i = 0; i < estados.size(); i++) {

			Estado estado = estados.get(i);
			boolean estadoSP = estado.getSigla().equalsIgnoreCase("SP");
			boolean estadpRJ = estado.getSigla().equalsIgnoreCase("RJ");

			if (estadoSP || estadpRJ) {
				ordenadoSPeRJ.add(estado);

				if (ordenadoSPeRJ.size() == 2) {
				}
			} else {
				ordenadoOutros.add(estado);
			}
		}
		Collections.sort(ordenadoSPeRJ, Comparator.comparing(Estado::getSigla).reversed());
		Collections.sort(ordenadoOutros, Comparator.comparing(Estado::getSigla));

		ordenadoSPeRJ.addAll(ordenadoOutros);
		return ordenadoSPeRJ;
	}
}