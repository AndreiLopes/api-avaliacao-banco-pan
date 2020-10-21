package br.com.bancoPan.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Estado {

	private int id;
	private String sigla;
	private String nome;

	public Estado() {
		super();
	}

	public Estado(int id, String sigla, String nome) {
		super();
		this.id = id;
		this.sigla = sigla;
		this.nome = nome;
	}

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Estado> classificaEstados(List<Estado> estados) {

		List<Estado> estadosSPeRJ = new ArrayList<Estado>();
		List<Estado> estadosOutros = new ArrayList<Estado>();

		for (int i = 0; i < estados.size(); i++) {
			Estado estado = estados.get(i);

			boolean sPeRJ = estado.getSigla().equals("SP");
			boolean outros = estado.getSigla().equals("RJ");

			if (sPeRJ || outros) {
				estadosSPeRJ.add(estado);

				if (estadosSPeRJ.size() == 2) {
				}
			} else {
				estadosOutros.add(estado);
			}
		}

		Collections.sort(estadosOutros, Comparator.comparing(Estado::getSigla));
		Collections.sort(estadosSPeRJ, Comparator.comparing(Estado::getSigla).reversed());

		estadosSPeRJ.addAll(estadosOutros);
		return estadosSPeRJ;
	}
}
