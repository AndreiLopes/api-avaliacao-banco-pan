package br.com.bancoPan.service;

import java.util.List;

import br.com.bancoPan.model.Endereco;
import br.com.bancoPan.model.Pessoa;

/**
 * Interface de Pessoa
 * 
 * @author andrei
 *
 */
public interface PessoaService {

	Pessoa consultarPessoa(String cpf);

	List<Endereco> consultarEnderecoPessoa(String cpf, String cep);

	Endereco alterarEnderecoPessoa(String cpf, Long enderecoId, Endereco enderecoAlterado);
}
