package br.com.bancoPan.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.bancoPan.model.entity.EnderecoEntity;

@Repository
public interface EnderecoRepository extends CrudRepository<EnderecoEntity, Long> {
}
