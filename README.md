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
![image](https://user-images.githubusercontent.com/66437295/228337213-da39a026-20e7-4bbe-be5b-0842751fdaeb.png)



## Rest api endpoints
- Put a server ```http://localhost:8080/createServer``` add "server" object in JSON form.

- GET servers ```http://localhost:8080/getServers``` returns a list of "server" objects.

- GET server by id ```http://localhost:8080/getServers/{id}``` returns a "server" object matching with id.

- GET servers by name ```localhost:8080/getServer/{name}``` returns a list of "server" objects matching with name.

- DELETE server by id ```http://127.0.0.1:2017/deleteServer/{id}``` Deletes a "server" object matching with id.


## Checking Api using Postman

- <h3> Adding Server </h3>

### Post request to /createServer
When this endpoint is hit in postman, the createServer() method is invoked in sprinboot controller which accepts a Server object in the request body and created the objects in the database.
The `serverService.addServer(server)` method is called with the Server object as a parameter, which adds the server to the system and returns a message indicating whether the operation was successful or not.
```
  @PostMapping("/createServer")
    public String createServer(@RequestBody Server server){
        return serverService.addServer(server);
    }
```

![create_server](https://user-images.githubusercontent.com/66437295/228340951-c348bdb6-970b-481a-a17b-076f66d7d3c4.jpg)

- <h3> Get Servers </h3>
### Get request to /getServers
When this endpoint is hit in postman, the getAllServers() method is invoked. This method returns a list of Server objects that are retrieved from the system through the serverService.getAllServers() method call. 

```
@GetMapping("/getServers")
    public ResponseEntity<List<Server>> getAllServers(){
        return serverService.getAllServers();
    }
 ```
Here we get all the servers present in database

![get_allservers](https://user-images.githubusercontent.com/66437295/228347001-2827d413-d3fd-4001-9260-bbcf00acef37.jpg)

- <h3> Get Servers by id </h3>
### Get request to /getServers/id
When this endpoint is hit, it retrieves a server by its ID by sending a GET request with the ID in the URL parameter. The server object is returned in a ResponseEntity with appropriate HTTP status codes based on whether the server was found or not.

```
public ResponseEntity<Optional<Server>> getServerById(long id) {
        Optional<Server> server= serverRepository.findById(id);
        if(server.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(server));
    }
```
This method returns an Optional object that may contain the server object if it is present in the database.

```Response Status : 200 found```
![get_server_byid](https://user-images.githubusercontent.com/66437295/228349702-f582e07e-e5af-4012-b682-ceb6302c731f.jpg)

```Response Status : 404 not found```
![image](https://user-images.githubusercontent.com/66437295/228351332-eab087c2-182d-447c-ab42-0725051fc0d6.png)


- <h3> Get Servers by name </h3>
### Get request to /getServer/name
When this endpoint is hit, it retrieves a server by its name by sending a GET request with the ID in the URL parameter. The server object is returned in a ResponseEntity with appropriate HTTP status codes based on whether the server was found or not.
```
public ResponseEntity<List<Server>> getServerByName(String name) {
        List<Server> servers= serverRepository.findByName(name);
        if(servers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(servers));

    }
```
``` Response Status : 200 found```
![image](https://user-images.githubusercontent.com/66437295/228353644-bbfedc62-ce44-479c-981c-e41602a772d7.png)

``` Response Status : 404 not found```
![image](https://user-images.githubusercontent.com/66437295/228354045-671ce597-53cd-4943-a61f-ca390b2823b1.png)



