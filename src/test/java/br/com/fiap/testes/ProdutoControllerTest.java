package br.com.fiap.testes;

import br.com.fiap.testes.controller.ProdutoController;
import br.com.fiap.testes.model.Produto;
import br.com.fiap.testes.service.ProdutoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ProdutoController.class)
@AutoConfigureMockMvc
public class ProdutoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProdutoService produtoService;

    @TestConfiguration
    static class ProdutoServiceConfig{
        @Bean
        public ProdutoService produtoService() {
            return Mockito.mock(ProdutoService.class);
        }
    }

    @Test
    @DisplayName("GET /produtos/{id} deve retornar Produto quando ID existir")
    void buscarPorId_QuandoIdExistir_DeveRetornarStatusOkEProduto() throws Exception {
        Produto produtoMock = new Produto();
        produtoMock.setId(1L);
        produtoMock.setNome("Produto Mock");
        produtoMock.setPreco(50.0);

        when(produtoService.buscarPorId(1L)).thenReturn(Optional.of(produtoMock));

        mockMvc.perform(get("/produtos/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Produto Mock"))
                .andExpect(jsonPath("$.preco").value(50.0));
    }
}
