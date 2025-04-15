package br.com.fiap.testes.service;

import br.com.fiap.testes.model.Produto;
import br.com.fiap.testes.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;
    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

    public Optional<Produto> buscarPorId(Long id){
        return produtoRepository.findById(id);
    }
}
