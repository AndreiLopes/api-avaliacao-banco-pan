package br.com.bancoPan.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.bancoPan.model.entity.TelefoneEntity;

@Repository
public interface TelefoneRepository extends CrudRepository<TelefoneEntity, Long> {
}
