package com.cypcode.openai_microservice.domain;

import lombok.Getter;
import lombok.Setter;
import java.util.*;
	
@Setter
@Getter
public class Generation {
	private List<ChatChoices> choices;
}
