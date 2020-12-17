package br.com.bancoPan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.bancoPan.model.entity.PessoaEntity;

@Repository
public interface PessoaRepository extends CrudRepository<PessoaEntity, Long> {

	List<PessoaEntity> findByCpfPessoa(String cpf);

	@Query("SELECT p FROM PessoaEntity p JOIN FETCH p.enderecos pe WHERE p.cpfPessoa = :cpf AND pe.cep = :cep")
	PessoaEntity findByCpfPessoaAndEnderecosCep(String cpf, String cep);

	@Query("SELECT p FROM PessoaEntity p JOIN FETCH p.enderecos pe WHERE p.cpfPessoa = :cpf AND pe.idEndereco = :idEndereco")
	PessoaEntity findByCpfPessoaAndEnderecosIdEndereco(String cpf, Long idEndereco);
}
