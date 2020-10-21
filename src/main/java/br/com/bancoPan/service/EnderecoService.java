package br.com.bancoPan.service;

import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.bancoPan.entity.Cidade;
import br.com.bancoPan.entity.Endereco;
import br.com.bancoPan.entity.Estado;
import br.com.bancoPan.repository.EnderecoRepository;

@Service
public class EnderecoService {

	@Value("${url.api.viacep}")
	private String urlApiViacep;

	@Value("${url.api.estado}")
	private String urlApiEstado;

	@Autowired
	EnderecoRepository repository;

	public Endereco atualizaEndereco(Endereco endereco) {
		return repository.updateEndereco(endereco);
	}

	public List<Estado> obtemListaTodosEstados() {

		JsonArray arrayResponse = null;
		List<Estado> estados = new ArrayList<Estado>();

		try {
			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpGet httpGet = new HttpGet(urlApiEstado);

			HttpResponse httpResponse = httpClient.execute(httpGet);
			HttpEntity httpEntity = httpResponse.getEntity();

			arrayResponse = Json.createReader(httpEntity.getContent()).readArray();

			for (int i = 0; i < arrayResponse.size(); i++) {

				JsonObject object = arrayResponse.getJsonObject(i);
				estados.add(new Estado(object.getInt("id"), object.getString("sigla"), object.getString("nome")));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return estados;
	}

	public JsonObject obtemCep(String cep) {

		JsonObject enderecoJsonRecebido = null;
		String json = "/json";

		try {
			HttpClient httpclient = HttpClientBuilder.create().build();
			HttpGet httpGet = new HttpGet(urlApiViacep + cep + json);

			HttpResponse response = httpclient.execute(httpGet);
			HttpEntity entity = response.getEntity();

			enderecoJsonRecebido = Json.createReader(entity.getContent()).readObject();

		} catch (Exception e) {
			return enderecoJsonRecebido;
		}
		return enderecoJsonRecebido;
	}

	public List<Cidade> obterCidadePeloId(int id) {

		JsonArray arrayResponse = null;
		List<Cidade> cidades = new ArrayList<Cidade>();
		String municipios = "/municipios/";

		try {
			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpGet httpGet = new HttpGet(urlApiEstado + id + municipios);

			HttpResponse httpResponse = httpClient.execute(httpGet);
			HttpEntity httpEntity = httpResponse.getEntity();

			arrayResponse = Json.createReader(httpEntity.getContent()).readArray();

			for (int i = 0; i < arrayResponse.size(); i++) {

				JsonObject object = arrayResponse.getJsonObject(i);
				int codigo = object.getInt("id");
				String nome = object.getString("nome");

				JsonObject microrregiao = object.getJsonObject("microrregiao");
				JsonObject mesorregiao = microrregiao.getJsonObject("mesorregiao");
				JsonObject estado = mesorregiao.getJsonObject("UF");
				String sigla = estado.getString("sigla");

				cidades.add(new Cidade(codigo, nome, sigla));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return cidades;
	}
}
