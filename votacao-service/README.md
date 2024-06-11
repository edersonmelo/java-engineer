# Votação API

### SWAGGER
```
http://localhost:8080/api-votacao/swagger-ui.html
```

### ACTUATOR
```
http://localhost:8080/api-votacao/actuator
```
```
http://localhost:8080/api-votacao/actuator/health
```

## Utilizado para o Desenvolvimento conforme meu conhecimento técnico:

* [Springboot](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [MySQL](https://www.mysql.com/) - MySQL
* [Swagger](https://swagger.io/docs/) - The API design framework
* [Kafka](https://kafka.apache.org/) - Apache Kafka
* [Lombok](https://projectlombok.org/features/all) - The build tools framework
* [Hibernate](https://hibernate.org/) - The Object/Relational Mapping (ORM) framework


#Chamadas da API conforme descritas para importação no postman(pode ser importado para o postman apartir do arquivo Votacao API.postman_collection.json na pasta TarefaAdicional:

* nome método: "Get Pautas",
	método: "GET",
		valor e chamada: "http://localhost:8080/api-votacao/v1/pautas",

* nome método: "Create Pauta",
	método: "POST",
		valor e chamada: "{\n\t\"id\": \"2\",\n\t\"nome\": \"Pauta Ederson\"\n}"
		valor e chamada: "http://localhost:8080/api-votacao/v1/pautas",

* nome método: "Find Pauta",
	método: "GET",
		valor e chamada: "http://localhost:8080/api-votacao/v1/pautas/1",

* nome método: "Delete Pauta",
	método: "DELETE",
		valor e chamada: "http://localhost:8080/api-votacao/v1/pautas/sessoes/1",

* nome método: "Get Sessoes",
	método: "GET",
		valor e chamada: "http://localhost:8080/api-votacao/v1/pautas/sessoes",

* nome método: "Create Sessao",
	método: "POST",
		valor e chamada: "{\n\t\"dataInicio\": \"2021-03-15T00:00:00\",\n\t\"minutosValidade\": 60\n}"
		valor e chamada: "http://localhost:8080/api-votacao/v1/pautas/1/sessoes",

* nome método: "Find Sessao",
	método: "GET",
		valor e chamada: "http://localhost:8080/api-votacao/v1/pautas/sessoes/1",

* nome método: "Delete Sessao",
	método: "DELETE",
		valor e chamada: "http://localhost:8080/api-votacao/v1/pautas/2",

* nome método: "Get Votos",
	método: "GET",
		valor e chamada: "http://localhost:8080/api-votacao/v1/pautas/sessoes/votos",

* nome método: "Create Voto",
	método: "POST",
		valor e chamada: "{\n\t\"cpf\": \"CPF OCULTO POR SEGURANÇA\",\n\t\"escolha\": True\n}"
		valor e chamada: "http://localhost:8080/api-votacao/v1/pautas/1/sessoes/1/votos",
		
* nome método: "Find Voto",
	método: "GET",
		valor e chamada: "http://localhost:8080/api-votacao/v1/pautas/sessoes/votos/1",

* nome método: "Delete Voto",
	método: "DELETE",
		valor e chamada: "http://localhost:8080/api-votacao/v1/pautas/sessoes/votos/1",

* nome método: "Get Resultado Votacao",
	método: "GET",
		valor e chamada: "http://localhost:8080/api-votacao/v1/pautas/1/votacao",


# Versionamento da API, Como você versionaria a API da sua aplicação? Que estratégia usar?
Minha ideia é que caso tenhamos alguma mudança que quebre a compatibilidade com a versão atual. Neste caso devemos trabalhar o versionamento e talvez uma branche com uma nova versão de API e assim o número da versão. 
É possível manter sempre a versão anterior e a atual(nova) até que os clientes atualizem para a nova versão da API.

## Accept versions
Utilizar o cabeçalho Accept da requisição HTTPS para determinar o número de versão secundária.



## Autor

* **Ederson Melo** - [edersonmelo](https://github.com/edersonmelo)

