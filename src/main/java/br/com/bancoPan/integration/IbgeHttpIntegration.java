package br.com.bancoPan.integration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.bancoPan.integration.model.Localidade;

/**
 * Classe que representa a Integracao HTTP com o IBGE
 * 
 * @author andrei
 */
@Component
public class IbgeHttpIntegration {

	@Value("${url.api.estado}")
	private String ibgeApiUrl;

	@Value("${url.service.localidade}")
	private String ibgeServiceLocalidade;

	@Value("${url.service.municipios}")
	private String ibgeServiceMunicipio;

	@Autowired
	private RestTemplate restTemplate;

	/**
	 * Obtem uma lista de localidades do service do IBGE
	 * 
	 * @return uma lista de Localidades
	 */
	public List<Localidade> obtemListaDeLocalidades() {

		ResponseEntity<List<Localidade>> response = restTemplate.exchange(ibgeApiUrl + ibgeServiceLocalidade,
				HttpMethod.GET, null, new ParameterizedTypeReference<List<Localidade>>() {
				});

		return response.getBody();
	}

	public List<Localidade> obtemListaDeMunicipiosLocalidades(Integer localidadeId) {

		ResponseEntity<List<Localidade>> response = restTemplate.exchange(ibgeApiUrl + ibgeServiceMunicipio,
				HttpMethod.GET, null, new ParameterizedTypeReference<List<Localidade>>() {
				}, localidadeId.toString());

		return response.getBody();
	}
}