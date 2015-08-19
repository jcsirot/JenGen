package com.chelonix.jenkinsplugingenerator;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.chelonix.jenkinsplugingenerator.model.PluginRequest;
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

    private final Templater templater = new Templater();

    @Post("/")
    public byte[] newPlugin(PluginRequest request) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try(ZipOutputStream zos = new ZipOutputStream(baos)) {
            List<PluginFile> files = templater.template(request);
            for (PluginFile file: files) {
                ZipEntry entry = new ZipEntry(request.getId() + "/" + file.getPath().replace("_id_", request.getId()));
                zos.putNextEntry(entry);
                zos.write(file.getContent().getBytes(Charset.forName("UTF-8")));
                zos.closeEntry();

            }
        }
        return baos.toByteArray();
    }
}
