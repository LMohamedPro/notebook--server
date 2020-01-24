package com.interpreter.nootbook.controller;


import com.interpreter.nootbook.models.Execution;
import com.interpreter.nootbook.models.InterpreterRequest;
import com.interpreter.nootbook.models.InterpreterResponse;
import com.interpreter.nootbook.services.InterpreterService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Random;

@RestController
@RequiredArgsConstructor
public class InterpreterController {

    @Autowired
    private  InterpreterService service;


    /**
     * endpoint that accepts a JSON object.
     * <p>
     * It accept a block of code and execute it then forward the output
     * <p>
     * Subsequent calls can be run in the same execution context
     * if a valid session id is present in the request.
     * <p>
     * Only python is accepted as languages.
     *
     * @param request The piece of code to execute and the session id.
     * @return InterpreterResponse.
     */
    @PostMapping("/execute")
    public InterpreterResponse execute(@Valid @RequestBody InterpreterRequest request) {

        InterpreterResponse response = new InterpreterResponse();

        Random rand = new Random();

        if (request.getSessionId() == null) {

            request.setSessionId(String.valueOf(rand.nextLong()));
            response.setSessionId(request.getSessionId());
        }
        try {

            Execution execution = service.execute(request);
            response.setResult(execution.getOutputStream().toString());
            response.setSessionId(request.getSessionId());

        } catch (RuntimeException e) {
            response.setResult(e.getMessage());
            response.setSessionId(request.getSessionId());
        }


        return response;
    }

}
