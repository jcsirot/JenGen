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
public class PasswordParameter extends AbstractGenerator {

    @Override
    public String generateArgs(Parameter parameter) {
        Mustache template = getTemplate("template/parameter/password.argument.mustache");
        return render(template, parameter);
    }

    @Override
    public String generateFieldDeclaration(Parameter parameter) {
        if ("password".equalsIgnoreCase(parameter.getWidget().getType())) {
            return String.format("public final hudson.util.Secret %s;", parameter.getId());
        } else {
            return String.format("public final String %s;", parameter.getId());
        }
    }

    @Override
    public String generateConstructorParameter(Parameter parameter) {
        return String.format("hudson.util.Secret %s", parameter.getId());
    }

    @Override
    public String generateWidget(Parameter parameter) {
        Mustache template = getTemplate("template/parameter/password.jelly.mustache");
        return render(template, parameter);

    }
}
