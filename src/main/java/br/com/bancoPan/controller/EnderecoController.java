package br.com.bancoPan.controller;

import javax.json.JsonObject;
import javax.json.JsonValue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bancoPan.entity.Endereco;
import br.com.bancoPan.exception.CustomException;
import br.com.bancoPan.service.EnderecoService;

@RestController
@RequestMapping("/v1")
public class EnderecoController {

	private static final String ERRO = "Erro";

	private static final String UNEXPECTED_ERROR = null;

	@Value("${url.api.viacep}")
	private String urlApiViacep;

	@Value("${url.api.estado}")
	private String urlApiEstado;

	@Value("${url.api.municipio}")
	private String urlApiMunicipio;

	@Autowired
	EnderecoService service;

	@GetMapping("/endereco/consulta/{cep}")
	public ResponseEntity<?> consultaEndereco(@PathVariable("cep") String cep) throws Exception {
		try {
			Endereco endereco = new Endereco();

			JsonObject jsonInput = getCepResponse(cep);
			JsonValue jsonErro = jsonInput.get(ERRO);

			if (jsonErro == null) {

			}

			return null;

		} catch (Exception e) {
			return extracted(e);
		}
	}

	private JsonObject getCepResponse(String cep) {
		// TODO Auto-generated method stub
		return null;
	}

	public Endereco atualizaEndereco() {
		// TODO Auto-generated method stub
		return null;
	}

	private ResponseEntity<?> extracted(Exception e) {
		CustomException custom = new CustomException(UNEXPECTED_ERROR + e.getMessage());
		return custom.responseException(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
