# GraphQL POC — Clean Architecture (Hexagonal) e Clean Code

Este repositório é uma prova de conceito (POC) de uma API GraphQL construída com Spring Boot.
O projeto foi refatorado para seguir princípios de Clean Code e a Arquitetura Hexagonal (Ports & Adapters).

Versão do Java requerida: **Java 21** (conforme `pom.xml`).

Visão geral
---------
- Linguagem: Java 21
- Framework: Spring Boot
- API: Spring for GraphQL
- Build: Maven

Objetivo
--------
Demonstrar uma aplicação pequena com:
- Separação clara entre domínio e infraestrutura
- Portas (interfaces) que definem contratos de entrada/saída
- Adaptadores (adapters) que implementam as portas (ex.: adaptação para JPA)
- Código testável e com dependências invertidas via injeção de dependência

Estrutura do projeto (principal)
--------------------------------
```
src/main/java/
└─ com.example.graphql_poc
   ├─ GraphqlPocApplication.java           # Spring Boot bootstrap
   ├─ controller/                          # Entrada (GraphQL controllers / adapters in-bound)
   │  └─ BookController.java
   ├─ domain/
   │  ├─ model/                            # Modelo de domínio puro (sem JPA)
   │  │  └─ Book.java
   │  └─ port/                             # Ports (interfaces) usadas pelo domínio
   │     └─ BookRepositoryPort.java
   ├─ adapter/
   │  └─ out/persistence/                  # Adapters que conectam o domínio à infraestrutura
   │     ├─ BookRepositoryAdapter.java     # Implementação da porta usando Spring Data JPA
   │     └─ BookEntityMapper.java          # Converte entre entidade JPA e domínio
   ├─ model/                               # Entidades JPA (infraestrutura)
   │  └─ Book.java                         # Entidade persistível (Jakarta JPA)
   ├─ repository/                          # Spring Data repositories
   │  └─ BookRepository.java
   └─ service/
      └─ BookService.java                  # Caso de uso / camada de aplicação

src/main/resources/graphql/schema.graphqls  # Schema GraphQL (queries & mutations)
```

Princípios e decisões de projeto
--------------------------------
- Domain separado da persistência: `domain.model.Book` (modelo de domínio) vs `model.Book` (entidade JPA).
- Portas definem o contrato: `BookRepositoryPort` descreve o que o domínio espera de um repositório.
- Adaptadores concretos implementam portas: `BookRepositoryAdapter` converte para/da entidade JPA e delega ao `BookRepository` (Spring Data).
- Injeção por construtor e Imutabilidade em inputs GraphQL: `BookController` usa um `record` para representar `BookInput`.
- Testes focados na camada de serviço usando mocks da porta (`BookRepositoryPort`).

Como rodar (local)
------------------
Pré-requisitos:
- Java 21+
- Maven (o wrapper `mvnw`/`mvnw.cmd` já está incluído)

1. Compilar e rodar os testes:

```cmd
mvnw.cmd clean test
```

2. Rodar a aplicação:

```cmd
mvnw.cmd spring-boot:run
```

3. Acessar o GraphQL playground / IDE (depende da configuração):
- Geralmente está disponível em: `http://localhost:8080/graphiql` ou `http://localhost:8080/playground`.

Exemplos de operações GraphQL
-----------------------------
Query: listar todos os livros
```graphql
query {
  findAllBooks {
    id
    title
    author
    publicationYear
  }
}
```

Mutation: criar um livro
```graphql
mutation {
  createBook(book: {title: "Dom Casmurro", author: "Machado de Assis", publicationYear: 1899}) {
    id
    title
    author
    publicationYear
  }
}
```

Mutation: atualizar um livro
```graphql
mutation {
  updateBook(id: 1, book: {title: "Memórias Póstumas", author: "Machado de Assis", publicationYear: 1881}) {
    id
    title
    author
    publicationYear
  }
}
```

Mutation: deletar um livro
```graphql
mutation {
  deleteBook(id: 1)
}
```

## Como Executar o Projeto

1. **Pré-requisitos:**
    - Java 21 ou superior
    - Maven

2. **Instalar dependências e compilar:**
   ```cmd
   mvnw clean install
   ```

3. **Executar a aplicação:**
   ```cmd
   mvnw spring-boot:run
   ```

4. **Acessar o playground GraphQL:**
    - Normalmente disponível em `http://localhost:8080/graphiql` ou conforme configurado.

## Personalização
- O schema GraphQL pode ser ajustado em `src/main/resources/graphql/schema.graphqls`.
- Para adicionar novos campos ou entidades, crie novos modelos, repositórios, serviços e atualize o controller e o schema.

## Referências
- [Spring for GraphQL Documentation](https://docs.spring.io/spring-graphql/docs/current/reference/html/)
- [GraphQL Foundation](https://graphql.org/)

Testes
------
- Testes unitários da camada de serviço estão em `src/test/java/.../BookServiceTest.java`.
- Os testes usam Mockito para simular a `BookRepositoryPort`.

Próximos passos / melhorias possíveis
-----------------------------------
- Adicionar DTOs e validação explícita para inputs (ex.: `javax.validation`).
- Cobertura de testes mais ampla (controller/graphql integration tests).
- Implementar logs estruturados e tratamento centralizado de exceções.
- Separar módulos (ex.: `application`, `domain`, `infrastructure`) em multi-módulos para projetos maiores.

Licença e Autor
---------------
- Autor: Seu Nome
- Licença: MIT
