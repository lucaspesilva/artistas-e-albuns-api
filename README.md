# API de Artistas de Álbuns
Projeto prático de backend do processo seletivo da Polícia Judiciária Civil de MT

Candidado: Lucas Pinto e Silva

### Dependências do Projeto
- Projeto Maven
- Java 11
- Spring Boot 2.4.1
- Spring Web
- Jersey
- MySQL JDBC Driver
- Spring Data JPA

### Como rodar a aplicação
1. Instalar o Maven: http://maven.apache.org/install.html
2. Instalar o Docker: https://docs.docker.com/get-docker/
3. Clonar o repositório
4. Baixar as dependências e compilar o projeto:
   `mvn clean install`
5. Rodar o docker compose para construir e rodar as imagens tanto da aplicação quanto do banco de dados:
   `docker-compose up --build --force-recreate`

### Como realizar os testes
1. Recuperar o token JWT do usuário de teste:
```bash
curl -i -X POST "http://localhost:8080/login" -d "{ \"username\":\"user\", \"password\":\"123\" }" -H "Content-Type: application/json"
```
2. Anotar o token incluindo a string Bearer:
```
Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNjExNzUzNDAzfQ._tih5H79WaI99xzcsuIfQnZdW9WepEv2tuzl4823ghYYW1qQN4xRXNS8r5rYgYpEYIUr3mFxE5a-R5oMEQKQXA
```
3. Acessar a página do Swagger: http://localhost:8080/swagger-ui.html
4. Inserir o token JWT clicando no botão "Authorize"

### Endpoints disponíveis

###### ArtistaController
- Recuperar todos os artistas:
  `GET /artista/`
  
- Cadastrar um novo artista:
`POST /artista/`
```json
{
  "nome": "string"
}
```
- Recuperar artista por ID: 
`GET /artista/{artistaUID}`
- Atualizar artista por ID:
`PUT /artista/{artistaUID}`
```json
{
  "nome": "string"
}
```
- Consultar artistas por nome e ordenado por asc ou desc (asc=true ou false):
`GET /artista/consultar?asc=&nome=`

###### AlbumController
- Recuperar todos os álbuns com paginação:  
  `GET /album/?page=&pageSize=`
- Cadastrar um novo álbum (com artista cadastrado previamente):
  `POST /album/`
```json
{
  "artista": 
	{
	"artistaUID": 0,
	"nome": "string"
	},
  "nome": "string"
}
```
- Recuperar álbum por ID:
  `GET /album/{albumUID}`
- Atualizar álbum por ID:
  `PUT /album/{albumUID}`
```json
{
  "artista": 
	{
	"artistaUID": 0,
	"nome": "string"
	},
  "nome": "string"
}
```
- Rcuperar álbuns por artista com paginação:
  `GET /album/artista/{artistaUID}?page=&pageSize=`

###### CapaController
- Recuperar as capas de um álbum (retorna links do MinIO):
`GET /capa/{albumUID}`
  
- Fazer upload de uma capa para um álbum (pode ser chamado mais de uma vez para mandar mais de uma capa):
`POST /capa/{albumUID} "Content-Type: multipart/form-data" -F "file=@;type="`
  
