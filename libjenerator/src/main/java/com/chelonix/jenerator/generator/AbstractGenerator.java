package com.chelonix.jenerator.generator;

import com.chelonix.jenerator.model.Parameter;

import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;
import java.net.URL;
import java.nio.charset.Charset;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import com.google.common.io.Resources;

/**
 * Created with IntelliJ IDEA.
 * User: jcsirot
 * Date: 28/04/15
 * Time: 11:41
 * To change this template use File | Settings | File Templates.
 */
abstract class AbstractGenerator implements ParameterGenerator {

    private MustacheFactory mf;

    protected AbstractGenerator() {
        this.mf = new DefaultMustacheFactory();
    }

    protected Mustache getTemplate(String templatePath) {
        try {
            URL url = Resources.getResource(templatePath);
            Reader reader = Resources.asCharSource(url, Charset.forName("UTF-8")).openBufferedStream();
            StringWriter writer = new StringWriter();
            return mf.compile(reader, templatePath);
        } catch (IOException ioe) {
            // FIXME
            throw new Error(ioe);
        }
    }

    protected String render(Mustache template, Object... scope) {
        StringWriter writer = new StringWriter();
        try {
            template.execute(writer, scope).flush();
        } catch (IOException ioe) {
            // FIXME
            throw new Error(ioe);
        }
        return writer.toString();
    }

    @Override
    public String generateDescriptorMethod(Parameter parameter) {
        return "";
    }

    @Override
    public String generateFinallyBlock(Parameter parameter) {
        return "";
    }

    public String generateDoFillItemsMethodName(String id) {
        return "";
    }
}
