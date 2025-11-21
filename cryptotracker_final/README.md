# CryptoTracker (final)
Spring Boot + Maven project with H2, JWT, Swagger.
Admin default: admin@cryptotracker.com / admin123

Run:
mvn clean package
mvn spring-boot:run

H2 console: http://localhost:8080/h2-console
Swagger: http://localhost:8080/swagger-ui/index.html

Endpoints highlights:
POST /auth/register
POST /auth/register-admin
POST /auth/login
CRUD /moedas (admin)
CRUD /carteiras
CRUD /carteira-moedas
CRUD /transacoes
POST /transferencias
