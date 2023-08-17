package br.rabbitmqpoc;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.queue.name}")
    private String queue;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    @Bean
    public Queue queue() {
//        return new Queue(queue, true);
        return QueueBuilder.durable(queue)
                .withArgument("x-dead-letter-exchange", "dead-exchange")
                .withArgument("x-dead-letter-routing-key", "routing-key-teste-erro")
                .build();
    }

//    @Bean
//    public DirectExchange exchange() {
//        return new DirectExchange(exchange);
//    }
//
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchange);
    }

    @Bean
    public DirectExchange deadLetterExchange() {
        return new DirectExchange("dead-exchange");
    }

    @Bean
    public Queue dlq() {
        return QueueBuilder.durable("teste-erro").build();
    }

    @Bean
    public Binding DLQbinding() {
        return BindingBuilder
                .bind(dlq())
                .to(deadLetterExchange())
                .with("routing-key-teste-erro");
    }

    @Bean
    public Binding binding() {
        return BindingBuilder
                .bind(queue())
                .to(exchange())
                .with(routingKey);
    }

}
