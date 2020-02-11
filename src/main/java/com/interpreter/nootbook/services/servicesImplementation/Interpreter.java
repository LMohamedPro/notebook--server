package com.interpreter.nootbook.services.servicesImplementation;


import com.interpreter.nootbook.configuration.NoteBookProperties;
import com.interpreter.nootbook.exceptions.InvalidRequestFormatException;
import com.interpreter.nootbook.exceptions.LanguageUnsupportedException;
import com.interpreter.nootbook.exceptions.TimeOutException;
import com.interpreter.nootbook.models.Execution;
import com.interpreter.nootbook.models.InterpreterRequest;
import com.interpreter.nootbook.services.InterpreterService;
import lombok.RequiredArgsConstructor;
import org.graalvm.polyglot.PolyglotException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class Interpreter implements InterpreterService {

    private final Pattern pattern = Pattern.compile("%(\\w+)\\s+(.*)");
    private Map<String, Execution> sessionExecution = new HashMap<>();

    @Autowired
    private  NoteBookProperties noteBookProperties;


    @Override
    public Execution execute(InterpreterRequest request) throws RuntimeException {

        Timer timer = new Timer(true);
        final Execution finalContext ;
        Execution context = null;
        try {

            String language ;
            String code ;

            Matcher matcher = pattern.matcher(request.getCode());

            if (matcher.matches()) {
                language = matcher.group(1);
                code = matcher.group(2);
            }else{
                throw new InvalidRequestFormatException("Invalid Request Format");
            }

            if (!language.equals(noteBookProperties.getLaguage())) {
                throw new LanguageUnsupportedException("Language Unsupported");
            }

            context = this.sessionExecution.get(request.getSessionId());
            if (context == null) {
                context = new Execution();
                this.sessionExecution.put(request.getSessionId(), context);
            }

            context.getContext().enter();
            context.getOutputStream().reset();
            finalContext = context;

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    finalContext.getContext().close(true);
                    System.out.println("closed");
                }
            }, noteBookProperties.getTimeOutValue() );

            finalContext.getContext().eval(language, code);

        } catch (PolyglotException e) {

            if (e.isCancelled()) {
                sessionExecution.remove(request.getSessionId());
                throw new TimeOutException("Request taking too long to execute, Context Closed and removed");
            }

            throw new RuntimeException(e.getMessage());

        }finally {

            if(context != null){
                context.getContext().leave();
            }
            timer.cancel();
            timer.purge();
        }

        return finalContext;
    }





}
