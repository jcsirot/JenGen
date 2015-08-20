package com.chelonix.jengen.cli;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import com.chelonix.jengen.JenGen;
import com.chelonix.jengen.model.PluginRequest;
import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.docopt.Docopt;

/**
 * Created with IntelliJ IDEA.
 * User: jcsirot
 * Date: 20/08/15
 * Time: 11:22
 * To change this template use File | Settings | File Templates.
 */
public class Main {

    public static final String USAGE = "" +
            "JenGen\n"
            + "\n"
            + "Usage:\n"
            + "  jengen <MANIFEST> (-o FILE | --output=FILE)\n"
            + "  jengen (-h | --help)\n"
            + "  jengen --version\n"
            + "\n"
            + "Options:\n"
            + "  -h --help                 Show this screen.\n"
            + "  --version                 Show version.\n"
            + "  -o FILE --output=FILE     Specify output file [default: ./plugin.zip]\n"
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
        PluginRequest manifest = gson.fromJson(new FileReader(new File(opts.get("<MANIFEST>").toString())), PluginRequest.class);
        JenGen jenGen = new JenGen();
        byte[] zip = jenGen.generate(manifest);
        Files.write(zip, output);
    }

}
