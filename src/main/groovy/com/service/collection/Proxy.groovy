package com.service.collection

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="testdb")
class Proxy {

    @Id
    private String id;
    private String host;
    private int port;
    private int pingTimeout;
    private boolean pingSuccess;
    private boolean proxyInuse;

    boolean isPingSuccess() {
        return pingSuccess;
    }

    boolean isProxyInuse() {
        return proxyInuse;
    }

    void setPingSuccess(boolean pingSuccess) {
        this.pingSuccess = pingSuccess;
    }

    void setProxyInuse(boolean proxyInuse) {
        this.proxyInuse = proxyInuse;
    }

    String getId() {
        return id;
    }

    void setId(String id) {
        this.id = id;
    }

    String getHost() {
        return host;
    }

    int getPort() {
        return port;
    }

    void setHost(String host) {
        this.host = host;
    }

    void setPort(int port) {
        this.port = port;
    }

    int getPingTimeout() {
        return pingTimeout;
    }

    void setPingTimeout(int pingTimeout) {
        this.pingTimeout = pingTimeout;
    }
}
