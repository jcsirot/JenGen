package com.chelonix.jenkinsplugingenerator.generator;

import com.chelonix.jenkinsplugingenerator.model.Parameter;
import com.github.mustachejava.Mustache;

/**
 * Created with IntelliJ IDEA.
 * User: jcsirot
 * Date: 28/04/15
 * Time: 11:03
 * To change this template use File | Settings | File Templates.
 */
public class StringParameter extends AbstractGenerator {

    @Override
    public String generateArgs(Parameter parameter) {
        Mustache template = getTemplate("template/parameter/string.argument.mustache");
        return render(template, parameter);
    }

    @Override
    public String generateFieldDeclaration(Parameter parameter) {
        return String.format("public final String %s;", parameter.getId());
    }

    @Override
    public String generateConstructorParameter(Parameter parameter) {
        return String.format("String %s", parameter.getId());
    }

    @Override
    public String generateWidget(Parameter parameter) {
        Mustache template = null;
        if ("textbox".equalsIgnoreCase(parameter.getWidget().getType())) {
            template = getTemplate("template/parameter/textbox.jelly.mustache");
        } else if ("number".equalsIgnoreCase(parameter.getWidget().getType())) {
            template = getTemplate("template/parameter/number.jelly.mustache");
        }  else if ("list".equalsIgnoreCase(parameter.getWidget().getType())) {
            template = getTemplate("template/parameter/select.jelly.mustache");
        } else {
            throw new Error(String.format("Unsupported widget type %s for type String", parameter.getWidget().getType()));
        }
        return render(template, parameter);
    }
}
