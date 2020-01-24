package com.interpreter.nootbook.configuration;

import lombok.Getter;
import org.springframework.stereotype.Component;



@Component
@Getter
public class NoteBookProperties {

    private int TimeOutValue = 10000;

}
