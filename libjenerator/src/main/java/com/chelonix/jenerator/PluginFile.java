package com.chelonix.jenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 * User: jcsirot
 * Date: 24/04/15
 * Time: 18:30
 * To change this template use File | Settings | File Templates.
 */
@AllArgsConstructor
public class PluginFile {

    @Getter private String path;
    @Getter private String content;
}
