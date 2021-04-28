package com.unice.benchai.android_tp02;
import java.net.URI;

public class Préférences {
    private String userName = "Default username";
    private URI serverURI = URI.create("http://2.15.255.168:10101");

    public Préférences() {

    }

    public URI getServerURI() {
        return serverURI;
    }

    public void setServerURI(String uri) {
        this.serverURI = URI.create(uri);
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return this.userName;
    }
}
