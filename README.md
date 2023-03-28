# Task1 - Java rest api

## Tech stack used :
```
Java
Maven
Springboot
MongoDB
```

## Requirements :
1. Java jdk 20

2. Mongodb Compass <br/>

   <b>Note:</b> I have used Mongodb Atlas to connect to Compass using connection url in properties file

3. Postman
4. IDE - Intellij

## Rest api endpoints
- Put a server ```http://localhost:8080/createServer``` add "server" object in JSON form.

- GET servers ```http://localhost:8080/getServers``` returns a list of "server" objects.

- GET server by id ```http://localhost:8080/getServers/{id}``` returns a "server" object matching with id.

- GET servers by name ```localhost:8080/getServer/{name}``` returns a list of "server" objects matching with name.

- DELETE server by id ```http://127.0.0.1:2017/deleteServer/{id}``` Deletes a "server" object matching with id. 
