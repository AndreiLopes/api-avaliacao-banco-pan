package br.com.bancoPan.controller;

import javax.validation.Valid;

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
import br.com.bancoPan.dto.error.ExceptionResponse;
import br.com.bancoPan.entity.Cliente;
import br.com.bancoPan.service.ClienteService;

/**
 * Classe de CRUD de Clientes - {@link CadastroCrienteController}
 * 
 * @author andrei-lopes - 2020-10-19
 */
@RestController
@RequestMapping("/v1")
public class CadastroCrienteController {

	private static final String UNEXPECTED_ERROR = "Erro inesperado: ";

	@Autowired
	private ClienteService service;

	@GetMapping("/clientes")
	public ResponseEntity<?> listaTodosCliente() {

		try {
			ObjectData data = new ObjectData();

			data.setData(service.buscaTodosClientes());

			return new ResponseEntity<>(data, HttpStatus.OK);

		} catch (Exception e) {
			return extracted(e);
		}
	}

	/**
	 * Metodo para retornar o cliente pelo ID.
	 * 
	 * @param cpf
	 * @return cliente
	 */
	@GetMapping("/cliente/{id_cliente}")
	public ResponseEntity<?> consultaClientePeloId(@PathVariable("id_cliente") String id) throws Exception {

		try {
			ObjectData data = new ObjectData();

			data.setData(service.buscaClientePeloId(id));

			return new ResponseEntity<>(data, HttpStatus.OK);

		} catch (Exception e) {
			return extracted(e);
		}
	}

	/**
	 * Metodo para retornar o cliente pelo CPF.
	 * 
	 * @param cpf
	 * @return cliente
	 */
	@GetMapping("/cliente/consulta/{cpf}")
	public ResponseEntity<?> consultaClientePeloCpf(@PathVariable("cpf") String cpf) throws Exception {

		try {
			ObjectData data = new ObjectData();
			Cliente cliente = service.buscaClientePeloCpf(cpf);

			if (!cliente.getNome().isEmpty()) {
				data.setData(cliente);
				return new ResponseEntity<>(data, HttpStatus.OK);

			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return extracted(e);
		}
	}

	/**
	 * Metodo para salvar um novo cliente.
	 * 
	 * @param cliente
	 * @return Novo Cliente Salvo
	 */
	@PostMapping("/cliente/cadastrar")
	public ResponseEntity<?> cadastrarCliente(@Valid @RequestBody Cliente cliente) throws Exception {

		try {
			ObjectData data = new ObjectData();
			data.setData(service.salvarCliente(cliente));
			return new ResponseEntity<>(data, HttpStatus.CREATED);

		} catch (Exception e) {
			return extracted(e);
		}

	}

	private ResponseEntity<?> extracted(Exception e) {
		ExceptionResponse response = new ExceptionResponse(500, UNEXPECTED_ERROR + e.getMessage());
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
