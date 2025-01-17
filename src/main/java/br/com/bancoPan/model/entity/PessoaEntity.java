package br.com.bancoPan.model.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Classe que representa a entidade Pessoa.
 * 
 * @author andrei
 */
@Entity
@Table(name = "Pessoa")
public class PessoaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idPessoa;

	private String nomePessoa;
	private String cpfPessoa;
	private String filiacaoPai;
	private String filiacaoMae;

	@ManyToMany
	@JoinTable(name = "PessoasHasTelefones")
	private List<TelefoneEntity> telefones;

	@ManyToMany
	@JoinTable(name = "PessoasHasEnderecos")
	private List<EnderecoEntity> enderecos;

	public Long getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Long idPessoa) {
		this.idPessoa = idPessoa;
	}

	public String getNomePessoa() {
		return nomePessoa;
	}

	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}

	public String getCpfPessoa() {
		return cpfPessoa;
	}

	public void setCpfPessoa(String cpfPessoa) {
		this.cpfPessoa = cpfPessoa;
	}

	public String getFiliacaoPai() {
		return filiacaoPai;
	}

	public void setFiliacaoPai(String filiacaoPai) {
		this.filiacaoPai = filiacaoPai;
	}

	public String getFiliacaoMae() {
		return filiacaoMae;
	}

	public void setFiliacaoMae(String filiacaoMae) {
		this.filiacaoMae = filiacaoMae;
	}

	public List<TelefoneEntity> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<TelefoneEntity> telefones) {
		this.telefones = telefones;
	}

	public List<EnderecoEntity> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<EnderecoEntity> enderecos) {
		this.enderecos = enderecos;
	}
}
