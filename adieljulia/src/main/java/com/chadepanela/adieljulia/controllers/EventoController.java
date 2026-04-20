package com.chadepanela.adieljulia.controllers;

import com.chadepanela.adieljulia.models.Convidado;
import com.chadepanela.adieljulia.repositories.ConvidadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // Permite que o Front-end na Vercel acesse sua API
public class EventoController {

    @Autowired
    private ConvidadoRepository repository;

    // Retorna a lista de números de 1 a 56 que AINDA NÃO foram escolhidos
    @GetMapping("/numeros-disponiveis")
    public ResponseEntity<List<Integer>> getNumerosDisponiveis() {
        List<Integer> escolhidos = repository.findNumerosEscolhidos();
        List<Integer> todos = IntStream.rangeClosed(1, 56).boxed().collect(Collectors.toList());
        todos.removeAll(escolhidos);
        return ResponseEntity.ok(todos);
    }

    // Salva o convidado com o número
    @PostMapping("/confirmar")
    public ResponseEntity<String> confirmarPresenca(@RequestBody Convidado convidado) {
        try {
            repository.save(convidado);
            return ResponseEntity.ok("Presença confirmada com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro: Número já escolhido ou dados inválidos.");
        }
    }

    // Rota para seu controle (Painel de Admin)
    @GetMapping("/admin/lista")
    public ResponseEntity<List<Convidado>> getTodosConvidados() {
        return ResponseEntity.ok(repository.findAll());
    }
}