package com.chelonix.jengen.server;

import com.chelonix.jengen.JenGen;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.chelonix.jengen.model.PluginRequest;
import net.codestory.http.annotations.Post;
import net.codestory.http.annotations.Prefix;

/**
 * Created with IntelliJ IDEA.
 * User: jcsirot
 * Date: 24/04/15
 * Time: 17:07
 * To change this template use File | Settings | File Templates.
 */
@Prefix("/plugin")
public class APIResource {

    private final JenGen jengen = new JenGen();

    @Post("/")
    public byte[] newPlugin(PluginRequest request) throws IOException {
	return jengen.generate(request);
    }
}
