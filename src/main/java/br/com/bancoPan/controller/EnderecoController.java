package br.com.bancoPan.controller;

import java.util.List;

import javax.json.JsonObject;
import javax.json.JsonValue;

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
import br.com.bancoPan.entity.Cidade;
import br.com.bancoPan.entity.Endereco;
import br.com.bancoPan.entity.Estado;
import br.com.bancoPan.exception.CustomException;
import br.com.bancoPan.service.EnderecoService;

/**
 * Classe para orquestrar requisições de Endereco.
 * 
 * @author andrei-lopes - 2020-10-21
 */
@RestController
@RequestMapping(value = "/v1")
public class EnderecoController {

	private static final String ERROR = "Erro: ";

	@Autowired
	EnderecoService service;

	/**
	 * Atualiza endereco do cliente
	 * 
	 * @param endereco
	 * @return
	 */
	@PostMapping(value = "/endereco/editar")
	public ResponseEntity<?> atualizaEndereco(@RequestBody Endereco endereco) {

		try {
			ObjectData data = new ObjectData();
			data.setData(service.atualizaEndereco(endereco));

			return ResponseEntity.ok(data);
		} catch (Exception e) {
			return extracted(e);
		}
	}

	/**
	 * Obtem Lista de estados.
	 * 
	 * @return
	 */
	@GetMapping(value = "endereco/consulta/estados")
	public ResponseEntity<?> listaTodosEstados() {

		try {
			ObjectData data = new ObjectData();
			List<Estado> estados = service.obtemListaTodosEstados();
			data.setData(new Estado().classificaEstados(estados));

			return ResponseEntity.ok(data);
		} catch (Exception e) {
			return extracted(e);
		}
	}

	/**
	 * Obtem endereco pelo CEP.
	 * 
	 * @param cep
	 * @return
	 */
	@GetMapping(value = "endereco/consulta/{cep}")
	public ResponseEntity<?> obtemEnderecoPeloCep(@PathVariable(value = "cep") String cep) {

		JsonObject jsonInput = service.obtemCep(cep);
		Endereco endereco = null;
		JsonValue jsonErro = jsonInput.get("erro");

		if (jsonErro == null) {

			endereco = new Endereco(jsonInput.getString("logradouro"), jsonInput.getString("bairro"),
					jsonInput.getString("localidade"), jsonInput.getString("uf"), jsonInput.getString("cep"));

			return ResponseEntity.ok(endereco);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * Otem cidade pelo ID.
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/endereco/consulta/cidades/{id}")
	public ResponseEntity<?> obtemCidadePeloId(@PathVariable(value = "id") int id) {

		List<Cidade> cidades = service.obterCidadePeloId(id);

		if (!cidades.isEmpty()) {
			return ResponseEntity.ok(cidades);

		} else {
			return ResponseEntity.notFound().build();
		}
	}

	private ResponseEntity<?> extracted(Exception e) {
		CustomException custom = new CustomException(ERROR + e.getMessage());
		return custom.responseException(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
