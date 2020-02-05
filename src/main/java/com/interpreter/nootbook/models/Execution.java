package com.interpreter.nootbook.models;

import lombok.Getter;
import lombok.Setter;
import org.graalvm.polyglot.Context;

import java.io.ByteArrayOutputStream;


@Setter
@Getter
public class Execution {

    /**
     * the actual context.
     */
    private Context context;

    /**
     * The output of the execution.
     */
    private ByteArrayOutputStream outputStream;


    /**
     * Constructor to create new context ans new ByteArrayOutputStream
     */
    public Execution() {
        this.outputStream =  new ByteArrayOutputStream();
        this.context = Context.newBuilder("python").out(this.outputStream).err(this.outputStream).build();

    }
}
