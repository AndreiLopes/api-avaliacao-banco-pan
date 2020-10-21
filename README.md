## API Atualização Cadastro de Cliente e Consulta de Endereços - Banco Pan

### Inicio
Iniciar com: mvn spring-boot:run

### Cenários
##### Cenário 1 - Consultar Cliente
- url: localhost:8080/v1/cliente/consulta/{cpf}
- method: GET Variável {cpf} é um número de 14 digítos.

##### Cenário 2 - Consultar Endereço
- url: localhost:8080/v1/endereco/consulta/{cep}
- method: GET Variável {cep} é um número de 8 digítos.

##### Cenário 3 – Consultar Estados
- url: localhost:8080/v1/endereco/consulta/estados/
- method: GET

##### Cenário 4 – Consultar Municípios Pelo UF
- url: localhost:8080/v1/endereco/consulta/cidades/{uf}
- method: GET Variável {uf} é um número de 2 digítos, pode ser consultado por um dos codigos do resultado do Cenário 3, ou 35 para o estado de São Paulo

##### Cenário 5 – Alterar endereço
- Não está funcional
