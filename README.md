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


### Project Directory structure
Create the following Directory structure under:<br/>
<b>src/main/java/</b>
- controller
- model
- repository
- service

### Creating MVC model for our api
We are creating a spring project using spring intialiser having a main java class named SpringbootMongodbApplication.

![image](https://user-images.githubusercontent.com/66437295/228762985-f26970ed-ff38-4ed0-88ab-306da8e1db52.png)


### Defining the model
Create <b>Server</b> class under the model package. The structure of the mongoDB user instance is defined in this class.
```
package com.example.springbootmongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "server")

public class Server {

    @Id
    private int id;

    private String name;
    private String language;
    private String framework;

    public Server(int id, String name, String language, String framework) {
        super();
        this.id = id;
        this.name = name;
        this.language = language;
        this.framework = framework;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
    public String getFramework() {
        return framework;
    }
    public void setFramework(String framework) {
        this.framework = framework;
    }

    @Override
    public String toString() {
        return "Server [id=" + id + ", name=" + name + ", language=" + language + ", framework=" + framework + " ]";
    }

}
```

![image](https://user-images.githubusercontent.com/66437295/228754747-784d62e9-8f4c-4e83-afdd-67b004ac7b27.png)

### Defining the Repository
Create <b>ServerRepository</b> Interface under the repository package. This interface extends from the MongoRepository. This interface is used to access the database.
```
package com.example.springbootmongodb.repository;

import java.util.List;

import com.example.springbootmongodb.model.Server;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ServerRepository extends MongoRepository<Server, Long> {
    public List<Server> findByName(String name);

}
```

![image](https://user-images.githubusercontent.com/66437295/228755471-c25c8123-81c7-4cf0-8e0d-5d69cd5696f9.png)

### Defining the Service
Create <b>ServerService</b> class under the service package. This interface defines all the methods that controller can access.
```
package com.example.springbootmongodb.service;

import java.util.List;
import java.util.Optional;

import com.example.springbootmongodb.repository.ServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.springbootmongodb.model.Server;

@Service
public class ServerService {

    @Autowired
    public ServerRepository serverRepository;

    public ResponseEntity<List<Server>> getAllServers(){

        List<Server> servers= serverRepository.findAll();
        if(servers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(servers));
    }

    public ResponseEntity<Optional<Server>> getServerById(long id) {
        Optional<Server> server= serverRepository.findById(id);
        if(server.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(server));
    }

    public String addServer(Server server) {
        serverRepository.insert(server);
        return "Server added : " + server;
    }

    public String deleteServer(long id) {
        Optional<Server> server=serverRepository.findById(id);
        serverRepository.deleteById(id);
        return "Server deleted : "+ server;
    }

    public ResponseEntity<List<Server>> getServerByName(String name) {
        List<Server> servers= serverRepository.findByName(name);
        if(servers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(servers));

    }
}
```

![image](https://user-images.githubusercontent.com/66437295/228756599-bb45dc47-ee4a-4bd4-aaf5-7559dca5b326.png)


### Defining the Controller
Create <b>ServerController</b> class under the controller package. Controller handles all the routes.
```
package com.example.springbootmongodb.controller;

import com.example.springbootmongodb.model.Server;
import com.example.springbootmongodb.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ServerController {

    @Autowired
    public ServerService serverService;

    @GetMapping("/getServers")
    public ResponseEntity<List<Server>> getAllServers(){
        return serverService.getAllServers();
    }

    @GetMapping("/getServers/{id}")
    public ResponseEntity<Optional<Server>> getServerById(@PathVariable long id){
        return serverService.getServerById(id);
    }

    @PostMapping("/createServer")
    public String createServer(@RequestBody Server server){
        return serverService.addServer(server);
    }

    @DeleteMapping("/deleteServer/{id}")
    public String deleteServer(@PathVariable("id") long id){
        return serverService.deleteServer(id);
    }

    @GetMapping("/getServer/{name}")
    public ResponseEntity<List<Server>> getServer(@PathVariable String name){
        return serverService.getServerByName(name);
    }

}
```

![image](https://user-images.githubusercontent.com/66437295/228756922-a21ae660-d39f-4395-aac5-faf31aff97c4.png)

### Application Properties
In application.properties file paste the provided connection string provide by mongodb atlas to connect to your cluster also  you can connect to your cluster using MongoDB Compass, database is serverdb.
Replace <b><password></b> with the password specified when you created your database user.

```
spring.data.mongodb.uri= your connection string
spring.data.mongodb.database=serverdb
```

![image](https://user-images.githubusercontent.com/66437295/228759676-6009f6c1-a48a-4b36-a6a0-ba359ac64640.png)


## Rest Controller Functionality
```/getServers``` this will give the list of all server objects which is stored in the mongodb database.

```/getServers/{id}``` Gives the object with the id passed in url.

```/createServer``` Create the server object passed as a json-encoded form in body.

```/deleteServer/{id}``` Deletes the object with the given id

```/getServer/{name}``` Gives the object with the name passed in url

Code screenshot:
![image](https://user-images.githubusercontent.com/66437295/228337213-da39a026-20e7-4bbe-be5b-0842751fdaeb.png)

![image](https://user-images.githubusercontent.com/66437295/228434809-6c4511df-9471-4348-9f8e-1c4a81d6a62a.png)


## Rest api endpoints
- Put a server ```http://localhost:8080/createServer``` add "server" object in JSON form.

- GET servers ```http://localhost:8080/getServers``` returns a list of "server" objects.

- GET server by id ```http://localhost:8080/getServers/{id}``` returns a "server" object matching with id.

- GET servers by name ```localhost:8080/getServer/{name}``` returns a list of "server" objects matching with name.

- DELETE server by id ```http://127.0.0.1:2017/deleteServer/{id}``` Deletes a "server" object matching with id.


## Checking Api using Postman

### 1. Adding Server
### Post request to /createServer
When this endpoint is hit in postman, the createServer() method is invoked in sprinboot controller which accepts a Server object in the request body and created the objects in the database.
The `serverService.addServer(server)` method is called with the Server object as a parameter, which adds the server to the system and returns a message indicating whether the operation was successful or not.
```
  @PostMapping("/createServer")
    public String createServer(@RequestBody Server server){
        return serverService.addServer(server);
    }
```
<b>Server is added</b>
![create_server](https://user-images.githubusercontent.com/66437295/228340951-c348bdb6-970b-481a-a17b-076f66d7d3c4.jpg)



### 2. Get Servers
### Get request to /getServers
When this endpoint is hit in postman, the getAllServers() method is invoked in ServerService class. This method returns a list of Server objects that are retrieved from the system through the serverService.getAllServers() method call. 

```
@GetMapping("/getServers")
    public ResponseEntity<List<Server>> getAllServers(){
        return serverService.getAllServers();
    }
 ```
Here we get all the servers present in database

![get_allservers](https://user-images.githubusercontent.com/66437295/228347001-2827d413-d3fd-4001-9260-bbcf00acef37.jpg)



### 3. Get Servers by id
### Get request to /getServers/id
When this endpoint is hit, it retrieves a server by its ID by sending a GET request with the ID in the URL parameter. The server object is returned in a ResponseEntity with appropriate HTTP status codes based on whether the server was found or not.
This method returns an Optional object that may contain the server object if it is present in the database.

```
public ResponseEntity<Optional<Server>> getServerById(long id) {
        Optional<Server> server= serverRepository.findById(id);
        if(server.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(server));
    }
```

```Response Status : 200 ok```
![get_server_byid](https://user-images.githubusercontent.com/66437295/228349702-f582e07e-e5af-4012-b682-ceb6302c731f.jpg)


```Response Status : 404 not found``` <b>When id not found</b> 
![image](https://user-images.githubusercontent.com/66437295/228351332-eab087c2-182d-447c-ab42-0725051fc0d6.png)



### 4. Get Servers by name
### Get request to /getServer/name
When this endpoint is hit in postman, it retrieves a server by its name by sending a GET request with the ID in the URL parameter. The server object is returned in a ResponseEntity with appropriate HTTP status codes based on whether the server was found or not.
```
public ResponseEntity<List<Server>> getServerByName(String name) {
        List<Server> servers= serverRepository.findByName(name);
        if(servers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(servers));

    }
```
``` Response Status : 200 ok```
![image](https://user-images.githubusercontent.com/66437295/228353644-bbfedc62-ce44-479c-981c-e41602a772d7.png)

``` Response Status : 404 not found``` <b> When name not found</b>
![image](https://user-images.githubusercontent.com/66437295/228354045-671ce597-53cd-4943-a61f-ca390b2823b1.png)



### 5. Delete Server by Id
### Delete request to /deleteServer/id
This methods in the ServerService class deletes the object with the given id passed in the url.
```
public String deleteServer(long id) {
        Optional<Server> server=serverRepository.findById(id);
        serverRepository.deleteById(id);
        return "Server deleted : " + server;
    }
```

Here server <b>deleted</b> from the database
![image](https://user-images.githubusercontent.com/66437295/228431470-0ebc1222-4409-4465-8b32-b43a1b53d923.png)

![image](https://user-images.githubusercontent.com/66437295/228432494-3a14a3fe-8df6-41ab-b09e-0d68cd44bd93.png)


### MongoDB database
![image](https://user-images.githubusercontent.com/66437295/228432800-3026f410-2fc7-49f1-8500-e42150b4b55c.png)

