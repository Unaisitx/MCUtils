package com.zalyx.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Server {

    private String name;
    private int playerCount;

    public Server(String name) {
        this.name = name;

        this.playerCount = 0;

        BungeeCord.getServersByName().put(name, this);
        BungeeCord.getServers().add(this);
        BungeeCord.getServersName().add(name);
    }
}
