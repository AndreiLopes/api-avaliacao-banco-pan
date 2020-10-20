package br.com.bancoPan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.bancoPan.entity.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, String> {

	@Query(value = "UPDATE TB_ENDERECO SET bairro=:endereco.bairro, cep=:endereco.cep, cidade=:endereco.cidade, logradouro=:endereco.logradouro, numero=:endereco.numero, uf=:endereco.uf WHERE cod=:endereco.cod", nativeQuery = true)
	Endereco updateEndereco(@Param("endereco") Endereco endereco);

	Endereco findByCep(String cep);
}