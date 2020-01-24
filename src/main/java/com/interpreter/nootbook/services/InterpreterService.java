package com.interpreter.nootbook.services;


import com.interpreter.nootbook.models.Execution;
import com.interpreter.nootbook.models.InterpreterRequest;

public interface InterpreterService {

    /**
     * Execute python code
     *
     * The service preserve the execution context based on the session id.
     *
     *
     * @param request code and sessionId.
     *
     * @throws RuntimeException the interpreter doesn't work / the format of the code is invalid / code other than python.
     *
     * @return The output of the code execution.
     */
    Execution execute(InterpreterRequest request) throws RuntimeException;
}
