package br.com.bancoPan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bancoPan.dto.ObjectData;
import br.com.bancoPan.entity.Cliente;
import br.com.bancoPan.exception.CustomException;
import br.com.bancoPan.service.ClienteService;

/**
 * Classe para orquestrar requisições de Clientes.
 * 
 * @author andrei-lopes - 2020-10-21
 */
@RestController
@RequestMapping(value = "/v1")
public class ClienteController {

	private static final String ERROR = "Erro: ";

	@Autowired
	ClienteService service;

	/**
	 * Metodo que Salva Cliente
	 * 
	 * @param cliente
	 * @return
	 */
	@PostMapping("/cliente")
	public ResponseEntity<?> cadastraCliente(@RequestBody Cliente cliente) {
		try {
			ObjectData data = new ObjectData();
			data.setData(service.cadastroCliente(cliente));

			return ResponseEntity.ok(data);
		} catch (Exception e) {
			return extracted(e);
		}
	}

	/**
	 * Metodo que obtem todos os clientes cadastrados
	 * 
	 * @return
	 */
	@GetMapping("/clientes")
	public ResponseEntity<?> obtemTodosClientes() {

		try {
			ObjectData data = new ObjectData();
			data.setData(service.obterClientes());

			return ResponseEntity.ok(data);
		} catch (Exception e) {
			return extracted(e);
		}
	}

	/**
	 * Metodo que retorna cliente pelo CPF
	 * 
	 * @param cpf
	 * @return Cliente
	 */
	@GetMapping("/cliente/consulta/{cpf}")
	public ResponseEntity<?> obtemClientePeloCpf(@PathVariable(value = "cpf") String cpf) {
		try {
			ObjectData data = new ObjectData();
			data.setData(service.obterClienteCpf(cpf));

			return ResponseEntity.ok(data);
		} catch (Exception e) {
			return extracted(e);
		}
	}

	/**
	 * Metodo que retorna cliente pelo ID.
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/cliente/{id}")
	public ResponseEntity<?> obtemClientePeloId(@PathVariable(value = "id") long id) {

		try {
			ObjectData data = new ObjectData();
			data.setData(service.obterClienteID(id));

			return ResponseEntity.ok(data);
		} catch (Exception e) {
			return extracted(e);
		}

	}

	private ResponseEntity<?> extracted(Exception e) {
		CustomException custom = new CustomException(ERROR + e.getMessage());
		return custom.responseException(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
