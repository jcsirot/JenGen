package com.chelonix.jengen.server;

import static com.google.common.io.Resources.*;

import net.codestory.http.annotations.Get;
import net.codestory.http.annotations.Prefix;

import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: jcsirot
 * Date: 24/04/15
 * Time: 16:54
 * To change this template use File | Settings | File Templates.
 */
@Prefix("/api/info")
public class ServerInfoResource {

    private final ServerInfo serverInfo;

    public ServerInfoResource() {
	try {
	    Properties props = new Properties();
	    props.load(getResource("properties/version.properties").openStream());
	    String version = props.getProperty("app.version");
	    String buildNumber = props.getProperty("app.buildNumber");
	    String apiVersion = props.getProperty("api.version");
	    serverInfo = new ServerInfo(version, buildNumber, apiVersion);
	} catch (IOException ioe) {
	    throw new RuntimeException("Unable to load properties", ioe);
	}
    }

    @Get("/")
    public ServerInfo getInfo() {
        return serverInfo;
    }

    public static final class ServerInfo {
        public final String version;
	public final String build_number;
        public final String api_version;

        public ServerInfo(String version, String build_number, String api_version) {
            this.api_version = api_version;
	    this.build_number = build_number;
            this.version = version;
        }
    }
}
