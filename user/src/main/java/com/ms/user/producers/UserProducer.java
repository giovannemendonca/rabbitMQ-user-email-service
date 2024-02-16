package com.ms.user.producers;

import com.ms.user.dto.EmailDto;
import com.ms.user.models.UserModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Classe que publica uma mensagem na fila do RabbitMQ
 * O método publishMessageEmail recebe um objeto do tipo UserModel e cria um objeto do tipo EmailDto
 * com os dados do usuário e publica na fila do RabbitMQ
 * O método publishMessageEmail é chamado no método save da classe UserService
 */
@Component
public class UserProducer {

    // RabbitTemplate é uma classe do Spring que permite a comunicação com o RabbitMQ
    final RabbitTemplate rabbitTemplate;

    public UserProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value("${broker.queue.email.name}") // exchange do tipo default: chave routingKey é o nome da fila
    private String routingKey;


    /**
     * Método que publica uma mensagem na fila do RabbitMQ
     * o convertAndSend recebe 3 parâmetros:
     * 1 - nome do exchange
     * 2 - routingKey
     * 3 - objeto que será enviado
     */
    public void publishMessageEmail(UserModel model) {
        var emailDto = new EmailDto();
        emailDto.setUserId(model.getId());
        emailDto.setEmailTo(model.getEmail());
        emailDto.setSubject("Cadastro realizado com sucesso");
        emailDto.setText("Seja bem vindo, " + model.getName() + "!" + "\n Seu cadastro foi realizado com sucesso! \n" +
                "Agora você pode aproveitar todos os nossos serviços! \n" +
                "Obrigado por se cadastrar! \n" +
                "Atenciosamente, \n" +
                "Equipe de suporte");

        rabbitTemplate.convertAndSend("", routingKey, emailDto);
    }


}
