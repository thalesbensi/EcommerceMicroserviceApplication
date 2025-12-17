# 📘 Documentação do Projeto

## 🧩 Visão Geral

Este projeto implementa uma **arquitetura de microserviços** utilizando **Spring Boot**, **Spring Cloud**, **Eureka Server**, **RabbitMQ** e **Docker**, com o objetivo de simular um cenário real de mercado envolvendo **controle de estoque** e **processamento de vendas**.

> ⚠️ **Importante:** Este projeto **ainda não possui autenticação/autorização com JWT**. Todas as APIs estão abertas para fins de estudo e desenvolvimento.

---

## 🏗️ Arquitetura Geral

O sistema é composto pelos seguintes componentes:

* **Eureka Server** – Service Discovery
* **API Gateway** – Ponto único de entrada
* **Estoque Service** – Gerenciamento de produtos e estoque
* **Vendas Service** – Processamento de vendas
* **RabbitMQ** – Comunicação assíncrona
* **Bancos de Dados independentes** – Um por microserviço

```
Client
  ↓
API Gateway
  ↓
Eureka Server (Service Discovery)
  ↓
Microserviços (Estoque / Vendas)
  ↕
RabbitMQ
```

---

## 🔎 Service Discovery – Eureka Server

### Responsabilidade

* Registrar os microserviços
* Permitir descoberta dinâmica de serviços
* Evitar dependência de endereços fixos

### Porta

```
8761
```

### Acesso

```
http://localhost:8761
```

---

## 🌐 API Gateway

### Responsabilidade

* Centralizar o acesso às APIs
* Roteamento baseado em Service Discovery
* Futuro ponto de validação de JWT

### Porta

```
8080
```

### Rotas Expostas

| Rota            | Destino         |
| -------------   | --------------- |
| `/stock-api/**` | stock-service   |
| `/sales-api/**` | sales-service   |

> O Gateway utiliza `lb://service-name`, integrando diretamente com o Eureka.

---

## 📦 Microserviço de Estoque

### Responsabilidade

* Cadastro de produtos
* Consulta de produtos
* Validação de disponibilidade
* Atualização de estoque após venda

### Banco de Dados

* Independente (MySQL)

### Porta Interna

```
8080
```

### Principais Endpoints

| Método | Endpoint            | Descrição       |
| ------ | ------------------- | --------------- |
| POST   | `/products`         | Criar produto   |
| GET    | `/products`         | Listar produtos |
| GET    | `/products/{id}`    | Buscar produto  |

---

## 🛒 Microserviço de Vendas

### Responsabilidade

* Criar vendas
* Consultar vendas
* Validar estoque antes da venda
* Publicar evento de venda no RabbitMQ

### Banco de Dados

* Independente (MySQL)

### Porta Interna

```
8080
```

### Principais Endpoints

| Método | Endpoint       | Descrição     |
| ------ | -------------- | ------------- |
| POST   | `/sales`       | Criar venda   |
| GET    | `/sales`       | Listar vendas |
| GET    | `/sales/{id}`  | Buscar venda  |

---

## 🔄 Comunicação entre Serviços

### Comunicação Síncrona (HTTP)

* **Vendas → Estoque**
* Validação de disponibilidade antes de registrar a venda

### Comunicação Assíncrona (RabbitMQ)

* **Vendas publica evento de venda realizada**
* **Estoque consome o evento e atualiza o estoque**

---

## 🐰 RabbitMQ

### Responsabilidade

* Garantir desacoplamento entre serviços
* Processamento assíncrono

### Acesso ao Console

```
https://www.cloudamqp.com
```
## 🐳 Docker e Orquestração

O projeto utiliza **Docker Compose** para subir todo o ecossistema:

* Eureka Server
* API Gateway
* Estoque Service
* Vendas Service
* Dois bancos MySQL

### Subir o ambiente

```bash
docker-compose up -d --build
```

---

## 🗃️ Bancos de Dados

### Estratégia

* **Database per Service**
* Nenhuma chave estrangeira entre serviços
* Relacionamento apenas lógico via IDs

### Benefícios

* Baixo acoplamento
* Escalabilidade
* Independência de deploy

---

## 🔐 Autenticação e Segurança (Planejado)

⚠️ **Ainda não implementado**

Planejamento futuro:

* Autenticação com JWT
* Login centralizado
* Validação do token no API Gateway
* Microserviços protegidos indiretamente

---

## 🚀 Próximos Passos

* Implementar autenticação com JWT
* Adicionar Resilience4j (Circuit Breaker)
* Centralizar Swagger no Gateway
* Monitoramento com Spring Actuator

---

## 📌 Considerações Finais

Este projeto foi desenvolvido com foco em **boas práticas** e **cenários reais de mercado**, servindo como base sólida para estudos avançados em microserviços com Spring.

---

✍️ *Projeto educacional desenvolvido para fins de aprendizado e evolução técnica.*
