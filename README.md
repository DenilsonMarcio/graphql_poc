# GraphQL POC (Proof of Concept)

Este projeto é uma prova de conceito (POC) para uma API GraphQL utilizando Spring Boot. Ele demonstra como implementar operações CRUD (Create, Read, Update, Delete) para uma entidade simples chamada `Book` (Livro), usando o Spring for GraphQL.

## Tecnologias Utilizadas
- **Java 21+**
- **Spring Boot**
- **Spring for GraphQL**
- **Maven**

## Estrutura do Projeto
```
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/graphql_poc/
│   │   │       ├── GraphqlPocApplication.java
│   │   │       ├── controller/BookController.java
│   │   │       ├── model/Book.java
│   │   │       ├── repository/BookRepository.java
│   │   │       └── service/BookService.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── graphql/schema.graphqls
│   └── test/
│       └── java/com/example/graphql_poc/
│           ├── BookServiceTest.java
│           └── GraphqlPocApplicationTests.java
├── pom.xml
└── README.md
```

## Descrição dos Componentes

### 1. Model
- **Book.java**: Representa a entidade Livro, com campos como id, título, autor e ano de publicação.

### 2. Repository
- **BookRepository.java**: Interface para acesso aos dados dos livros (pode ser implementada com JPA ou em memória).

### 3. Service
- **BookService.java**: Camada de serviço que encapsula a lógica de negócio para manipulação dos livros.

### 4. Controller
- **BookController.java**: Expõe os endpoints GraphQL (queries e mutations) para interação com os livros.

### 5. Schema GraphQL
- **schema.graphqls**: Define o schema GraphQL, incluindo tipos, queries e mutations disponíveis.

### 6. Testes
- **BookServiceTest.java**: Testes unitários para a camada de serviço.
- **GraphqlPocApplicationTests.java**: Testes de integração da aplicação.

## Exemplos de Operações GraphQL

### Query: Listar todos os livros
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

### Query: Buscar livro por ID
```graphql
query {
  findBookById(id: 1) {
    id
    title
    author
    publicationYear
  }
}
```

### Mutation: Criar um novo livro
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

### Mutation: Atualizar um livro
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

### Mutation: Deletar um livro
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

---

**Autor:** Seu Nome
**Licença:** MIT
