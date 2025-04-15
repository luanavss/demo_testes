package br.com.fiap.testes;

import br.com.fiap.testes.controller.ProdutoController;
import br.com.fiap.testes.service.ProdutoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class TestesApplicationTests {

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private ProdutoController produtoController;

	@Test
	void contextLoads() {
		assertNotNull(produtoService);
		assertNotNull(produtoController);
	}

}
