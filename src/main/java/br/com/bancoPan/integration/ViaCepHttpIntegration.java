package br.com.bancoPan.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.bancoPan.model.Endereco;

/**
 * Classe que representa a Integracao HTTP com a ViaCep
 * 
 * @author andrei
 *
 */
@Component
public class ViaCepHttpIntegration {

	@Value("${url.api.viacep}")
	private String viaCepUrlApi;

	@Value("${url.api.viacep.cep}")
	private String viaCepServiceCep;

	@Autowired
	private RestTemplate restTemplate;

	/**
	 * Consulta no endpoint da API ViaCep para obter os dados de endereço de um
	 * determinado CEP
	 * 
	 * @param cep
	 * @return
	 */
	public Endereco obterDadosCep(String cep) {

		ResponseEntity<Endereco> response = restTemplate.exchange(viaCepUrlApi + viaCepServiceCep, HttpMethod.GET, null,
				new ParameterizedTypeReference<Endereco>() {
				}, cep.replace("-", ""));

		return response.getBody();
	}
}
