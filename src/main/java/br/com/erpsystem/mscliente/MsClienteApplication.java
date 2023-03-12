package br.com.erpsystem.mscliente;

import br.com.erpsystem.mscliente.entity.Cliente;
import br.com.erpsystem.mscliente.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MsClienteApplication{

	public static void main(String[] args) {
		SpringApplication.run(MsClienteApplication.class, args);
	}

}
