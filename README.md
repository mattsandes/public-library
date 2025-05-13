# 📚 Public Library API

Uma API REST para gerenciamento de uma biblioteca pública fictícia. Este projeto foi criado com o objetivo de treinar habilidades iniciais em **Java** e **Spring Boot**, com foco no desenvolvimento de um CRUD completo para livros.

---

## ✨ Funcionalidades

- ✅ Cadastrar livros  
- 📖 Listar todos os livros  
- 🔍 Buscar livro por ID  
- ✏️ Atualizar livro  
- ❌ Deletar livro  

---

## 📦 Endpoints da API

| Método | Endpoint                     | Descrição                 |
|--------|------------------------------|---------------------------|
| GET    | `/api/library/books`         | Lista todos os livros     |
| GET    | `/api/library/books/{id}`    | Busca um livro por ID     |
| POST   | `/api/library/books`         | Cadastra um novo livro    |
| PUT    | `/api/library/books`         | Atualiza um livro         |
| DELETE | `/api/library/books/{id}`    | Remove um livro por ID    |

---

## 📄 Exemplo de JSON para cadastro

```json
{
  "title": "Dom Quixote",
  "subtitle": "O Cavaleiro da Triste Figura",
  "authors": "Miguel de Cervantes",
  "publisher": "Editora Fictícia",
  "publishDate": "1605-01-16"
}
```

## 🔁 Exemplo de resposta de criação
```json
{
  "bookId": "a1b2c3d4-e5f6-7890-abcd-1234567890ef",
  "title": "Dom Quixote",
  "subtitle": "O Cavaleiro da Triste Figura",
  "authors": "Miguel de Cervantes",
  "publisher": "Editora Fictícia",
  "publishDate": "1605-01-16"
}
```
🛠️ Tecnologias utilizadas

- Java 21

- Spring Boot

- Spring Data JPA

- PostgreSQL

- Gradle

▶️ Como executar

1. Clone o repositório:

```json
git clone https://github.com/mattsandes/public-library.git
```

2. Configure o PostgreSQL com nome de banco, usuário e senha compatíveis com seu `application.yml` ou `.properties`.

3. Execute a aplicação com:
```json
./gradlew bootRun
```

A API estará disponível em:
📍 `http://localhost:8080/api/library/books`

🧪 Testando a API

Utilize ferramentas como:

- Postman

- Insomnia

- Terminal com curl

🤝 Contribuindo

Contribuições são bem-vindas!

1. Faça um fork

2. Crie uma branch: `git checkout -b feature/nova-feature`

3. Commit suas alterações: `git commit -m 'feat: nova feature'`

4. Envie para seu fork: `git push origin feature/nova-feature`

5. Abra um Pull Request
