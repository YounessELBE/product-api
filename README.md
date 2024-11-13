# ALTEN E-commerce : Product API

Projet back-end pour la gestion des produits en utilisant Java/Spring Boot.

# Description
Ce back-end permet de gérer des produits avec plusieurs endpoints d'API pour la création, la récupération, la mise à jour et la suppression de produits.

# API Endpoints

| Resource           | POST                  | GET                            | PATCH                                    | PUT | DELETE           |
| ------------------ | --------------------- | ------------------------------ | ---------------------------------------- | --- | ---------------- |
| **/products**      | Create a new product  | Retrieve all products          | X                                        | X   |     X            |
| **/products/:id**  | X                     | Retrieve details for product 1 | Update details of product 1 if it exists | X   | Remove product 1 |

# Structure du Produit
Un produit contient les attributs suivants :

``` typescript
class Product {
  id: number;
  code: string;
  name: string;
  description: string;
  image: string;
  category: string;
  price: number;
  quantity: number;
  internalReference: string;
  shellId: number;
  inventoryStatus: "INSTOCK" | "LOWSTOCK" | "OUTOFSTOCK";
  rating: number;
  createdAt: number;
  updatedAt: number;
}
```
# Prérequis
- Java 17
- Spring Boot 3.2.1
- Maven
- MapStruct
- Lombok
- h2database
- JUnit


# Exécution
- Localisez la classe ProductApplication dans le package com.alten.product
- Clic droit sur la classe et sélectionnez "Run as Java Application".

Ou bien en utilisant la commande maven : mvn spring-boot:run

# Swagger
Après avoir démarré l'application, vous pouvez consulter la documentation Swagger :
http://localhost:9090/swagger-ui/index.html

# Exemple de Requêtes API
Créer un produit : 

POST /products
{
    "code": "code_10",
    "name": "name 10",
    "description": "Product Description 10",
    "image": "black-10.jpg",
    "category": "CAT-10",
    "price": 10.01,
    "quantity": 10,
    "shellId": "1010101",
    "inventoryStatus": "INSTOCK",
    "rating": 5
}

# Postman collection
product-api/postman/Product API.postman_collection.json