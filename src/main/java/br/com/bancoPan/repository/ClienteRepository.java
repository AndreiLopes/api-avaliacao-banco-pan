package br.com.bancoPan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bancoPan.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	Cliente findById(long id);
	Cliente findByCpf(String cpf);
}
