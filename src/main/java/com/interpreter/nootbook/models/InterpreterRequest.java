package com.interpreter.nootbook.models;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;


@Setter
@Getter
public class InterpreterRequest {


    /**
     * Python script.
     */
    @NotEmpty
    private String code;

    /**
     * The session.
     */
    private String sessionId;

}
