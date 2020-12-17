# API Atualização Cadastro de Cliente e Consulta de Endereços - Banco Pan

Repositorio com um simples exemplo de projeto Java utilizando Spring Boot e Banco de dados em memória.

## Pessoa

### Consultar uma pessoa utilizando seu CPF como parâmetro.
**[GET]** http://localhost:8080/v1/pessoa/45081816604

### Consultar o endereço pessoa utilizando seu CPF e CEP como parâmetro.
**[GET]** http://localhost:8080/v1/pessoa/45081816604/endereco/02721200

### Alterar o endereço de uma pessoa
**[PUT]** http://localhost:8080/v1/pessoa/45081816604/endereco/3
> exemplo de curl para realizar a alteração:
```
curl -X PUT \
  http://localhost:8080/v1/pessoa/45081816604/endereco/4 \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: eb1fd97d-7c1a-4eee-9a3d-ea826d85d86d' \
  -H 'cache-control: no-cache' \
  -d '    {
        "apelidoEndereco": "Escritorio",
        "logradouro": "Nome da rua",
        "numeroLogradouro": 123,
        "complemento": "casa",
        "bairro": "Centro",
        "cep": "02621003",
        "cidade": "São Paulo",
        "estado": "SP"
    }'
```

## Endereço

### Consulta de estados brasileiros
**[GET]**  http://localhost:8080/v1/endereco/estados

### Consulta de municipios de um determinado estados brasileiro
**[GET]**  http://localhost:8080/v1/endereco/estados/31/municipios

### Consulta de um endereço pelo CEP
**[GET]**  http://localhost:8080/v1/endereco/cep/02551200