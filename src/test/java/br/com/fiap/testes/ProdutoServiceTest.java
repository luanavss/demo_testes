package br.com.fiap.testes;

import br.com.fiap.testes.model.Produto;
import br.com.fiap.testes.repository.ProdutoRepository;
import br.com.fiap.testes.service.ProdutoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProdutoServiceTest {
    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ProdutoService produtoService;

    private Produto produtoExistente;

    @BeforeEach
    void setUp(){
        produtoExistente = new Produto();
        produtoExistente.setId(1L);
        produtoExistente.setNome("Produto 1");
        produtoExistente.setPreco(50.0);
    }

    @Test
    @DisplayName("Deve retornar um Produto quando ID existir")
    void buscarPorId_QuandoIdExistir_DeveRetornarProduto(){
        when(produtoRepository.findById(anyLong())).thenReturn(Optional.of(produtoExistente));

        Optional<Produto> resultado = produtoService.buscarPorId(1L);

        assertTrue(resultado.isPresent(), "O Optional não deveria estar vazio.");
        assertEquals(produtoExistente.getId(), resultado.get().getId());
        assertEquals(produtoExistente.getNome(), resultado.get().getNome());
        assertEquals(produtoExistente.getPreco(), resultado.get().getPreco());

        verify(produtoRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Deve retornar Optional vazio quando o ID não existir.")
    void buscarPorId_QuandoIdNaoExistir_DeveRetornarOptionalVazio(){

        when(produtoRepository.findById(anyLong())).thenReturn(Optional.empty());

        Optional<Produto> resultado = produtoService.buscarPorId(99L);

        assertFalse(resultado.isPresent(),"O Optional deveria estar vazio");

        verify(produtoRepository).findById(99L);
    }
}
