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

    public Server() {
        // TODO Auto-generated constructor stub
    }

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
