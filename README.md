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

## Rest Controller Functionality
```/getServers``` this will give the list of all server objects which is stored in the mongodb database.

```/getServers/{id}``` Gives the object with the id passed in url.

```/createServer``` Create the server object passed as a json-encoded form in body.

```/deleteServer/{id}``` Deletes the object with the given id

```/getServer/{name}``` Gives the object with the name passed in url

Code screenshot:
![image](https://user-images.githubusercontent.com/66437295/228335924-0b6f1f4a-b2c7-444c-96e7-a7259550a2a4.png)



## Rest api endpoints
- Put a server ```http://localhost:8080/createServer``` add "server" object in JSON form.

- GET servers ```http://localhost:8080/getServers``` returns a list of "server" objects.

- GET server by id ```http://localhost:8080/getServers/{id}``` returns a "server" object matching with id.

- GET servers by name ```localhost:8080/getServer/{name}``` returns a list of "server" objects matching with name.

- DELETE server by id ```http://127.0.0.1:2017/deleteServer/{id}``` Deletes a "server" object matching with id.



