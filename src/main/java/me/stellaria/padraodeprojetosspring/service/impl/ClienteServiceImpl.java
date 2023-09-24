package me.stellaria.padraodeprojetosspring.service.impl;

import me.stellaria.padraodeprojetosspring.model.Cliente;
import me.stellaria.padraodeprojetosspring.model.Endereco;
import me.stellaria.padraodeprojetosspring.model.repository.ClienteRepository;
import me.stellaria.padraodeprojetosspring.model.repository.EnderecoRepository;
import me.stellaria.padraodeprojetosspring.service.ClienteService;
import me.stellaria.padraodeprojetosspring.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Implementação da <b>Strategy</b> {@link ClienteService}, a qual pode ser injetada pelo Spring
 * através de <i>Dependency Injection</i> com a anotação <code>@Autowired</code>.
 */

@Service
public class ClienteServiceImpl implements ClienteService {
    // TODO Singleton: Injetar os componentes necessários para a implementação do serviço com o @Autowired
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ViaCepService viaCepService;
    // TODO Strategy: Implementar os métodos da interface ClienteService
    // TODO Facade: Abstrair integrações com subsistemas, provendo uma interface simples para o cliente.
    @Override
    public Iterable<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    @Override
    public Optional<Cliente> findById(Long id) {
        if (id == null)
            throw new IllegalArgumentException("O id não pode ser nulo.");


        Optional<Cliente> cliente = clienteRepository.findById(id);

        if(cliente.isPresent()){
            return cliente;
        } else {
            throw new RuntimeException("Cliente não encontrado");
        }

    }

    @Override
    public void create(Cliente cliente) {
        salvarClienteComCep(cliente);
    }
    private void salvarClienteComCep(Cliente cliente) {
        String cep = cliente.getEndereco().getCep();
        // Verifica se o endereço já existe pelo cep
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            // Caso não exista, integrar com o ViaCEP e persistir o retorno.
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        //Inserir cliente, vinculando o endereço (novo ou existente)
        cliente.setEndereco(endereco);
        clienteRepository.save(cliente);
    }

    @Override
    public void update(Long id, Cliente cliente) {
        if(cliente == null){
            throw new RuntimeException("Cliente não pode ser nulo");
        }
        Optional<Cliente> clienteBd = clienteRepository.findById(id);
        if(clienteBd.isPresent()){
            cliente.setId(id);
            salvarClienteComCep(cliente);
        } else {
            throw new RuntimeException("Cliente não encontrado");
        }
    }

    @Override
    public void delete(Long id) {
        if (id == null){
            throw new IllegalArgumentException("Id não pode ser nulo ou vazio");
        }
        Optional<Cliente> clienteBd = clienteRepository.findById(id);
        if(clienteBd.isPresent())
            clienteRepository.deleteById(id);
    }
}
