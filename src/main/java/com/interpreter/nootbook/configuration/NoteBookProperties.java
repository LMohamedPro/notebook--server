package com.interpreter.nootbook.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "config")
@Getter
@Setter
public class NoteBookProperties {

    private int timeOutValue;

    private String laguage;

}
