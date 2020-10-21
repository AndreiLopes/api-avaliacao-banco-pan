package br.com.bancoPan.controller;

import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bancoPan.dto.ObjectData;
import br.com.bancoPan.entity.Endereco;
import br.com.bancoPan.entity.Estado;
import br.com.bancoPan.service.EnderecoService;

@RestController
@RequestMapping("/v1")
public class EnderecoController {

	private static final String ERRO = "Erro";

	@Value("${url.api.viacep}")
	private String urlApiViacep;

	@Value("${url.api.estado}")
	private String urlApiEstado;

	@Value("${url.api.municipio}")
	private String urlApiMunicipio;

	@Autowired
	private EnderecoService service;

	@PostMapping("/endereco/atualizar")
	public Endereco atualizaEndereco(@RequestBody Endereco endereco) {
		return service.atualizaEndereco(endereco);
	}

	@GetMapping("/endereco/consulta/{cep}")
	public ResponseEntity<?> consultaEndereco(@PathVariable("cep") String cep) throws Exception {

		ObjectData data = new ObjectData();
		Endereco endereco = null;

		JsonObject jsonInput = obterCep(cep);
		JsonValue jsonErro = jsonInput.get(ERRO);

		if (jsonErro == null) {

			endereco = new Endereco(jsonInput.getString("logradouro"), jsonInput.getString("numeroCasa"),
					jsonInput.getString("bairro"), jsonInput.getString("cidade"), jsonInput.getString("cep"));
			data.setData(endereco);

			return new ResponseEntity<>(data, HttpStatus.OK);
		}
		return ResponseEntity.notFound().build();
	}

	public JsonObject obterCep(String cep) {

		JsonObject enderecoInput = null;
		String json = "json";

		try {
			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpGet httpGet = new HttpGet(urlApiViacep + "/" + cep + "/" + json);
			HttpResponse httpResponse = httpClient.execute(httpGet);
			HttpEntity httpEntity = httpResponse.getEntity();
			enderecoInput = Json.createReader(httpEntity.getContent()).readObject();

		} catch (Exception e) {
			return enderecoInput;
		}
		return enderecoInput;
	}

	@GetMapping("/endereco/consulta/estados")
	public ResponseEntity<?> listaTodosEstados() {

		List<Estado> estados = obterListaTodosEstados();
		return ResponseEntity.ok(new Estado().classificarEstados(estados));
	}

	private List<Estado> obterListaTodosEstados() {
		JsonArray jsonArray = null;
		List<Estado> estados = new ArrayList<Estado>();

		try {

			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpGet httpGet = new HttpGet(urlApiEstado);
			HttpResponse httpResponse = httpClient.execute(httpGet);
			HttpEntity httpEntity = httpResponse.getEntity();

			jsonArray = Json.createReader(httpEntity.getContent()).readArray();

			for (int i = 0; i < jsonArray.size(); i++) {

				JsonObject jsonObject = jsonArray.getJsonObject(i);
				estados.add(new Estado(jsonObject.getInt("id"), jsonObject.getString("sigla"),
						jsonObject.getString("nome")));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return estados;
	}
}
