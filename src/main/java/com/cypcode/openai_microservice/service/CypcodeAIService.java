package com.cypcode.openai_microservice.service;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cypcode.openai_microservice.domain.Generation;

import java.util.*;

@Service
public class CypcodeAIService {
	 @Value("${spring.ai.openai.api-key}")
	    private String apiKey;

	    @Value("${spring.ai.openai.chat.base-url}")
	    private String apiUrl;

	    @Autowired
	    private RestTemplate restTemplate;
	    
	public String chat(String prompt) {
		 HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        headers.setBearerAuth(apiKey);

	        // Request Body
	        Map<String, Object> body = Map.of(
	            "model", "gpt-3.5-turbo", // or another model like gpt-3.5-turbo
	            "messages", List.of(
//	                Map.of("role", "system", "content", "You are a helpful assistant."),
	                Map.of("role", "user", "content", prompt)
	            )
	        );

	        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

	        // Send POST request
	        ResponseEntity<Generation> response = restTemplate.postForEntity(apiUrl, entity, Generation.class);

	        if (response.getStatusCode().is2xxSuccessful()) {
	            return response.getBody().getChoices().get(0).getMessage().getContent();
	        } else {
	            throw new RuntimeException("OpenAI API call failed: " + response.getStatusCode());
	        }
	}
}
