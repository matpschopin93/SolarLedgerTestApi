package com.solarledger.app;

import com.solarledger.app.config.TestConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@ContextConfiguration(classes ={TestConfiguration.class})
public abstract class AppApplicationTests {

	@Autowired
	protected MockMvc mockMvc;

	protected void getTestStatusOk(String URI, String jsonTest, MediaType mediaType) throws Exception{

		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
				.post(URI).contentType(mediaType).content(jsonTest));
		resultActions.
				andExpect(MockMvcResultMatchers.status().isOk()).
				andDo(MockMvcResultHandlers.print());

	}

	protected void getTestStatusOkWithParam(String URI, String start,String end) throws Exception{

		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
				.get(URI).param("start",start).param("end",end));
		resultActions.
				andExpect(MockMvcResultMatchers.status().isOk()).
				andDo(MockMvcResultHandlers.print());

	}
	protected void getTestStatusis4xx(String URI, String jsonTest, MediaType mediaType) throws Exception{

		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
				.post(URI).contentType(mediaType).content(jsonTest));
		resultActions.
				andExpect(MockMvcResultMatchers.status().is4xxClientError()).
				andDo(MockMvcResultHandlers.print());

	}

}
