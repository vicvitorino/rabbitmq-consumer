package br.rabbitmqpoc.consumer;

import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RabbitMQConsumer {

//    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
//    public void receive(@Payload Message message) {
//        System.out.println("Message " + message + "  " + LocalDateTime.now());
//        String ultima = String.valueOf(message.getHeaders().get("ultima"));
//        if (ultima.equals("sim")) {
//            System.out.println(ultima);
//        }
//        String payload = String.valueOf(message.getPayload());
//    }
//
    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
    public void consume(final String message) throws Exception {
        log.info(String.format("Recebido -> %s", message));
        try {
//            Thread.sleep(1000);
            Thread.sleep(0);
        } catch (InterruptedException ex) {
            Logger.getLogger(RabbitMQConsumer.class.getName()).log(Level.SEVERE, null, ex);
        }
        final String lastCharacter = message.substring(message.length() - 1);
        if (lastCharacter.equals("1")) {
            throw new Exception("Ocorreu alguma falha");
        }
        log.info(String.format("Concluido"));
    }

}
