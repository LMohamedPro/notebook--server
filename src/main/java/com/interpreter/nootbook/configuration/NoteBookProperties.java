package com.interpreter.nootbook.configuration;

import lombok.Getter;
import org.springframework.stereotype.Component;



@Component
@Getter
public class NoteBookProperties {

    private int timeOutValue = 5000;

    private String laguage = "python";

}
