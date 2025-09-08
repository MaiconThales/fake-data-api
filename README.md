# 🚀 API Performance Tester

Um projeto para testar e medir o desempenho de APIs utilizando dados fictícios, permitindo análise detalhada de tempo de resposta e performance em diferentes cenários.

## 📋 Sobre o Projeto

Este projeto foi desenvolvido com o objetivo de fornecer uma ferramenta simples e eficaz para testar o desempenho de APIs através da geração de dados falsos. Ideal para testes de carga, análise de performance e validação de endpoints em ambientes de desenvolvimento.

### ✨ Características Principais

- 🎯 **Testes de Performance**: Medição precisa do tempo de resposta das APIs
- 📊 **Dados Fictícios**: Geração automática de dados para testes
- 🔍 **Análise Detalhada**: Resultados individuais e tempo total de execução
- 🛠️ **Extensível**: Arquitetura preparada para novos endpoints

## 🛣️ Endpoints Disponíveis

### `POST /fake-data/send`

Gera e envia dados fictícios para teste de performance, retornando métricas detalhadas sobre o tempo de execução.

#### 📥 Parâmetros de Entrada

```json
{
  "type": "@sentence",
  "plate": "@plate",
  "port": "@number",
  "date": "@dateTime",
  "parameters": [
    {
      "idItineraries": "@number",
      "description": "@sentence"
    },
    {
      "idItineraries": "@number",
      "description": "@sentence"
    }
  ]
}
```

#### 📤 Resposta

```json
{
  "path": "/fake-data/send",
  "totalTime": 1250,
  "amountOfDataSent": 2,
  "results": [
    {
      "callTime": 150
    },
    {
      "callTime": 200
    },
    {
      "callTime": 180
    }
  ]
}
```

**Estrutura da Resposta:**

| Campo | Tipo | Descrição                                       |
|-------|------|-------------------------------------------------|
| `path` | String | Endpoint testado                                |
| `totalTime` | Long | Tempo total de execução em milissegundos        |
| `amountOfDataSent` | Long | Quantidade de dados enviados                    |
| `results` | Array | Lista com resultados individuais de cada chamada |
| `results[].callTime` | Long | Tempo de execução individual em milissegundos   |

## 🚀 Como Executar

### Pré-requisitos

- Java 21 ou superior
- Maven 3.6+
- IDE de sua preferência

### Instalação

1. Clone o repositório:
```bash
git clone https://github.com/MaiconThales/fake-data-api.git
cd fake-data-api
```

2. Instale as dependências:
```bash
mvn clean install
```

3. Execute a aplicação:
```bash
mvn spring-boot:run
```

A API estará disponível em `http://localhost:8081`

## 📖 Exemplo de Uso

### Usando com Postman

1. Crie uma nova requisição POST
2. URL: `http://localhost:8081/fake-data/send`
3. Headers: `Content-Type: application/json`
4. Body (raw JSON):
```json
{
  "path": "/fake-data/send",
  "totalTime": 1250,
  "amountOfDataSent": 4,
  "results": [
    {
      "callTime": 150
    },
    {
      "callTime": 200
    },
    {
      "callTime": 180
    }
  ]
}
```

## 🏗️ Arquitetura

```
src/
├── main/
│   ├── java/
│   │   └── com/example/apitest/
│   │       ├── config/        # Configurações da aplicação
│   │       ├── controller/    # Controllers REST
│   │       ├── dto/           # Data Transfer Objects
│   │       ├── exception/     # Tratamento de exceções
│   │       ├── service/       # Lógica de negócio
│   │       └── util/          # Utilitários
│   └── resources/
│       └── application.yml    # Configurações
└── test/                      # Testes unitários e integração
```

## 🤝 Como Contribuir

Contribuições são sempre bem-vindas! Para contribuir com o projeto:

### 1. Fork do Projeto

```bash
git fork https://github.com/seu-usuario/api-performance-tester.git
```

### 2. Crie uma Branch para sua Feature

```bash
git checkout -b feature/nova-funcionalidade
```

### 3. Commit suas Mudanças

```bash
git commit -m "feat: adiciona nova funcionalidade X"
```

**Padrão de Commits:**
- `feat:` para novas funcionalidades
- `fix:` para correções de bugs
- `docs:` para documentação
- `test:` para testes
- `refactor:` para refatoração de código

### 4. Push para a Branch

```bash
git push origin feature/nova-funcionalidade
```

### 5. Abra um Pull Request

Descreva suas mudanças detalhadamente e inclua:
- Descrição da funcionalidade/correção
- Testes realizados
- Screenshots (se aplicável)

## 📝 Licença

Este projeto está licenciado sob a [MIT License](LICENSE).

## 📞 Contato

- **Autor**: Maicon Thales Silva Gomes
- **Email**: maiconthales34@hotmail.com
- **LinkedIn**: [Maicon Thales](https://www.linkedin.com/in/maicon-thales-555996110/)
- **GitHub**: [@MaiconThales](https://github.com/MaiconThales/fake-data-api)

---

⭐ Se este projeto foi útil para você, considere dar uma estrela no repositório!