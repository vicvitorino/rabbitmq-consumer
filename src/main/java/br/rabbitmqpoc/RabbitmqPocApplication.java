package br.rabbitmqpoc;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class RabbitmqPocApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqPocApplication.class, args);
    }

}
