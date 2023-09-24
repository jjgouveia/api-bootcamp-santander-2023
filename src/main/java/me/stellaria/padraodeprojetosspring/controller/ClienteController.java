package me.stellaria.padraodeprojetosspring.controller;

import me.stellaria.padraodeprojetosspring.model.Cliente;
import me.stellaria.padraodeprojetosspring.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Esse {@link RestController} representa o padrão <b>Facade</b>, pois abstrai toda a
 * complexidade das integrações (banco de dados, chamada à api's externas) em uma interface
 * simples e coesa <b>(API REST)</b>.
 */

@RestController
@RequestMapping("/api")
public class ClienteController {

    //O Autowired, por padrão, sempre vai prover os recursos dos componentes do Spring como um Singleton
    @Autowired // Aqui é onde acontece a inversão de controle (IoC);
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<Iterable<Cliente>> getAll() {
        return ResponseEntity.ok(clienteService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Cliente>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Cliente> create(@RequestBody Cliente cliente){
       try {
           clienteService.create(cliente);
           return ResponseEntity.ok(cliente);
       } catch (Exception e) {
           return ResponseEntity.badRequest().build();
       }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> update(@PathVariable Long id, @RequestBody Cliente cliente){
        clienteService.update(id, cliente);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cliente> delete(@PathVariable Long id) {
        clienteService.delete(id);
        return  ResponseEntity.ok().build();
    }
}