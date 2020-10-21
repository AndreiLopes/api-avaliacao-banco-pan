package br.com.bancoPan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.bancoPan.entity.Cliente;
import br.com.bancoPan.entity.Endereco;
import br.com.bancoPan.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository repository;

	@Autowired
	EnderecoService enderecoService;

	public List<Cliente> obterClientes() {
		return repository.findAll();
	}

	public Cliente cadastroCliente(Cliente cliente) {

		Endereco endereco = enderecoService.atualizaEndereco(cliente.getEndereco());
		cliente.setEndereco(endereco);

		return repository.saveAndFlush(cliente);
	}

	public Cliente obterClienteID(long id) {

		return repository.findById(id);
	}

	public ResponseEntity<?> obterClienteCpf(String cpf) {

		Cliente cliente = repository.findByCpf(cpf);

		if (!cliente.getNome().isEmpty()) {
			return ResponseEntity.ok(cliente);

		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
