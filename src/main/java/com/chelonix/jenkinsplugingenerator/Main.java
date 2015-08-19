package com.chelonix.jenkinsplugingenerator;

import net.codestory.http.WebServer;

/**
 * Created with IntelliJ IDEA.
 * User: jcsirot
 * Date: 24/04/15
 * Time: 16:46
 * To change this template use File | Settings | File Templates.
 */
public class Main {

    public static void main(String[] args) {
        new WebServer().configure(routes -> routes
            .add(ServerInfoResource.class)
            .add("/api/" + ServerInfoResource.API_VERSION, APIResource.class))
        .start(9090);
    }
}
