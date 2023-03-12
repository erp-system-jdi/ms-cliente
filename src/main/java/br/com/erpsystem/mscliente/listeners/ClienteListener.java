package br.com.erpsystem.mscliente.listeners;

import br.com.erpsystem.mscliente.constants.RabbitMQConstants;
import br.com.erpsystem.mscliente.dto.ClienteDTO;
import br.com.erpsystem.mscliente.services.ClienteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ClienteListener {

    private final ClienteService clienteService;

    @RabbitListener(queues = RabbitMQConstants.CLIENTE_QUEUE)
    public void listen(@Payload ClienteDTO clienteDTO){
        log.info("ClienteListener.listen - Start - clienteDTO: {}", clienteDTO);

        clienteService.salvarCliente(clienteDTO);

        log.info("ClienteListener.listen - End");
    }
}
