package com.azqore.demo.api.dto;

public class ServerInfo {

    private String version;
    private String applicationName;

    public ServerInfo() {
    }

    public ServerInfo(String version, String applicationName) {
        this.version = version;
        this.applicationName = applicationName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }
}
