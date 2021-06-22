# Desafio Supera Airton

## Executando projeto

Após importar projeto na IDE basta executar o arquivo DesafioSuperaApplication.java para rodar o projeto.

Para executar os testes, execute o comando: 

```./mvnw clean test```


## Framework escolhido: Spring Boot

Escolhi o Spring por já possuir familiaridade para construir REST API's com ele e por ser muito usado no mercado.

Foi utilizado JPA, Hibernate e Spring-data com o banco em memória H2. Foram inseridos alguns registros inicias.

Segue endereço para console do H2 (dados de acesso padrão):

http://localhost:8080/h2-console

Foram criados três tabelas:

- TB_PRODUCT (Tabela para guardar Produtos)
- TB_SHOPCART_PRODUCT  (Tabela de relacionamento entre Produtos e Carrinhos)
- TB_SHOPCART  (Tabela para guardar Carrinhos)


# Endpoints

Foi adicionado na pasta raiz um arquivo que pode ser importado no Insomnia que possui todos os endpoints para testes. Eles são: 

## Carrinho

### Listar carrinhos - ```GET``` /shopcarts/

http://localhost:8080/shopcarts/

Pode ser passado um QueryParameter ```sortAttribute``` para ordenar produtos no carrinho. Valores possíveis de ordenação:

- NAME
- PRICE
- SCORE

Exemplo: http://localhost:8080/shopcarts/?sortAttribute=PRICE

---

### Buscar carrinho por Id - ```GET``` /shopcarts/{id}

http://localhost:8080/shopcarts/1

Obs: Aceita ```sortAttribute```

---

### Checkout carrinho por Id - ```GET``` /shopcarts/checkout/{id}

http://localhost:8080/shopcarts/checkout/2

Calcular subtotal, frete e total dos produtos no carrinho.

---

### Inserir carrinho - ```POST``` /shopcarts/

http://localhost:8080/shopcarts/

Cria carrinho com produtos selecionados. Deve ser passado uma lista de objetos com ```id``` e ```quantity``` no corpo da requisição.

``` JSON
[
	{
		"id": 1,
		"quantity": 3
	},
	{
		"id": 3,
		"quantity": 1
	}
]
```
---

### Inserir produto no carrinho - ```PUT``` /shopcarts/add-product/{id}

http://localhost:8080/shopcarts/add-product/3

Adiciona produtos ao um carrinho existente.

Obs: Segue mesmo padrão do exemplo acima.

---

### Remove produto do carrinho - ```DELETE``` /shopcarts/remove-product/{id}

http://localhost:8080/shopcarts/remove-product/2

Remove produtos do carrinho pela quantidade informada.

Obs: Segue mesmo padrão do exemplo acima.

---

### Deleta carrinho - ```DELETE``` /shopcarts/{id}

http://localhost:8080/shopcarts/1

Deleta carrinho.

---

## Produto

### Listar produtos - ```GET``` /products/

http://localhost:8080/products/

Lista todos os produtos, também podendo ser ordenados por ```NAME, SCORE, PRICE```.

Exemplo: http://localhost:8080/products/?sortAttribute=NAME

---

### Listar produtos por id- ```GET``` /products/{id}

http://localhost:8080/products/1

Lista produto por ```id``` informado.

---

### Inserir produto -  ```POST``` /products/

http://localhost:8080/products/

Insere produtos conforme exemplo:

```JSON
{
	"name": "Top Gear 2000",
	"price": 85.00,
	"score": 185,
	"image": "top_gear_2000.png"
}
```

---

### Inserir produto -  ```PUT``` /products/{id}

http://localhost:8080/products/7

Altera informações de produtos ja inseridos.

Obs: Segue padrão de objeto acima.

---

### Inserir produto -  ```DELETE``` /products/{id}

http://localhost:8080/products/7

Deleta produto existente.

---
