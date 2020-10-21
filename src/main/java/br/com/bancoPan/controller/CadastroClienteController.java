package br.com.bancoPan.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bancoPan.dto.ObjectData;
import br.com.bancoPan.entity.Cliente;
import br.com.bancoPan.entity.Endereco;
import br.com.bancoPan.exception.CustomException;
import br.com.bancoPan.service.ClienteService;

/**
 * Classe de CRUD de Clientes - {@link CadastroClienteController}
 * 
 * @author andrei-lopes - 2020-10-19
 */
@RestController
@RequestMapping("/v1")
public class CadastroClienteController {

	private static final String UNEXPECTED_ERROR = "Erro inesperado: ";

	@Autowired
	private ClienteService service;

	@GetMapping("/clientes")
	public ResponseEntity<?> listaTodosCliente() throws Exception, CustomException {

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
	public ResponseEntity<?> consultaClientePeloId(@PathVariable("id_cliente") String id)
			throws Exception, CustomException {

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
	public ResponseEntity<?> consultaClientePeloCpf(@PathVariable("cpf") String cpf) throws Exception, CustomException {

		try {
			ObjectData data = new ObjectData();
			final Cliente cliente = service.buscaClientePeloCpf(cpf);

			if (!cliente.getNome().isEmpty()) {
				data.setData(cliente);
				return new ResponseEntity<>(data, HttpStatus.OK);

			}
			return ResponseEntity.notFound().build();

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
	public ResponseEntity<?> cadastrarCliente(@Valid @RequestBody Cliente cliente) throws Exception, CustomException {

		try {
			ObjectData data = new ObjectData();

			data.setData(service.salvarCliente(cliente));
			return new ResponseEntity<>(data, HttpStatus.CREATED);

		} catch (Exception e) {
			return extracted(e);
		}

	}

	@PutMapping("/cliente/atualizar/{cpf}")
	public ResponseEntity<?> atualizarClientePeloCpf(@Valid @RequestBody Cliente cliente)
			throws Exception, CustomException {

		try {
			ObjectData data = new ObjectData();

			Endereco endereco = new EnderecoController().atualizaEndereco(cliente.getEndereco());
			cliente.setEndereco(endereco);
			data.setData(service.salvarCliente(cliente));

			return new ResponseEntity<>(data, HttpStatus.CREATED);

		} catch (Exception e) {
			return extracted(e);
		}
	}

	private ResponseEntity<?> extracted(Exception e) {
		CustomException custom = new CustomException(UNEXPECTED_ERROR + e.getMessage());
		return custom.responseException(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
