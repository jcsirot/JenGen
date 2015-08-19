package com.chelonix.jenkinsplugingenerator.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

/**
 * Created with IntelliJ IDEA.
 * User: jcsirot
 * Date: 24/04/15
 * Time: 17:20
 * To change this template use File | Settings | File Templates.
 */
@ToString
public class Parameter {

    @Getter @Setter private String id;
    @Getter @Setter private String flag;
    @Getter @Setter private String delimiter;
    @Getter @Setter private String type;
    @Getter @Setter private Widget widget;

    public boolean isSpaceDelimiter() {
        return delimiter == null || StringUtils.isBlank(delimiter);
    }
}
