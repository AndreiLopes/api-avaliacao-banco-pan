package br.com.bancoPan.service;

import java.util.List;

import br.com.bancoPan.model.Endereco;
import br.com.bancoPan.model.Estado;
import br.com.bancoPan.model.Municipio;

/**
 * Interface de Endereco
 * 
 * @author andrei
 *
 */
public interface EnderecoService {

	List<Estado> obterListaEstados();

	List<Municipio> obterListaMunicipios(Integer estadoId);

	Endereco obterEnderecoDoCep(String cep);

}
