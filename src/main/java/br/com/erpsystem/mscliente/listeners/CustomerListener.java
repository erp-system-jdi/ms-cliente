package br.com.erpsystem.mscliente.listeners;

import br.com.erpsystem.mscliente.constants.RabbitMQConstants;
import br.com.erpsystem.mscliente.dto.CustomerDTO;
import br.com.erpsystem.mscliente.services.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class CustomerListener {

    private final CustomerService customerService;

    @RabbitListener(queues = RabbitMQConstants.CLIENTE_QUEUE)
    public void listen(@Payload CustomerDTO customerDTO){
        log.info("ClienteListener.listen - Start - clienteDTO: {}", customerDTO);

        customerService.salvarCliente(customerDTO);

        log.info("ClienteListener.listen - End");
    }
}
