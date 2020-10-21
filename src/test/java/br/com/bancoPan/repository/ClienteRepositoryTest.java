package br.com.bancoPan.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.bancoPan.entity.Cliente;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClienteRepositoryTest {

	@Autowired
	private ClienteRepository cliRepository;

	@Test
	public void testConsultaClientePorCpf() {

		Cliente cliente = cliRepository.findByCpf("12345678901");
		assertThat(cliente.getNome()).isEqualTo("Luke Skywalker");

		cliente = cliRepository.findByCpf("10987654321");
		assertThat(cliente.getNome()).isEqualTo("Anakin Skywalker");
	}

	@Test
	public void testClientes() {

		List<Cliente> clientes = cliRepository.findAll();
		assertThat(clientes.size()).isEqualTo(2);
	}

}
