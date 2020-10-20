package br.com.bancoPan.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", updatable = false, nullable = false)
	@JsonProperty("id_cliente")
	private String idCliente;

	@NotBlank
	@JsonProperty("nome")
	private String nome;

	@NotNull
	@JsonProperty("cpf")
	private String cpf;

	@NotBlank
	@JsonProperty("data_nascimento")
	private String dataNascimento;

	@NotBlank
	@JsonProperty("sexo")
	private String sexo;

	@JsonProperty("telefone_residencial")
	private String telefoneResidencial;

	@NotNull
	@JsonProperty("telefone_celular")
	private String telefoneCelular;

	@NotBlank
	@JsonProperty("email")
	private String email;

	@NotBlank
	@JsonProperty("legradouro")
	private String legradouro;

	@NotNull
	@JsonProperty("numero_casa")
	private String numeroCasa;

	@JsonProperty("complemento")
	private String complemento;

	@NotNull
	@JsonProperty("cep")
	private String cep;

	@NotBlank
	@JsonProperty("cidade")
	private String cidade;

	@NotBlank
	@JsonProperty("estado")
	private String estado;

	@NotBlank
	@JsonProperty("pais")
	private String pais;

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getTelefoneResidencial() {
		return telefoneResidencial;
	}

	public void setTelefoneResidencial(String telefoneResidencial) {
		this.telefoneResidencial = telefoneResidencial;
	}

	public String getTelefoneCelular() {
		return telefoneCelular;
	}

	public void setTelefoneCelular(String telefoneCelular) {
		this.telefoneCelular = telefoneCelular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLegradouro() {
		return legradouro;
	}

	public void setLegradouro(String legradouro) {
		this.legradouro = legradouro;
	}

	public String getNumeroCasa() {
		return numeroCasa;
	}

	public void setNumeroCasa(String numeroCasa) {
		this.numeroCasa = numeroCasa;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}