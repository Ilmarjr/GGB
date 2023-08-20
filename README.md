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

Nossa API utiliza [OAuth0](https://auth0.com/pt) como forma de autenticação/autorização.

## Solicitando tokens de acesso [/auth/signin]
username:  `ggbAdmin`  
password:  `ggbadmin` 

## Refresh Token [/refresh/{username}]

## Notícias [/api/v1/news]
### Listar todos [GET {?id,title,date,tags,mainPicture,description}]
### Buscar pelo ID [GET/{id} {?id,title,date,tags,mainPicture,description}]
