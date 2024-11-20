package com.cypcode.openai_microservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cypcode.openai_microservice.service.CypcodeAIService;
import java.util.*;

@RestController
@RequestMapping("intelligent")
public class ChatController {

	@Autowired
	private CypcodeAIService aiService;
	
	@GetMapping("chat/{prompt}")
	public ResponseEntity<?> chat(@PathVariable(name = "prompt") String prompt){
		return ResponseEntity.ok(Map.of("generation", aiService.chat(prompt)));
	}
}
