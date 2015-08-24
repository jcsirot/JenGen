package com.chelonix.jenerator.generator;

import com.chelonix.jenerator.model.Parameter;

/**
 * Created with IntelliJ IDEA.
 * User: jcsirot
 * Date: 28/04/15
 * Time: 11:05
 * To change this template use File | Settings | File Templates.
 */
public interface ParameterGenerator {

    String generateArgs(Parameter parameter);

    String generateFieldDeclaration(Parameter parameter);

    String generateConstructorParameter(Parameter parameter);

//    String generateFieldAssignment(Parameter parameter);

    String generateWidget(Parameter parameter);

    String generateDescriptorMethod(Parameter parameter);

    String generateFinallyBlock(Parameter parameter);

}
