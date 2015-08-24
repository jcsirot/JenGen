package com.chelonix.jenerator;

import com.chelonix.jenerator.model.PluginRequest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class JenGen {

    private final Templater templater = new Templater();

    public byte[] generate(PluginRequest request) throws IOException {
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
