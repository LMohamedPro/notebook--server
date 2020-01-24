package com.interpreter.nootbook.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InterpreterResponse {

    /**
     * The output of the code execution.
     */
    private String result;

    /**
     * The session.
     */
    private String sessionId;

}
