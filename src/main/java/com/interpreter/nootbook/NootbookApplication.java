package com.interpreter.nootbook;

import com.interpreter.nootbook.configuration.NoteBookProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableConfigurationProperties(NoteBookProperties.class)
public class NootbookApplication {

    public static void main(String[] args) {

        final ApplicationContext ctx = SpringApplication.run(NootbookApplication.class, args);
        final NoteBookProperties noteBookProperties = ctx.getBean(NoteBookProperties.class);
        System.out.println(noteBookProperties.getLaguage());
        System.out.println(noteBookProperties.getTimeOutValue());
    }

}
