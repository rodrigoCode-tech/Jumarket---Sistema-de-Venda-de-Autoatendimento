# Jumarket - Sistema de Venda de Autoatendimento

O Jumarket é uma aplicação de venda de autoatendimento desenvolvida em Kotlin/Java, que permite que os clientes façam suas compras de forma rápida e conveniente. A aplicação oferece funcionalidades para cadastro de categorias de produtos, cadastro de produtos, criação de carrinho de compras e finalização da compra com diferentes formas de pagamento.

## 🚀 Começando



### 📋 Pré-requisitos

 - Kotlin/Java
 - Maven
 - Docker
 - JUnit (para testes)
 - MySQL
 - JPA
 - Flyway
### 🔧 Instalação

 - Clone o repositório do Jumarket para sua máquina local: git clone 
 - https://github.com/seu-usuario/jumarket.git
 - Navegue até o diretório do projeto: cd jumarket
 - Compile o projeto usando o Maven: mvn clean package
 - Inicie o banco de dados MySQL usando Docker: docker-compose up -d
 - Execute a migração do banco de dados usando o Flyway: mvn flyway:migrate
 - Agora você pode executar a aplicação: java -jar target/jumarket-1.0.0.jar

## ⚙️  Como utilizar o Jumarket
  -  Passo 1 : 
   Cria-se uma categoria
   Método POST
   http://localhost:8080/categorias

   - Passo 2:
     Cria-se um produto
     metodo POST
     http://localhost:8080/produtos

  - Passo 3 : 
    Cria-se um Cliente
    Método POST
    http://localhost:8080/clientes

  - Passo 4 : 
    Cria-se um carrinho
    Método POST
    http://localhost:8080/carrinhos/abrir/cliente/1

  - Passo 5 : 
    Adiciona um Produto no carrinho
    Metodo POST
    http://localhost:8080/carrinhos/1/produtos

  - Passo 6 : 
    Finalizar a venda
    Metodo POST
    http://localhost:8080/vendas/finalizar/1/forma-pagamento/PIX
  
