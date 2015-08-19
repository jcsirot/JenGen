package com.chelonix.jenkinsplugingenerator.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Created with IntelliJ IDEA.
 * User: jcsirot
 * Date: 24/04/15
 * Time: 17:32
 * To change this template use File | Settings | File Templates.
 */
public class Widget {

    @Getter @Setter private String type;
    @Getter @Setter private List<WidgetLabel> label;
    @Getter @Setter private List<WidgetSelectOption> options;

    public WidgetLabel defaultLabel() {
        return label.get(0);
    }
}
