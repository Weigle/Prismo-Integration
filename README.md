# transactions API

this project is example RESTful using java Spring Boot with docker 

## Prerequisites
[![JDK](https://img.shields.io/badge/JDK-8.0.1-orange)](https://www.azul.com/downloads/zulu-community/?architecture=x86-64-bit&package=jdk)

[![Docker](https://img.shields.io/badge/Docker-%3E%3D18.09.1-blue)](https://www.docker.com/)

[![Docker-compose](https://img.shields.io/badge/Docker--compose-%3E%3D1.21.0,-blue)](https://github.com/docker/compose/releases)

**TO RUN IN LINUX:**
```console
chmod +x init.sh && ./init.sh 
```

## ENDPOINT
This end pont create a new client

**ALL REQUEST MUST BE SET HEADER** Content-Type=application/json

## Accounts

```url
http://{$URL}/Accounts/
```

**Method:** ```POST```

Parameters | Is Required | Type
--- | --- | ---
documentNumber | Yes  | Long

Example Request Body 
```json
{
  "documentNumber": "123456789"
}
```
___________

```url
http://{$URL}/Accounts/{accountId}
```

**Method:** ```GET```

Parameters | Is Required | Type
--- | --- | ---
accountId | Yes  | Long

Example in URL
```url
{
  http://{$URL}/Accounts/1'
}
```
Example Response
```json
{
  "accountId": "1",
  "operationTypeId": "PAYMENT",
  "amount": "100"
}
```

_____________

## Transactions

```url
http://{$URL}/Transactions/
```

**Method:** ```POST```

Parameters | Is Required | Type
--- | --- | ---
accountId | Yes  | Long
operationTypeId | Yes  | enum
amount | Yes  | Long

operationType enum 

CASH_PURCHASE 
INSTALLED_PURCHASE 
WITHDRAW 
PAYMENT

Example Request Body
```json
{
  "accountId": "1",
  "operationTypeId": "PAYMENT",
  "amount": "100"
}
```
