package com.chelonix.jenerator.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created with IntelliJ IDEA.
 * User: jcsirot
 * Date: 24/04/15
 * Time: 17:14
 * To change this template use File | Settings | File Templates.
 */
@ToString
public class PluginRequest {

    @Getter @Setter private String id;
    @Getter @Setter private String name;
    @Getter @Setter private String description;
    @Getter @Setter private List<Parameter> parameters;
    @Getter @Setter private Command command;
}
