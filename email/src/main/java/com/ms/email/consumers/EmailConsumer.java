package com.ms.email.consumers;

import com.ms.email.dto.EmailDto;
import com.ms.email.models.EmailModel;
import com.ms.email.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * Essa classe é responsável por consumir as mensagens da fila de email
 * através do RabbitMQ e enviar o email.
 */
@Component
public class EmailConsumer {

    private final EmailService emailService;

    public EmailConsumer(EmailService emailService) {
        this.emailService = emailService;
    }


    /**
     * Método que consome a fila de email e envia o email.
     * O método @RabbitListener é responsável por ouvir a fila de email.
     *
     * @param body
     */
    @RabbitListener(queues = "${broker.queue.email.name}")
    public void listenEmailQueue(@Payload EmailDto emailDto) {

        var emailModel = new EmailModel();
        BeanUtils.copyProperties(emailDto, emailModel);

        emailService.sendEmail(emailModel);
    }

}
