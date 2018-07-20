package com.collection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="testdb")
public class Proxy {

    @Id
    @JsonIgnore
    private String id;
    @JsonProperty("host")
    private String host;
    @JsonProperty("port")
    private int port;
    @JsonProperty("pingTimeout")
    private int pingTimeout;
    @JsonIgnore
    private boolean pingSuccess;
    @JsonIgnore
    private boolean proxyInuse;

    public boolean isPingSuccess() {
        return pingSuccess;
    }

    public boolean isProxyInuse() {
        return proxyInuse;
    }

    public void setPingSuccess(boolean pingSuccess) {
        this.pingSuccess = pingSuccess;
    }

    public void setProxyInuse(boolean proxyInuse) {
        this.proxyInuse = proxyInuse;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getPingTimeout() {
        return pingTimeout;
    }

    public void setPingTimeout(int pingTimeout) {
        this.pingTimeout = pingTimeout;
    }
}
