# cryptotracker

CryptoTracker é um projeto de backend desenvolvido em **Java 17 + Spring Boot 3**, com autenticação via **JWT**, banco de dados em memória **H2**, e documentação automática via **Swagger UI**.

O projeto contém 6 CRUDs completos:

* Usuários
* Moedas
* Carteiras
* CarteiraMoedas
* Transações
* Transferências

---

# Requisitos para rodar

Antes de tudo, mude no IntelliJ a versão do Java para:

✔ **Java 17**

---

# Como rodar o projeto

1. Vá até:

```
src/main/java/com/cryptotracker/CryptoTrackerApplication.java
```

4. Clique no botão Run

O backend subirá em:

[http://localhost:8080](http://localhost:8080)

---

# Acessar o Swagger

Assim que o projeto estiver rodando, abra:

**[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)**

ou

**[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)**


---

# Criando usuários para testar

### Criar ADMIN

Endpoint:

```
POST /auth/register-admin
```

Body:

```json
{
  "nome": "Admin Teste",
  "email": "admin@teste.com",
  "senha": "admin123",
  "roles": ["ROLE_ADMIN"]
}
```

---

### Criar user normal

Endpoint:

```
POST /auth/register
```

Body:

```json
{
  "nome": "Usuario Teste",
  "email": "user@teste.com",
  "senha": "user123"
}
```

---

# Como autenticar no Swagger

1. Depois de criar um usuário, vá em:

```
POST /auth/login
```

E envie o JSON:

```json
{
  "email": "admin@teste.com",
  "senha": "admin123"
}
```

2. O Swagger retornará:

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

3. Clique no botão **Authorize** (cadeadinho no topo)

4. Cole o token assim:

```
Bearer SEU_TOKEN_AQUI
```

5. Clique em **Authorize**
6. Pronto — todos os endpoints ficarão com o **cadeado fechado** (isso é normal).

---

# Acesso ao banco H2

Se quiser ver as tabelas:

**[http://localhost:8080/h2-console](http://localhost:8080/h2-console)**

Configurações:

* JDBC URL: `jdbc:h2:mem:cryptotracker`
* User: `sa`
* Password: vazio
