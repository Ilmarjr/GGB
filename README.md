# GGB API 
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)


## Métodos
Requisições para a API devem seguir os padrões:
| Método | Descrição |
|---|---|
| `GET` | Retorna informações de um ou mais registros. |
| `POST` | Utilizado para criar um novo registro. |
| `PUT` | Atualiza dados de um registro ou altera sua situação. |
| `DELETE` | Remove um registro do sistema. |

## Respostas

| Código | Descrição |
|---|---|
| `200` | Requisição executada com sucesso (success).|
| `400` | Erros de validação ou os campos informados não existem no sistema.|
| `401` | Dados de acesso inválidos.|
| `403` | Falta de validação|
| `404` | Registro pesquisado não encontrado (Not found).|
| `405` | Método não implementado.|
| `410` | Registro pesquisado foi apagado do sistema e não esta mais disponível.|
| `422` | Dados informados estão fora do escopo definido para o campo.|
| `429` | Número máximo de requisições atingido. (*aguarde alguns segundos e tente novamente*)|

# Group Autenticação - OAuth

Nossa API utiliza [OAuth2](https://auth0.com/pt) como forma de autenticação/autorização.
## Documentação da API

#### Retorna todas as noticias

```http
 GET /api/v1/newsEntity
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `api_key` | `string` | Chave da API |

#### Retorna um item

```http
  GET /api/v1/newsEntity/${id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `string` | ID do item que você quer |

## Solicitando Token de Acesso

```http
  POST /auth/signin
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `api_key`      | `string` | Chave da API |

**Json deve informar:**
| Chave | Valor |
| :-- | :---- |
| `username` | `String` |
| `password` | `String` |

```http
  POST /refresh/{username}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `username`      | `string` | Username para refresh do token |

**Json deve informar:**
#### em desenvolvimento

## Enviando uma notícia

```http
  POST /api/v1/news
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `api_key`      | `string` | Chave da API |

**Json deve informar:**
| Chave | Valor |
| :-- | :---- |
| `title*` | `String` |
| `description*` | `String` |
| `tag` | `[String]` |
| `mainPicture` | `MULTI_PART_FORM_DATA` |

## Deletando uma noticia

```http
  DELETE /api/v1/news/{id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `string` | Id da noticia a ser deletada |

# Rota de Contatos
## Retorna todos os contatos

```http
  GET /api/v1/contact
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `api_key`      | `string` | Chave da API |

## Retorna o contato do id

```http
  GET /api/v1/contact/{id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `string` | ID do contato que você quer|

## Post do contato

```http
  POST /api/v1/contact
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `api_key`      | `string` | Chave da API|

**Json deve informar:**
| Chave | Valor |
| :-- | :---- |
| `email` | `String` |
| `valid` | `bit` |


## Autores

- [@ShinyRonald](https://github.com/ShinyRonald)
- [@Ilmarjr ](https://github.com/Ilmarjr)
- [@09Uno](https://github.com/09Uno)
