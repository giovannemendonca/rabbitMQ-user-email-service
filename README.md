# Spring Boot User Email Service with RabbitMQ

Este é um projeto Spring Boot que consiste em dois microserviços: um para cadastro de usuários e outro para envio de e-mails de boas-vindas. A comunicação assíncrona entre os microserviços é realizada utilizando RabbitMQ.

## Microserviço de Usuário (User)

O microserviço de usuário é responsável por gerenciar o cadastro de novos usuários. Ele oferece as seguintes funcionalidades:

- Cadastro de novos usuários através da rota `POST /v1/users`, enviando um corpo de requisição JSON com os campos `name` e `email`.
- Armazenamento seguro dos dados dos usuários no PostgreSQL.
- Exposição de um endpoint RESTful para o cadastro de usuários.
- Após o cadastro de um usuário, uma mensagem é enviada para a fila RabbitMQ para que o microserviço de e-mail possa enviar um e-mail de boas-vindas.

O formato do corpo da requisição para cadastrar um usuário é o seguinte:

```json
{
  "name": "Nome do Usuário",
  "email": "email@example.com"
}
```

## Microserviço de E-mail (Email)

O microserviço de e-mail é responsável por enviar e-mails de boas-vindas aos usuários cadastrados. Ele oferece as seguintes funcionalidades:

- Consumo de mensagens da fila RabbitMQ para processar os dados do usuário e enviar e-mails.
- Utiliza os dados do usuário, como nome e e-mail, para personalizar os e-mails de boas-vindas.
- Envio seguro de e-mails utilizando uma biblioteca de envio de e-mails, como JavaMailSender.

## Tecnologias Utilizadas

Este projeto utiliza as seguintes tecnologias:

- Java
- Spring Boot
- PostgreSQL
- RabbitMQ
- Docker
- Isomnia (ou Postman)

## Como Executar

1. Clone o repositório: `git clone git@github.com:giovannemendonca/rabbitMQ-user-email-service.git`
2. Configure o PostgreSQL e o RabbitMQ conforme necessário.
3. Execute cada microserviço (User e Email) separadamente.
4. Teste a funcionalidade de cadastro de usuários e o envio de e-mails de boas-vindas.

