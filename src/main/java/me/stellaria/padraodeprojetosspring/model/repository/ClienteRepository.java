package me.stellaria.padraodeprojetosspring.model.repository;
import me.stellaria.padraodeprojetosspring.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {
}
