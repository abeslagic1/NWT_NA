package com.etf.SystemEvents;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;

@Entity 
@Table(name = "system_events")
public class SystemEvents {

    @Id
    @GeneratedValue
    Integer id;
    private String timestamp;
    private String microserviceName;
    private String userName;
    private String action;
    private String resource;
    private String responseType;
    
    public SystemEvents() {}

    public SystemEvents(String timestamp, String microserviceName, String user, String action, String resource, String responseType) {

        this.timestamp = timestamp;
        this.microserviceName = microserviceName;
        this.userName = user;
        this.action = action;
        this.resource = resource;
        this.responseType = responseType;
    }

    public Integer getId() {
        return id;
    }
    public String getTimeStamp() {
        return timestamp;
    }
    public String getMicroserviceName() {
        return microserviceName;
    }
    public String getUser() {
        return userName;
    }
    public String getAction() {
        return action;
    }
    public String getResource() {
        return resource;
    }
    public String getResponseType() {
        return responseType;
    }
}
