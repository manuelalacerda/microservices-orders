# Delivery System — Microserviços
Sistema de delivery baseado em microserviços desenvolvido com Java e Spring Boot.  

## 👥 Integrantes
Manuela de Lacerda Soares — RM 564887

Sofia Siqueira Fontes — RM 563829



## 🏗️ Estrutura do Projeto

* eureka-server (:8761) — Registro e descoberta de serviços.
* order-service (:8080) — Criação de pedidos, controlo de concorrência e assistente de IA.
* payment-service (:8081 e :8082) — Simulação de pagamentos com duas instâncias para testes de tolerância a falhas.
* review-service (:8083) — Processamento assíncrono de avaliações via RabbitMQ.

## 🛠️ Requisitos e Tecnologias

* Java 25 e Spring Boot 4
* RabbitMQ (na porta standard 5672)
* H2 Database   

## 🚀 Como Executar

* Iniciar o RabbitMQ:
bash
```
docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management
```

* Definir a Chave da OpenAI (no terminal):
bash
```
export OPENAI_API_KEY="a-tua-chave-aqui"
```
* Iniciar os Serviços (por ordem, em janelas de terminal separadas):
bash
```
# 1. Servidor Eureka
./gradlew :eureka-server:bootRun

# 2. Instância 1 do Payment Service
./gradlew :payment-service:bootRun --args='--server.port=8081'

# 3. Instância 2 do Payment Service
./gradlew :payment-service:bootRun --args='--server.port=8082'

# 4. Review Service
./gradlew :review-service:bootRun

# 5. Order Service
./gradlew :order-service:bootRun

```
## 📌 Endpoints Principais
* POST /orders (:8080) — Realizar um pedido.   
* POST /reviews (:8080) — Enviar avaliação para a fila do RabbitMQ.   
* POST /assistant (:8080) — Chatbot de IA com o menu.   
* GET /reviews/ranking (:8083) — Consultar o ranking de pratos.   
