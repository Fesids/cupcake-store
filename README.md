# CupcakeStore (Spring Boot, Java 21)

Projeto exemplo MVC com funcionalidades básicas:
- Login / Registro (session-based)
- CRUD de Cupcakes
- Carrinho (session)
- Checkout -> gera pedido

## Rodando localmente
1. Tenha Java 21 e Maven instalados.
2. `mvn package` e `java -jar target/cupcakestore-0.0.1-SNAPSHOT.jar` ou `mvn spring-boot:run`.
3. Acesse `http://localhost:8080` e use o console H2 em `/h2-console`.

Banco: H2 arquivo (data/cupcakestore)
