package com.crud.pessoa.crud_test_pessoa.controller;

import com.crud.pessoa.crud_test_pessoa.entity.Pessoa;
import com.crud.pessoa.crud_test_pessoa.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/pessoa")
public class PessoaController {

    private PessoaRepository pessoaRepository;

    @Autowired
    public PessoaController(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @PostMapping
    public String criarPessoa(@RequestBody Pessoa pessoa){
        Pessoa salvo = pessoaRepository.save(pessoa);
        return "Pessoa criada com id " + salvo.getId();
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<Pessoa> atualizar(@PathVariable Long id, @RequestBody Pessoa pessoa){
        return pessoaRepository.findById(id)
                .map(gravar -> {
                    gravar.setNome(pessoa.getNome());
                    gravar.setEmail(pessoa.getEmail());
                    gravar.setIdade(pessoa.getIdade());
                    Pessoa atualizado = pessoaRepository.save(gravar);
                    return ResponseEntity.ok().body(atualizado);
                }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Pessoa> getAllPessoa(){
        return pessoaRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public void deletarPorId(@PathVariable Long id){
        pessoaRepository.deleteById(id);
    }
}
