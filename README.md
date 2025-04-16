# 🛡️ Desafio de Criptografia - Solução

Esta é a minha implementação do desafio de criptografia proposto pelo Backend BR. Você pode conhecer mais detalhes sobre esse e outros desafios no perfil deles do github [clicando aqui](https://github.com/backend-br/desafios/tree/master).

O desafio de criptografia tem o objetivo de proteger campos sensíveis no banco de dados de forma transparente para a API e as camadas de serviço da aplicação.



## 💡 Interpretando o desafio

O sistema implementa criptografia automática durante a persistência e leitura de dados no banco, garantindo que campos sensíveis como `userDocument` e `creditCardToken` nunca fiquem expostos em texto puro na base de dados.

A criptografia e descriptografia são feitas em tempo de execução, utilizando recursos do Hibernate para interceptar o momento de persistência e leitura dos dados.

## 🔐 Campos Criptografados

- `userDocument`
- `creditCardToken`

Exemplo de como os dados são armazenados no banco:

| id | userDocument     | creditCardToken | value |
|----|------------------|-----------------|-------|
| 1  | MzYxNDA3ODE4MzM= | YWJjMTIz         | 5999  |

## ⚙️ Tecnologias e Ferramentas

- **Java**
- **Spring Boot**
- **Hibernate / JPA**
- **PostgreSQL**
- **Lombok**
- **[Jasypt](https://github.com/jasypt/jasypt)** (Java Simplified Encryption) para criptografia simplificada

## 🛠️ Implementação

### 🔄 Conversão Transparente com Hibernate

A criptografia foi implementada utilizando:

- A biblioteca **Jasypt**, que simplifica a criptografia e descriptografia de strings.
- Atributos anotados com `@Transient`, para que os campos sensíveis não sejam diretamente mapeados para o banco.
- Campos internos persistentes (criptografados) que são manipulados automaticamente via métodos com as anotações:

    - `@PrePersist`: criptografa os dados antes de salvar no banco.
    - `@PostLoad`: descriptografa os dados após carregar do banco.

### 🧩 Exemplo de Estrutura da Entidade

```java
@Transient
private String userDocument;

@Column(name = "user_document")
private String encryptedUserDocument;

@PrePersist
@PreUpdate
private void encryptSensitiveData() {
    this.encryptedUserDocument = jasyptEncryptor.encrypt(userDocument);
}

@PostLoad
private void decryptSensitiveData() {
    this.userDocument = jasyptEncryptor.decrypt(encryptedUserDocument);
}
```

## 📂 Estrutura do Projeto

```
src/
├── controller/
├── service/
     └──...
     └──CryptoService.java
├── domain/
│   └── Operation.java
├── repository/
├── 
```

## 📌 Observações

- A chave de criptografia não deve ser exposta nem comitada. Em produção, utilize variáveis de ambiente ou cofres de segredo como AWS Secrets Manager, HashiCorp Vault, etc.
- Essa abordagem permite um alto grau de desacoplamento e transparência, pois a lógica de criptografia fica encapsulada na própria entidade.

## 👥 Comunidade

Sinta-se a vontade para contribuir com sugestões de melhorias, relatar bugs ou abrir pull requests. 