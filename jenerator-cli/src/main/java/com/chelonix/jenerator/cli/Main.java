package com.chelonix.jenerator.cli;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import com.chelonix.jenerator.JenGen;
import com.chelonix.jenerator.model.PluginRequest;
import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.docopt.Docopt;

public class Main {

    public static final String USAGE = "" +
        "Generate Jenkins plugin code from a build manifest.\n"
        + "\n"
        + "Usage:\n"
        + "  jenerator [options] MANIFEST\n"
        + "  jenerator --version\n"
        + "  jenerator --help\n"
        + "\n"
        + "Options:\n"
        + "  -o FILE, --output=FILE  Specify output file [default: ./plugin.zip]\n"
        + "  -V, --version           Show version.\n"
        + "  -h, --help              Show this screen.\n"
        + "\n"
        + "For the build manifest syntax, please check the online documentation.\n"
        + "\n";

    private static final String DEFAULT_OUTPUT = "plugin.zip";

    public static void main(String... args) throws IOException {
        Map<String, Object> opts = new Docopt(USAGE).withVersion("JenGen 1.0-SNAPSHOT").parse(args);
        File output;
        if (opts.containsKey("--output")) {
            output = new File(opts.get("--output").toString());
            if (output.isDirectory()) {
                output = new File(output, DEFAULT_OUTPUT);
            }
        } else {
            output = new File("plugin.zip");
        }

        Gson gson = new GsonBuilder().create();
        PluginRequest manifest = gson.fromJson(new FileReader(new File(opts.get("MANIFEST").toString())), PluginRequest.class);
        JenGen jenGen = new JenGen();
        byte[] zip = jenGen.generate(manifest);
        Files.write(zip, output);
    }

}
