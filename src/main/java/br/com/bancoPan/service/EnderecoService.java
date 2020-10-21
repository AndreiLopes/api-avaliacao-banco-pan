package br.com.bancoPan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bancoPan.entity.Endereco;
import br.com.bancoPan.repository.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository repository;

	public Endereco atualizaEndereco(Endereco endereco) {

		return repository.updateEndereco(endereco);
	}

}