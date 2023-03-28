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
