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
public class BooleanParameter extends AbstractGenerator {

    @Override
    public String generateArgs(Parameter parameter) {
        Mustache template = getTemplate("template/parameter/boolean.argument.mustache");
        return render(template, parameter);
    }

    @Override
    public String generateFieldDeclaration(Parameter parameter) {
        return String.format("public final boolean %s;", parameter.getId());
    }

    @Override
    public String generateConstructorParameter(Parameter parameter) {
        return String.format("boolean %s", parameter.getId());
    }

    @Override
    public String generateWidget(Parameter parameter) {
        Mustache template = getTemplate("template/parameter/checkbox.jelly.mustache");
        return render(template, parameter);
    }
}
