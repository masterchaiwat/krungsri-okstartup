package com.reg.ok.okstartup;



import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = OkstartupApplication.class)
public class RegisterGetInfoTest {

	@Autowired
    private MockMvc mvc;
	
	private String username = "test2";
	private String password = "password123";
    
	@Test
	public void register_thenStatus200()
	  throws Exception {

		JSONObject json = new JSONObject();
        json.put("username", this.username);
        json.put("password", this.password);
        json.put("firstname", "fname");
        json.put("lastname", "lname");
        json.put("email", "abc@gmail.com");
        json.put("address", "16");
        json.put("phone", "0841234567");
        json.put("salary", 17000);

        
	    mvc.perform(MockMvcRequestBuilders.post("/register")
	      .contentType(MediaType.APPLICATION_JSON)
	      .content(json.toString()))
	      .andExpect(MockMvcResultMatchers.status().isOk());
	    
	}
	
	@Test
	public void authenticateAndgetRegisterInfo() throws Exception {
	    

		//authenticate
	    JSONObject loginData = new JSONObject();
	    loginData.put("username", this.username);
	    loginData.put("password", this.password);

	    MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/authenticate").contentType(MediaType.APPLICATION_JSON).accept("application/json;charset=UTF-8")
	            .content(loginData.toString()))
	            .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

	    
	    String response = result.getResponse().getContentAsString();
	    
	    
	    //get access token
	    JacksonJsonParser jsonParser = new JacksonJsonParser();
	    String token = jsonParser.parseMap(response).get("token").toString();
	    
	    
	    JSONObject expectRegisterInfo = new JSONObject();
	    expectRegisterInfo.put("firstname", "fname");
	    expectRegisterInfo.put("lastname", "lname");
	    expectRegisterInfo.put("email", "abc@gmail.com");
	    expectRegisterInfo.put("address", "16");
	    expectRegisterInfo.put("phone", "0841234567");
	    expectRegisterInfo.put("registerDate", "2021-02-10");
	    expectRegisterInfo.put("referenceCode", "202102104567");
	    expectRegisterInfo.put("memberType", "Silver");
	    
	    //get info and pass access token to header
	    result = mvc.perform(MockMvcRequestBuilders.post("/getUserInfo")
					    	.accept("application/json;charset=UTF-8")
					        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
					        .andExpect(MockMvcResultMatchers.status().isOk())
					        .andExpect(MockMvcResultMatchers.content().json(expectRegisterInfo.toString())).andReturn();
	    
	    response = result.getResponse().getContentAsString();
	    System.out.println(response);
	    
	}

	
	
}
