package com.chelonix.jenerator;

import com.chelonix.jenerator.generator.BooleanParameter;
import com.chelonix.jenerator.generator.ParameterGenerator;
import com.chelonix.jenerator.generator.PasswordParameter;
import com.chelonix.jenerator.generator.StringParameter;
import com.chelonix.jenerator.model.Parameter;
import com.chelonix.jenerator.model.PluginRequest;

import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import com.google.common.io.Resources;

/**
 * Created with IntelliJ IDEA.
 * User: jcsirot
 * Date: 24/04/15
 * Time: 18:03
 * To change this template use File | Settings | File Templates.
 */
public class Templater {

    private static final String[] TEMPLATE_PATHS = new String[] {
            "template/pom.xml.mustache",
            "template/src/main/java/org/jenkinsci/plugins/chelonix/_id_/CustomToolInstallation.java.template",
            "template/src/main/resources/org/jenkinsci/plugins/chelonix/_id_/CustomToolInstallation/config.jelly.template",
            "template/src/main/java/org/jenkinsci/plugins/chelonix/_id_/CustomToolBuilder.java.template",
            "template/src/main/resources/org/jenkinsci/plugins/chelonix/_id_/CustomToolBuilder/config.jelly.mustache",
    };

    private final Map<String, Mustache> compiledTemplates = new HashMap<>();

    public Templater() {
        MustacheFactory mf = new DefaultMustacheFactory();
        for (String path: TEMPLATE_PATHS) {
            try {
                URL url = Resources.getResource(path);
                Reader reader = Resources.asCharSource(url, Charset.forName("UTF-8")).openBufferedStream();
                compiledTemplates.put(path, mf.compile(reader, path));
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

    public List<PluginFile> template(PluginRequest request) throws IOException {
        ArrayList<PluginFile> files = new ArrayList<>();
        for (String path: compiledTemplates.keySet()) {
            Mustache mustache = compiledTemplates.get(path);
            StringWriter writer = new StringWriter();
            mustache.execute(writer, new Object[] {request, new ParameterTemplater(request)}).flush();
            files.add(new PluginFile(path.substring(9, path.lastIndexOf('.')), writer.toString()));
        }
        return files;
    }

    private static class ParameterTemplater
    {
        private final PluginRequest request;
        private final MustacheFactory mf = new DefaultMustacheFactory();

        private static final Map<String, ParameterGenerator> generators = new HashMap<String, ParameterGenerator>() {{
            put("string", new StringParameter());
            put("boolean", new BooleanParameter());
            put("password", new PasswordParameter());
        }};

        public ParameterTemplater(PluginRequest request)
        {
            this.request = request;
        }

//        private Mustache getTemplate(String templatePath, String... args) {
//            try {
//                URL url = Resources.getResource(String.format(templatePath, args));
//                Reader reader = Resources.asCharSource(url, Charset.forName("UTF-8")).openBufferedStream();
//                StringWriter writer = new StringWriter();
//                return mf.compile(reader, templatePath);
//            } catch (IOException ioe) {
//                throw new Error(ioe);
//            }
//        }

        private Parameter getParameterById(String id) {
            Parameter parameter = request.getParameters().stream()
                    .filter(p -> id.trim().equals(p.getId()))
                    .findFirst()
                    .get();
            return parameter;
        }

//        private String render(Mustache template, Object... scopes) {
//            StringWriter writer = new StringWriter();
//            try {
//                template.execute(writer, scopes).flush();
//            } catch (IOException ioe) {
//            }
//            return writer.toString();
//        }

        public Function<String, String> generateArgs = new Function<String, String>()
        {
            @Override
            public String apply(String id)
            {
                Parameter parameter = getParameterById(id);
                return generators.get(parameter.getType()).generateArgs(parameter);
            }
        };

        public Function<String, String> constructor = new Function<String, String>()
        {
            @Override
            public String apply(String s)
            {
                List<String> constructorParameters = request.getParameters().stream()
                        .map(p -> generators.get(p.getType()).generateConstructorParameter(p))
                        .collect(Collectors.toList());
                return String.join(",", constructorParameters);
            }
        };

        public Function<String, String> generateFieldDeclaration = new Function<String, String>()
        {
            @Override
            public String apply(String id)
            {
                Parameter parameter = getParameterById(id);
                return generators.get(parameter.getType()).generateFieldDeclaration(parameter);
            }
        };

//        public Function<String, String> fieldAssignment = new Function<String, String>()
//        {
//            @Override
//            public String apply(String id)
//            {
//                Parameter parameter = getParameterById(id);
//                return String.format("this.%s = %s;", parameter.getId(), parameter.getId());
//            }
//        };

        public Function<String, String> generateWidget = new Function<String, String>()
        {
            @Override
            public String apply(String id)
            {
                Parameter parameter = getParameterById(id);
                return generators.get(parameter.getType()).generateWidget(parameter);
            }
        };
    }
}
