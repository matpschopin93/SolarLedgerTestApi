package com.solarledger.app.rest;

import com.solarledger.app.AppApplicationTests;
import com.solarledger.app.config.TestConfiguration;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.file.Files;
import java.nio.file.Paths;


@ActiveProfiles("test")
@ContextConfiguration(classes = {TestConfiguration.class})
public class RestApiTest extends AppApplicationTests {

    public static String readFileAsString(String file) throws Exception{
        return new String(Files.readAllBytes(Paths.get(file)));
    }

    @Test
    public void saveList() throws Exception{
        ClassPathResource classPathResource = new ClassPathResource("data/requestSaveExample.json");
        String test= readFileAsString(classPathResource.getFile().getAbsolutePath());

        getTestStatusOk("http://localhost:8080/saveList",test, MediaType.APPLICATION_JSON);
    }

    @Test
    public void getListinRangeTest() throws Exception{
        ClassPathResource classPathResource = new ClassPathResource("data/requestSaveExample.json");
        String test= readFileAsString(classPathResource.getFile().getAbsolutePath());

        mockMvc.perform(MockMvcRequestBuilders
                .post("http://localhost:8080/saveList").contentType(MediaType.APPLICATION_JSON).content(test));

        getTestStatusOkWithParam("http://localhost:8080/getListInRange","0","999999");
    }

    @Test
    public void saveListError() throws Exception{
        ClassPathResource classPathResource = new ClassPathResource("data/requestErrorJsonExample.json");
        String test= readFileAsString(classPathResource.getFile().getAbsolutePath());

        getTestStatusis4xx("http://localhost:8080/saveList",test, MediaType.APPLICATION_JSON);
    }
}
