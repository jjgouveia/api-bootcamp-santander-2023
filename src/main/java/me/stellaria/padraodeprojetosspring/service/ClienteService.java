package me.stellaria.padraodeprojetosspring.service;

import me.stellaria.padraodeprojetosspring.model.Cliente;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Interface que define o padrão <b>Strategy</b> no domínio de cliente. Com isso, podemos ter
 * múltiplas implementações dessa mesma interface.
 */
@Service
public interface ClienteService {
    Iterable<Cliente> findAll();
    Optional<Cliente> findById(Long id);
    void create(Cliente cliente);
    void update(Long id, Cliente cliente);
    void delete(Long id);

}
