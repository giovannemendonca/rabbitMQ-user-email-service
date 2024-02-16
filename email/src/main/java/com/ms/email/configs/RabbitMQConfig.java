package com.ms.email.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Essa classe é responsável por criar a fila no RabbitMQ
 * A fila é criada com o nome que está no application.properties
 *
 */
@Configuration
public class RabbitMQConfig {

    @Value("${broker.queue.email.name}")
    private String queue;

    /**
     * Esse método é responsável por criar a fila no RabbitMQ
     * Cria a fila com o nome que está no application.properties
     * a fila é durável ou seja, ela não é apagada quando o RabbitMQ é reiniciado
     */
    @Bean
    public Queue queue() {
        return new Queue(queue, true);
    }

    /**
     * Esse método é responsável por converter a mensagem que será enviada para a fila
     * para JSON e depois enviar para a fila
     */
    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        return new Jackson2JsonMessageConverter(objectMapper);
    }


}
