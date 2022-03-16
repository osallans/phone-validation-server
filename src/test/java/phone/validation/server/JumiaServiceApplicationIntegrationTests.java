package phone.validation.server;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import phone.validation.server.controllers.APIController;
import phone.validation.server.dto.CustomerSearchDto;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
class JumiaServiceApplicationIntegrationTests {

	@Autowired
    MockMvc mockMvc;
	@Autowired
    private APIController controller;
   
	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}
	//////////////////////////////////// API -> Health ////////////////////////////////////
	//Testing verify health API
	@Test
	public void test_health() throws Exception {
	
		mockMvc.perform(get("/api/health"))
				.andExpect(status().isOk());
	}
	
	//////////////////////////////////// API -> GetCountries ////////////////////////////////////
	//Testing processing data works
	//Testing verify health API
	@Test
	public void test_countries() throws Exception {
	
		mockMvc.perform(get("/api/countries"))
				.andExpect(status().isOk());
	}
	//////////////////////////////////// API -> GetCustomers ////////////////////////////////////
	//Testing processing data works
	//Testing verify health API
	@Test
	public void test_customers() throws Exception {

		Object input = new Object() {
            public final String countryId = "-1";
			public final String valid = "-1";
			public final String pageSize = "5";
			public final String pageIndex = "0";

        };
		ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(input);

		MvcResult result=mockMvc.perform(post("/api/customers").content(json).contentType(MediaType.APPLICATION_JSON)
		.content(json)
		.characterEncoding("utf-8"))
				.andExpect(status().isOk()).andReturn();
	}
	//////////////////////////////////// Function -> GetCustomers ////////////////////////////////////

}
