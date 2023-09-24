package me.stellaria.padraodeprojetosspring.service;

import me.stellaria.padraodeprojetosspring.model.Endereco;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Client HTTP criado via Feign para acessar a API do ViaCEP.
 * Através da anotação @FeignClient, definimos o nome do client e a URL base da API.
 * Através da anotação @RequestMapping, definimos o método HTTP e o endpoint que queremos acessar.
 * Através da anotação @PathVariable, definimos o parâmetro que será passado na URL.
 */

@Service
@FeignClient(name = "viacep", url = "https://viacep.com.br/ws")
public interface ViaCepService {

    @RequestMapping(method = RequestMethod.GET, value = "/{cep}/json")
    Endereco consultarCep(@PathVariable("cep") String cep);
}
