# Sistema Bancário

Esse repositório armazena um projeto que está dividido nas seguintes estrutura:

/projeto/  
├── frontend/  
├── backend/  
├── api/  
├── init-db/  
├── docker-compose.yml  
└── README.md  

## /frontend
Esse diretório possui uma aplicação desensolvida em React 18.  
Consome os endpoints dos outros projetos e fornce uma interface de interação para o usuário final.
Interface:
![backend-image](/images/frontend-image.png)

### Tecnologias

- React 18.3.1;
- React DOM 18.3.1;
- React Router DOM 7.0.1;
- React Hook Form 7.53.2;
- React Input Mask 2.0.4;
- React Modal 3.16.1;
- Axios 1.7.8;
- Yup 1.4.0;
- Day.js 1.11.13;
- Web Vitals 2.1.4.
## /backend
Esse diretório possui um projeto construído com Java 23, Spring Boot na versão 3.4.1.  
Possui os endpoints seguintes endpoints:
![backend-image](/images/backend-image.png)

### Tecnologias

- Java 23;
- Maven;
- PostgreSQL;
- Spring Boot;
- Spring Data JPA;
- Spring Web;
- Spring AMQP;
- Springdoc OpenAPI;
- Hibernate Validator;
- JUnit 5;
- Lombok;
- MapStruct.

## /api
Esse diretório possui um projeto construído com Java 23, Spring Boot na versão 3.4.1.  
Possui os endpoints seguintes endpoints:
![backend-image](/images/api-image.png)

### Tecnologias

- Java 23;
- Maven;
- PostgreSQL;
- Spring Boot;
- Spring Data JPA;
- Spring Web;
- Spring AMQP;
- Springdoc OpenAPI;
- Hibernate Validator;
- JUnit 5;
- Lombok;
- MapStruct.

### Requisitos
São requisitos para rodar esta aplicação:
- Docker na versão 25.0.3 ou superior;
- Java na versão 23 ou superior;
- Maven na versão 3.9.6 ou superior;
- Node na versão 20.14 ou superior

### Para rodar a aplicação
Primeiro, pelo terminal, navegue na pasta raiz.  
Execute o seguinte comando:
``` PowerShell
docker-compose up -d
```
Após isso, navegue para a pasta "\backend", e excute execute este comando:
``` PowerShell
mvn clean install
```
Em seguida, execute este comando:
``` PowerShell
mvn spring-boot:run
```
Após isso, navegue para a pasta "\api", e excute execute este comando:
``` PowerShell
mvn clean install
```
Em seguida, execute este comando:
``` PowerShell
mvn spring-boot:run
```
Após isso, navegue para a pasta "\frontend", e excute execute este comando:
``` PowerShell
npm install
```
Em seguida, execute este comando:
``` PowerShell
npm start
```
Observe que o Docker irá subir um container com duas instâncias do Postgres
E que a aplicação backend subirá na porta 8081, a api irá subir na porta 8080 e, por fim, o frontend estará na porta 3000.  
### Documentação
Todos os endpoints estão documentados no Swagger.   
Incluindo exemplos de requisições e respostas.   
Com as aplicações rodando, em seu navegador, para a api:
```
http://http://localhost:8080/senai-api
```
Para o backend, abra essa url:
```
http://http://localhost:8081/senai-backend
```
### A base de dados
Os comandos DDL são gerados automaticamente pelo programa ao ser iniciando.  
A aplicação também possui um arquivo script de SQL para o preenchimento da base  
com dados iniciais.