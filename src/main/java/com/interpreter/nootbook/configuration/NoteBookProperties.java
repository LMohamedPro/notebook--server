package com.interpreter.nootbook.configuration;

import lombok.Getter;
import org.springframework.stereotype.Component;



@Component
@Getter
public class NoteBookProperties {

    private int timeOutValue = 10000;

    private String laguage = "python";

}
