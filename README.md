# CRM GraphQL API

A Spring Boot application that provides a GraphQL API for managing books, authors, and orders with real-time subscription support.

## Technologies

- **Java 25**
- **Spring Boot 4.0.2**
- **Spring WebFlux** (Reactive web stack)
- **Netflix DGS** (GraphQL framework)
- **GraphQL Extended Scalars** (DateTime support)
- **Lombok**

## Features

- **GraphQL API** with queries, mutations, and subscriptions
- **Real-time subscriptions** via WebSocket
- **Role-based authorization** using custom `@auth` directive
- **Reactive architecture** using Spring WebFlux
- **Custom scalar types** (DateTime)
- **DataLoader pattern** for efficient batch loading

## Project Structure

```
src/main/java/tr/kontas/gql/
├── GqlApplication.java          # Application entry point
├── bus/
│   └── GlobalEventBus.java      # Event bus for subscriptions
├── config/
│   ├── AuthDirective.java       # Authorization directive
│   ├── CustomContext.java       # GraphQL context
│   ├── DirectiveConfig.java     # Directive configuration
│   ├── ReactiveCustomContextBuilder.java
│   └── WebSocketConfig.java     # WebSocket configuration
├── datafetcher/
│   ├── AuthorMutation.java      # Author mutations
│   ├── BookMutation.java        # Book mutations
│   ├── BookQuery.java           # Book queries
│   ├── EventSubscription.java   # Event subscriptions
│   └── OrderMutation.java       # Order mutations
├── loader/
│   └── AuthorDataLoader.java    # DataLoader for authors
├── model/
│   ├── Author.java
│   ├── Book.java
│   ├── DomainEvent.java
│   ├── Event.java
│   ├── EventType.java
│   └── Order.java
├── repository/
│   ├── AuthorRepository.java
│   └── BookRepository.java
└── wiring/
    └── CustomRuntimeWiring.java # Custom scalar configuration
```

## GraphQL Schema

### Types

- **Author**: Represents a book author
- **Book**: Represents a book with author reference
- **Order**: Represents a customer order
- **Event**: Union type for real-time events

### Queries

```graphql
books: [Book!]!
```

### Mutations

```graphql
addBook(userId: String!, title: String!, authorId: String!): Book!
createOrder(userId: String!, amount: Float!): Order!
addAuthor(id: String!, name: String): Author!
```

> All mutations require `ADMIN` role.

### Subscriptions

```graphql
bookAdded(userId: String): Book!
events(types: [EventType!]): Event!
```

## Getting Started

### Prerequisites

- Java 25 or higher
- Maven 3.x

### Running the Application

1. Clone the repository

2. Build the project:
   ```bash
   ./mvnw clean install
   ```

3. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```

4. Access the GraphQL endpoint:
   - HTTP: `http://localhost:8080/graphql`
   - WebSocket: `ws://localhost:8080/graphql`

## Configuration

Application properties are located in `src/main/resources/application.properties`:

```properties
spring.application.name=gql
spring.graphql.websocket.path=/graphql
spring.main.web-application-type=reactive
```

## Example Queries

### Fetch all books

```graphql
query {
  books {
    id
    title
    createdAt
    author {
      id
      name
    }
  }
}
```

### Add a new book

```graphql
mutation {
  addBook(userId: "user1", title: "GraphQL in Action", authorId: "author1") {
    id
    title
    createdAt
  }
}
```

### Subscribe to book events

```graphql
subscription {
  bookAdded(userId: "user1") {
    id
    title
    author {
      name
    }
  }
}
```

## License

This project is licensed under the terms specified in the LICENSE file.
