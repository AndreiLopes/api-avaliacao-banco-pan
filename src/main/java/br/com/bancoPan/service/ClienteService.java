package br.com.bancoPan.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bancoPan.entity.Cliente;
import br.com.bancoPan.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	public List<Cliente> buscaTodosClientes() {
		return repository.findAll();
	}

	public Optional<Cliente> buscaClientePeloId(String id) {
		return repository.findById(id);
	}

	public Cliente buscaClientePeloCpf(String cpf) {
		return repository.findByCpf(cpf);
	}

	public Cliente salvarCliente(Cliente cliente) {
		return repository.save(cliente);
	}

}
