<div align="center" id="top">  
    <img src="https://www.linkimagem.com/imagem-projeto-energyquest.jpg" alt="EnergyQuest" width="200" />  
    <h1 align="center">EnergyQuest</h1>  
</div>  
<p align="center">  
    <img alt="GitHub top language" src="https://img.shields.io/github/languages/top/CharCarvalho/GlobalSolution4?color=56BEB8">  
    <img alt="GitHub language count" src="https://img.shields.io/github/languages/count/CharCarvalho/GlobalSolution4?color=56BEB8">  
    <img alt="Repository size" src="https://img.shields.io/github/repo-size/CharCarvalho/GlobalSolution4?color=56BEB8">  
</p>  
<p align="center">  
    <a href="https://github.com/CharCarvalho" target="_blank">Autor</a>  
</p>

# 📝 Sobre  
**EnergyQuest** é uma API desenvolvida em Java utilizando o framework Spring Boot. Este projeto gerencia perguntas e respostas relacionadas a dados de usuários e questões energéticas, integrando funcionalidades de autenticação e armazenamento seguro.

Link para o Deploy: [EnergyQuest API](#)

# ✨ Funcionalidades
- ✅ **Endpoints RESTful** para CRUD de perguntas e respostas.
- ✅ **Gerenciamento de usuários** com campos seguros, incluindo senhas.
- ✅ Relacionamentos definidos entre perguntas e respostas.
- ✅ Integração com banco de dados relacional para persistência dos dados.
- ✅ Suporte para inicialização e configuração de ambiente usando variáveis.

# 🚀 Tecnologias
- Java 21
- Spring Boot 3
- Jakarta Persistence API (JPA)
- Banco de Dados Relacional
- Lombok para simplificar o código de boilerplate
- Maven para gerenciamento de dependências

# ⚙️ Configuração

### Pré-requisitos
- Java 17 ou superior
- Maven
- Banco de dados relacional (configurado com variáveis de ambiente)

### Variáveis de Ambiente
O projeto utiliza variáveis de ambiente para configuração de credenciais e URL do banco de dados. Certifique-se de definir as seguintes variáveis:

```text
SPRING_DATASOURCE_URL
SPRING_DATASOURCE_USERNAME
SPRING_DATASOURCE_PASSWORD
```

### Como rodar localmente

1. Clone o repositório:

```bash
git clone https://github.com/CharCarvalho/GlobalSolution4.git
```

2. Navegue até o diretório do projeto:

```bash
cd GlobalSolution4
```

3. Compile o projeto com Maven:

```bash
mvn clean install
```

4. Execute a aplicação:

```bash
mvn spring-boot:run
```

# 📁 Estrutura do Projeto
A estrutura do projeto é organizada da seguinte maneira:

```plaintext
src/
 ├── main/
 │    ├── java/
 │    │    ├── com/globalsolution2/fiap/
 │    │    │    ├── assembler/
 │    │    │    ├── controller/
 │    │    │    ├── model/
 │    │    │    ├── repository/
 │    │    │    ├── security/
 │    │    │    ├── service/
 │    │    │    └── GlobalSolution2Application.java
 │    │    └── resources/
 │    ├── resources/
 ├── test/
```

# 🔑 Rota de Autenticação e Segurança (Spring Security)

### Endpoints de Autenticação

- **Login**: `/auth/login`
    - **Método**: `POST`
    - **Descrição**: Realiza a autenticação do usuário e retorna um token JWT.
    - **Parâmetros**:
      - `login` (String): Nome de usuário.
      - `password` (String): Senha do usuário.
    - **Resposta**:
      - `200 OK`: Token JWT para acesso às rotas protegidas.
      - `400 Bad Request`: Caso as credenciais estejam incorretas.

**Exemplo de corpo da requisição:**
```json
{
  "login": "usuario1",
  "password": "senhaSegura123"
}
```

- **Registro**: `/auth/register`
    - **Método**: `POST`
    - **Descrição**: Registra um novo usuário no sistema.
    - **Parâmetros**:
      - `nmUsuario` (String): Nome completo do usuário.
      - `login` (String): Nome de login do usuário.
      - `password` (String): Senha do usuário (será criptografada).
      - `nmEmail` (String): Email do usuário.
      - `role` (Enum): Função do usuário (`ADMIN` ou `USER`).
    - **Resposta**:
      - `200 OK`: Usuário registrado com sucesso.
      - `400 Bad Request`: Caso o login já exista.

**Exemplo de corpo da requisição:**
```json
{
  "nmUsuario": "João Silva",
  "login": "joao.silva",
  "password": "senhaSegura123",
  "nmEmail": "joao.silva@email.com",
  "role": "USER"
}
```

### Endpoints de Usuário

- **Criar Usuários**: `/usuarios`
    - **Método**: `POST`
    - **Descrição**: Cria um ou mais usuários no sistema.
    - **Parâmetros**:
      - Um ou mais objetos `UsuarioModel` no corpo da requisição.
    - **Resposta**:
      - `201 Created`: Usuários criados com sucesso.
      - `400 Bad Request`: Caso haja erro na validação dos dados.

- **Buscar Todos os Usuários**: `/usuarios`
    - **Método**: `GET`
    - **Descrição**: Retorna a lista de todos os usuários cadastrados no sistema.
    - **Resposta**:
      - `200 OK`: Lista de usuários.

- **Buscar Usuário por ID**: `/usuarios/{id}`
    - **Método**: `GET`
    - **Descrição**: Retorna as informações de um usuário específico pelo seu ID.
    - **Parâmetros**:
      - `id` (Long): ID do usuário.
    - **Resposta**:
      - `200 OK`: Detalhes do usuário.
      - `404 Not Found`: Caso o usuário não seja encontrado.

- **Atualizar Usuário**: `/usuarios/{id}`
    - **Método**: `PUT`
    - **Descrição**: Atualiza as informações de um usuário existente.
    - **Parâmetros**:
      - `id` (Long): ID do usuário a ser atualizado.
      - Dados do usuário no corpo da requisição.
    - **Resposta**:
      - `200 OK`: Usuário atualizado com sucesso.
      - `404 Not Found`: Caso o usuário não seja encontrado.

- **Deletar Usuário**: `/usuarios/{id}`
    - **Método**: `DELETE`
    - **Descrição**: Deleta um usuário do sistema pelo seu ID.
    - **Parâmetros**:
      - `id` (Long): ID do usuário a ser deletado.
    - **Resposta**:
      - `204 No Content`: Usuário deletado com sucesso.
      - `404 Not Found`: Caso o usuário não seja encontrado.
      

### Endpoints de Perguntas

- **Criar Perguntas**: `/perguntas`
    - **Método**: `POST`
    - **Descrição**: Cria uma ou mais perguntas no sistema.
    - **Parâmetros**:
      - Um ou mais objetos `PerguntaModel` no corpo da requisição.
    - **Resposta**:
      - `201 Created`: Perguntas criadas com sucesso.
      - `400 Bad Request`: Caso haja erro na validação dos dados.

- **Buscar Todas as Perguntas**: `/perguntas`
    - **Método**: `GET`
    - **Descrição**: Retorna a lista de todas as perguntas cadastradas no sistema.
    - **Resposta**:
      - `200 OK`: Lista de perguntas.

- **Buscar Pergunta por ID**: `/perguntas/{id}`
    - **Método**: `GET`
    - **Descrição**: Retorna as informações de uma pergunta específica pelo seu ID.
    - **Parâmetros**:
      - `id` (Long): ID da pergunta.
    - **Resposta**:
      - `200 OK`: Detalhes da pergunta.
      - `404 Not Found`: Caso a pergunta não seja encontrada.

- **Atualizar Pergunta**: `/perguntas/{id}`
    - **Método**: `PUT`
    - **Descrição**: Atualiza as informações de uma pergunta existente.
    - **Parâmetros**:
      - `id` (Long): ID da pergunta a ser atualizada.
      - Dados da pergunta no corpo da requisição.
    - **Resposta**:
      - `200 OK`: Pergunta atualizada com sucesso.
      - `404 Not Found`: Caso a pergunta não seja encontrada.

- **Deletar Pergunta**: `/perguntas/{id}`
    - **Método**: `DELETE`
    - **Descrição**: Deleta uma pergunta do sistema pelo seu ID.
    - **Parâmetros**:
      - `id` (Long): ID da pergunta a ser deletada.
    - **Resposta**:
      - `204 No Content`: Pergunta deletada com sucesso.
      - `404 Not Found`: Caso a pergunta não seja encontrada.

### Endpoints de Respostas

- **Criar Respostas**: `/respostas`
    - **Método**: `POST`
    - **Descrição**: Cria uma ou mais respostas no sistema.
    - **Parâmetros**:
      - Um ou mais objetos `RespostaModel` no corpo da requisição.
    - **Resposta**:
      - `201 Created`: Respostas criadas com sucesso.
      - `400 Bad Request`: Caso haja erro na validação dos dados.

**Exemplo de corpo da requisição:**
```json
[
  {
    "idPergunta": 1,
    "txResposta": "A energia solar é uma fonte renovável.",
    "isCorreta": true
  },
  {
    "idPergunta": 2,
    "txResposta": "A energia eólica é gerada por ventos.",
    "isCorreta": true
  }
]
```

- **Buscar Todas as Respostas**: `/respostas`
    - **Método**: `GET`
    - **Descrição**: Retorna a lista de todas as respostas cadastradas no sistema.
    - **Resposta**:
      - `200 OK`: Lista de respostas.

- **Buscar Resposta por ID**: `/respostas/{id}`
    - **Método**: `GET`
    - **Descrição**: Retorna as informações de uma resposta específica pelo seu ID.
    - **Parâmetros**:
      - `id` (Long): ID da resposta.
    - **Resposta**:
      - `200 OK`: Detalhes da resposta.
      - `404 Not Found`: Caso a resposta não seja encontrada.

- **Atualizar Resposta**: `/respostas/{id}`
    - **Método**: `PUT`
    - **Descrição**: Atualiza as informações de uma resposta existente.
    - **Parâmetros**:
      - `id` (Long): ID da resposta a ser atualizada.
      - Dados da resposta no corpo da requisição.
    - **Resposta**:
      - `200 OK`: Resposta atualizada com sucesso.
      - `404 Not Found`: Caso a resposta não seja encontrada.

**Exemplo de corpo da requisição:**
```json
{
  "idPergunta": 1,
  "txResposta": "A energia solar é uma fonte sustentável.",
  "isCorreta": true
}
```

- **Deletar Resposta**: `/respostas/{id}`
    - **Método**: `DELETE`
    - **Descrição**: Deleta uma resposta do sistema pelo seu ID.
    - **Parâmetros**:
      - `id` (Long): ID da resposta a ser deletada.
    - **Resposta**:
      - `204 No Content`: Resposta deletada com sucesso.
      - `404 Not Found`: Caso a resposta não seja encontrada.

---

## 🛠️ Como o Spring Security é utilizado

O Spring Security é configurado para garantir a segurança dos endpoints da aplicação, utilizando autenticação baseada em JWT (JSON Web Token).

- **Autenticação**: A autenticação é realizada no endpoint `/auth/login` com a verificação das credenciais do usuário. Se as credenciais forem válidas, um token JWT é gerado e retornado.
- **Autorização**: O acesso aos endpoints protegidos é restrito com base no papel do usuário (`ROLE_ADMIN`, `ROLE_USER`). Apenas usuários autenticados com um token válido têm acesso a esses endpoints.
- **Papéis de Usuário**:
  - `ADMIN`: Usuários com este papel têm permissões mais amplas.
  - `USER`: Usuários com este papel têm permissões limitadas.
