# Projeto API JSON RESTful 

Desafio implementado consiste em um aplicação de cadastro de veículos para o processo seletivo da Tinnova.

### Especificação da Entidade Veiculo
```
{
    veiculo : String,
    marca : String,
    ano : Integer,
    descricao : text,
    vendido : boolean,
    created : datetime,
    updated : datetime
}
```
- As data de criação de um registro (created) e atualização de um registro (updated), nesta aplicação, são setadas camada de serviço da aplicação nos momentos de criação e atualização de cadastro.
- Na efetuação do cadastro de veículo, o atributo updated é persistido com valor 'null' e só poderá ser persistido no banco através das requisições de atualização.
## Requisitos da API
- Permitir cadastro de veículos;
- Permitir atualização de dados de um veículo;
- Permitir a exclusão de um veículo;
- Exibir a informação de quantos veiculos estão como não vendidos na base;
- Exibir a informação da distribuição de veículos por década de fabricação;
- Exibir a informação da distribuição de veículos por fabricante;
- Exibir os carros registrados durante a última semana;
- Deverá haver consistência das marcas fornecidas. Não poderá haver marcas escritas de forma errada (Exemplo: Xevrolet não serão permitidas no cadastro e nem na atualização).

## Tecnologias utilizadas no Backend
- Java
- Spring Boot
- JPA / Hibernate
- Maven
- H2 database
- Postman (Testar requisições)
- Junit

## Padrão Camadas

![Padrão Camadas](https://github.com/miguelmoraisdev/tinnovadesafio/blob/master/_assets/camadas.png)
- Usamos o padrão camadas separando a aplicação backend em três camadas: Controladores Rest, Camada de Serviços e Camada de Acesso aos Dados (+ Entidade).

## Endpoints da API
### Collection do Postman para testes e documentação.
https://www.getpostman.com/collections/894baa11efdde2111674
#### Busca paginada dos registros de veículos na base (GET)
```
http://localhost:8080/veiculos
```
![Busca Paginada](https://github.com/miguelmoraisdev/tinnovadesafio/blob/master/_assets/VeiculosPaged.png)

#### Buscar quantidade de veículos não vendidos (GET)
```
http://localhost:8080/veiculos/numberOfUnsoldVehicles
```
![Busca Veiculos Nao Vendidos](https://github.com/miguelmoraisdev/tinnovadesafio/blob/master/_assets/GetVeiculosNaoVendidos.png)

#### Buscar distribuição de veículos por década de fabricação (GET)
```
http://localhost:8080/veiculos/VehiclesPerDecade
```
![Busca Veiculos por Decada](https://github.com/miguelmoraisdev/tinnovadesafio/blob/master/_assets/VeiculosPorDecada.png)

#### Buscar distribuição de veículos por fabricante (GET)
```
http://localhost:8080/veiculos/amountVehiclesPerCompany
```
![Busca Veiculos por Fabricante](https://github.com/miguelmoraisdev/tinnovadesafio/blob/master/_assets/VeiculosPorMarca.png)

#### Buscar veiculos registrados na última semana (GET)
```
http://localhost:8080/veiculos/lastVehiclesRegistred
```
![Busca Veiculos Resgistrados na Ultima Semana](https://github.com/miguelmoraisdev/tinnovadesafio/blob/master/_assets/UltimosVeiculosRegistrados.png)

#### Buscar informações do veiculo por ID (GET)
```
http://localhost:8080/veiculos/1
```
- Caso onde ID existe no banco

![Busca Veiculo por ID](https://github.com/miguelmoraisdev/tinnovadesafio/blob/master/_assets/VeiculosByID.png)

```
http://localhost:8080/veiculos/21
```
- Caso onde ID não existe no banco

![Busca Veiculo por ID Nao Existente](https://github.com/miguelmoraisdev/tinnovadesafio/blob/master/_assets/VeiculosByIDNotFound.png)

#### Cadastrar veiculo na base (POST)

- JSON da entrada de dados do veiculo
```
{
    "veiculo" : "Palio",
    "marca" : "Fiat",
    "ano" : 2006,
    "descricao" : "O melhor carro que você pode ter na sua vida.",
    "vendido" : true
}
```
```
http://localhost:8080/veiculos
```
- Caso onde o fabricante está no formato correto

![Post Veiculo Correto](https://github.com/miguelmoraisdev/tinnovadesafio/blob/master/_assets/InsertVeiculo.png)

- Caso onde o fabricante está com formato incorreto
```
{
    "veiculo" : "Palio",
    "marca" : "fiate",
    "ano" : 2006,
    "descricao" : "O melhor carro que você pode ter na sua vida.",
    "vendido" : true
}
```
![Post Veiculo Incorreto](https://github.com/miguelmoraisdev/tinnovadesafio/blob/master/_assets/InsertVeiculoNoValidated.png)

#### Atualizar informações de veiculo (PUT)
```
http://localhost:8080/veiculos/1
```

- JSON da entrada de dados do veiculo para o caso onde o fabricante está com formato correto
```
{
    "veiculo" : "Polo",
    "marca" : "Volkswagen",
    "ano" : 2007,
    "descricao" : "O melhor carro que você pode ter na sua vida.",
    "vendido" : false
}
```
![Put Veiculo Correto](https://github.com/miguelmoraisdev/tinnovadesafio/blob/master/_assets/PutVeiculo.png)

- JSON da entrada de dados do veiculo para o caso onde o fabricante está com formato incorreto
```
{
    "veiculo" : "Polo",
    "marca" : "leoolkswagen",
    "ano" : 2007,
    "descricao" : "O melhor carro que você pode ter na sua vida.",
    "vendido" : false
}
```
![Put Veiculo Incorreto](https://github.com/miguelmoraisdev/tinnovadesafio/blob/master/_assets/PutVeiculoNoValidate.png)

#### Atualizar apenas se o veiculo foi vendido ou não (PATCH)
```
http://localhost:8080/veiculos/1
```
- JSON da entrada de dados para atualização do atributo vendido. Neste endpoint apenas o atributo vendido pode ser alterado.
```
{
    "vendido" : false
}
```
![Patch vendido](https://github.com/miguelmoraisdev/tinnovadesafio/blob/master/_assets/PatchVeiculo.png)

#### Deletar o veiculo registrado na base (DELETE)
```
http://localhost:8080/veiculos/1
```
- Caso onde o ID existe na base
![Delete existe](https://github.com/miguelmoraisdev/tinnovadesafio/blob/master/_assets/DeleteVeiculo.png)

- Caso onde o ID não existe na base
![Delete não existe](https://github.com/miguelmoraisdev/tinnovadesafio/blob/master/_assets/DeleteVeiculoNoValidate.png)

## Seed do banco H2 que foi usado para o desenvolvimento da aplicação
```
INSERT INTO tb_veiculo (veiculo, marca, ano, descricao, vendido, created, updated) VALUES ('Corsa Sedan', 'Chevrolet', 1998, 'O melhor carro que você pode ter na sua vida.', true, NOW(), null);
INSERT INTO tb_veiculo (veiculo, marca, ano, descricao, vendido, created, updated) VALUES ('Corsa Hatch', 'Chevrolet', 2002, 'O melhor carro que você pode ter na sua vida.', false,TIMESTAMP WITH TIME ZONE '2021-09-11T03:00:00Z', null);
INSERT INTO tb_veiculo (veiculo, marca, ano, descricao, vendido, created, updated) VALUES ('Celta', 'Chevrolet', 1996, 'O melhor carro que você pode ter na sua vida.', true, NOW(), null);
INSERT INTO tb_veiculo (veiculo, marca, ano, descricao, vendido, created, updated) VALUES ('Opala', 'Chevrolet', 1982, 'O melhor carro que você pode ter na sua vida.', true, TIMESTAMP WITH TIME ZONE '2021-09-11T03:00:00Z', null);
INSERT INTO tb_veiculo (veiculo, marca, ano, descricao, vendido, created, updated) VALUES ('Prisma', 'Chevrolet', 2018, 'O melhor carro que você pode ter na sua vida.', false, NOW(), null);
INSERT INTO tb_veiculo (veiculo, marca, ano, descricao, vendido, created, updated) VALUES ('Onix Hatch', 'Chevrolet', 2020, 'O melhor carro que você pode ter na sua vida.', true, TIMESTAMP WITH TIME ZONE '2021-09-11T03:00:00Z', null);
INSERT INTO tb_veiculo (veiculo, marca, ano, descricao, vendido, created, updated) VALUES ('Onix Sedan', 'Chevrolet', 2021, 'O melhor carro que você pode ter na sua vida.', false, NOW(), null);
INSERT INTO tb_veiculo (veiculo, marca, ano, descricao, vendido, created, updated) VALUES ('Palio', 'Fiat', 2006, 'O melhor carro que você pode ter na sua vida.', true, TIMESTAMP WITH TIME ZONE '2021-09-11T03:00:00Z', null);
INSERT INTO tb_veiculo (veiculo, marca, ano, descricao, vendido, created, updated) VALUES ('Punto', 'Fiat', 2011, 'O melhor carro que você pode ter na sua vida.', false, NOW(), null);
INSERT INTO tb_veiculo (veiculo, marca, ano, descricao, vendido, created, updated) VALUES ('Argo', 'Fiat', 2014, 'O melhor carro que você pode ter na sua vida.', true, TIMESTAMP WITH TIME ZONE '2021-09-11T03:00:00Z', null);
INSERT INTO tb_veiculo (veiculo, marca, ano, descricao, vendido, created, updated) VALUES ('Uno', 'Fiat', 1988, 'O melhor carro que você pode ter na sua vida.', false, NOW(), null);
INSERT INTO tb_veiculo (veiculo, marca, ano, descricao, vendido, created, updated) VALUES ('Siena', 'Fiat', 2003, 'O melhor carro que você pode ter na sua vida.', true, TIMESTAMP WITH TIME ZONE '2021-09-14T03:00:00Z', null);
INSERT INTO tb_veiculo (veiculo, marca, ano, descricao, vendido, created, updated) VALUES ('Doblô', 'Fiat', 2008, 'O melhor carro que você pode ter na sua vida.', false, NOW(), null);
INSERT INTO tb_veiculo (veiculo, marca, ano, descricao, vendido, created, updated) VALUES ('Fiesta Sedan', 'Ford', 2015, 'O melhor carro que você pode ter na sua vida.', true, TIMESTAMP WITH TIME ZONE '2021-09-14T03:00:00Z', null);
INSERT INTO tb_veiculo (veiculo, marca, ano, descricao, vendido, created, updated) VALUES ('Fiesta Hatch', 'Ford', 1999, 'O melhor carro que você pode ter na sua vida.', false, NOW(), null);
INSERT INTO tb_veiculo (veiculo, marca, ano, descricao, vendido, created, updated) VALUES ('Ecosport', 'Ford', 2009, 'O melhor carro que você pode ter na sua vida.', true, TIMESTAMP WITH TIME ZONE '2021-09-13T00:00:00Z', null);
INSERT INTO tb_veiculo (veiculo, marca, ano, descricao, vendido, created, updated) VALUES ('Ka Sedan', 'Ford', 2017, 'O melhor carro que você pode ter na sua vida.', false, NOW(), null);
INSERT INTO tb_veiculo (veiculo, marca, ano, descricao, vendido, created, updated) VALUES ('Ka Hatch', 'Ford', 2005, 'O melhor carro que você pode ter na sua vida.', true, TIMESTAMP WITH TIME ZONE '2021-09-12T01:00:00Z', null);
INSERT INTO tb_veiculo (veiculo, marca, ano, descricao, vendido, created, updated) VALUES ('Gol', 'Volkswagen', 1997, 'O melhor carro que você pode ter na sua vida.', false, TIMESTAMP WITH TIME ZONE '2021-09-11T03:00:00Z', null);
INSERT INTO tb_veiculo (veiculo, marca, ano, descricao, vendido, created, updated) VALUES ('Polo', 'Volkswagen', 1993, 'O melhor carro que você pode ter na sua vida.', true, NOW(), null);
```
# Como executar o projeto

## Back end
Pré-requisitos: Java 11

```bash
# clonar repositório
git clone https://github.com/miguelmoraisdev/tinnovadesafio.git

# entrar na pasta do projeto back end
cd backend

# executar o projeto
./mvnw spring-boot:run
```
# Autor

Miguel Augusto de Morais Junior

