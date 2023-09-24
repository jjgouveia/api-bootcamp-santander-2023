package me.stellaria.padraodeprojetosspring;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@OpenAPIDefinition(info = @Info(title = "DIO BTCMP SANTANDER API",
		version = "1.0", description = "Cadastro de clientes e endereços",
		contact = @Contact(name = "Jr Gouveia", email = "gouvik.dev@gmail.com")))
@EnableFeignClients
@SpringBootApplication
public class PadraodeprojetosSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(PadraodeprojetosSpringApplication.class, args);
	}

}
