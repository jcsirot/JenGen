package com.chelonix.jengen.server;

import net.codestory.http.annotations.Get;
import net.codestory.http.annotations.Prefix;

/**
 * Created with IntelliJ IDEA.
 * User: jcsirot
 * Date: 24/04/15
 * Time: 16:54
 * To change this template use File | Settings | File Templates.
 */
@Prefix("/api/info")
public class ServerInfoResource {

    private static final String VERSION = "1.0-SNAPSHOT";
    static final String API_VERSION = "1";

    private static final ServerInfo SERVER_INFO = new ServerInfo(VERSION, API_VERSION);

    @Get("/")
    public ServerInfo getInfo() {
        return SERVER_INFO;
    }

    public static final class ServerInfo {
        public final String version;
        public final String api_version;

        public ServerInfo(String version, String api_version) {
            this.api_version = api_version;
            this.version = version;
        }
    }
}
