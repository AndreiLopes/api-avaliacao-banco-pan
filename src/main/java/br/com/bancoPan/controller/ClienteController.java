package br.com.bancoPan.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bancoPan.model.Endereco;
import br.com.bancoPan.model.Pessoa;
import br.com.bancoPan.service.PessoaService;
import br.com.bancoPan.service.UtilService;

/**
 * Classe para orquestrar requisições de Clientes.
 * 
 * @author andrei
 */
@RestController
@RequestMapping(value = "/v1")
public class ClienteController {

	private UtilService utilService;

	private PessoaService service;

	private static final Logger LOGGER = LoggerFactory.getLogger(ClienteController.class);

	@Autowired
	public ClienteController(UtilService utilService, PessoaService service) {
		this.utilService = utilService;
		this.service = service;
	}

	/**
	 * Obtem os dados cadastrais de uma pessoa
	 * 
	 * @param cpf
	 * @return
	 */
	@GetMapping(value = "/pessoa/{cpf}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Pessoa> obterDadosPessoa(@PathVariable("cpf") String cpf) {

		LOGGER.info("Recebendo requisição para obter os dados da pessoa com o parâmetro cpf: {}", cpf);

		// Validar os dados de entrada (gera uma exception em casos de erro)
		utilService.validarDadosEntrada(cpf, null, null, null);

		// Realiza a consulta
		Pessoa pessoa = service.consultarPessoa(cpf);

		if (pessoa == null) {
			return ResponseEntity.noContent().build();
		}

		// Retornar o resutado
		LOGGER.info("Finalizando com sucesso a requisição da pessoa com o parâmetro cpf: {}", cpf);

		return ResponseEntity.ok(pessoa);
	}

	/**
	 * Obtem os dados de endereço de uma pessoa
	 * 
	 * @param cpf
	 * @param cep
	 * @return
	 */
	@GetMapping("/pessoa/{cpf}/endereco/{cep}")
	public ResponseEntity<List<Endereco>> obterEnderecoPessoa(@PathVariable("cpf") String cpf,
			@PathVariable("cep") String cep) {

		LOGGER.info("Recebendo requisição para obter os dados da pessoa com o parâmetro cpf: {}, cep: {}", cpf, cep);

		// Validar os dados de entrada (gera uma exception em casos de erro)
		utilService.validarDadosEntrada(cpf, cep, null, null);

		// Realiza a consulta
		List<Endereco> enderecos = service.consultarEnderecoPessoa(cpf, cep);

		if (enderecos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		// Retornar o resutado
		LOGGER.info("Finalizando com sucesso a requisição da pessoa com o parâmetro cpf: {}, cep: {}", cpf, cep);

		return ResponseEntity.ok(enderecos);
	}

	@PutMapping("/pessoa/{cpf}/endereco/{enderecoId}")
	public ResponseEntity<Endereco> alterarEnderecoPessoa(@PathVariable("cpf") String cpf,
			@PathVariable("enderecoId") Long enderecoId, @RequestBody Endereco enderecoAlterado) {

		LOGGER.info("Recebendo requisição para alterar endereço da pessoa com o parâmetro cpf: {}, enderecoId: {}", cpf,
				enderecoId);

		// Validar os dados de entrada (gera uma exception em casos de erro)
		utilService.validarDadosEntrada(cpf, null, enderecoId, enderecoAlterado);

		Endereco endereco = service.alterarEnderecoPessoa(cpf, enderecoId, enderecoAlterado);

		if (endereco == null) {
			return ResponseEntity.noContent().build();

		} else {
			return ResponseEntity.ok(endereco);
		}
	}
}
