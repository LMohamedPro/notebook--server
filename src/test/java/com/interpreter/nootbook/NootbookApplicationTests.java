package com.interpreter.nootbook;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.interpreter.nootbook.models.InterpreterRequest;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class NootbookApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;


    @Test
    public void workingGood() throws Exception {
        InterpreterRequest reauest = new InterpreterRequest();
        reauest.setCode("%python \n print('Hello')");
        this.mockMvc.perform(
                            post("/execute")
                                    .content(mapper.writeValueAsString(reauest))
                                    .contentType(MediaType.APPLICATION_JSON))
                            .andExpect(status().isOk())
                            .andExpect(jsonPath("$.result", is("Hello\n")));


    }

    @Test
    public void notWorkingGoodLanguageUnsupported() throws Exception {
        InterpreterRequest reauest = new InterpreterRequest();
        reauest.setCode("%test \n print('Hello')");
        this.mockMvc.perform(
                post("/execute")
                        .content(mapper.writeValueAsString(reauest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is("Language Unsupported")));


    }
    @Test
    public void notWorkingGoodError() throws Exception {
        InterpreterRequest reauest = new InterpreterRequest();
        reauest.setCode("%python \n print(a)");
        this.mockMvc.perform(
                post("/execute")
                        .content(mapper.writeValueAsString(reauest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is("NameError: name 'a' is not defined")));

    }


}
