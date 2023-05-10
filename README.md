# DOC

* Base de datos H2.
* Framework Spring boot '2.7.11' e intellij idea '2023.1.1'.
* Java 11.
* Gradle.
* Swagger.
* JWT.

## Swagger
* http://localhost:8080/swagger-ui/index.html

## database h2
* user: admin
* pasword:admin

## Postman

### Token

* método POST.
* path http://localhost:8080/token.
* agregar JSON


    {
        "email": "admin@email.com",
        "password": "admin"
    }

* el token se encontrara en el header de la respuesta
### Endpoints

#### SAVE
* path http://localhost:8080/user/save
* método POST.
* Utilizar Bearer Token obtenido de TOKEN.
* Agregar JSON


    {        
        "name": "testName",
        "email": "testemail@email.com",
        "password": "testPsw",
        "phones": [
                    {
                    "number": "123",
                    "cityCode":"456",
                    "countryCode": "789"
                    }
                ]
}

#### FINDALL
* path http://localhost:8080/user/findAll
* método GET.
* Utilizar Bearer Token obtenido de TOKEN.


#### FINDBYID
* path http://localhost:8080/user/findById/{id}
* método GET.
* Utilizar Bearer Token obtenido del anterior metodo.

#### UPDATE
* path http://localhost:8080/user/update/{id}
* método patch.
* Utilizar Bearer Token obtenido de TOKEN.
* Agregar JSON


    {        
        "name": "testName",
        "email": "testemail@email.com",
        "password": "testPsw",
        "phones": [
                    {
                    "number": "123",
                    "cityCode":"456",
                    "countryCode": "789"
                    }
                ]
}

#### DELETE
* path http://localhost:8080/user/delete/{id}
* método del.
* Utilizar Bearer Token obtenido de TOKEN.



