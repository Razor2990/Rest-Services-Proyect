package com.proyect.services.controllers;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.proyect.services.decorator.DecoratedUserService;
import com.proyect.services.entitys.MessageResponse;
import com.proyect.services.entitys.User;
import com.proyect.services.exceptions.ResourceException;
import com.proyect.services.service.IUserService;
import com.proyect.services.utils.EnumStatusResponse;

@RestController
@RequestMapping("user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	private final RestTemplate userRestTemplate = new RestTemplate();

	@Value("${client.endpoint}")
	private String restUrl;

	@Autowired
	@Qualifier("decoratedUserService")
	private IUserService<?> iUserService;

	@Autowired
	private DecoratedUserService decoratedUserService;

	@GetMapping
	public ResponseEntity<Map<String, Object>> getUser() {
		try {
			Map<String, Object> responseMap = decoratedUserService.getAllWithResponse();

			return ResponseEntity.ok(responseMap);

		} catch (Exception e) {
			EnumStatusResponse errorStatusResponse = EnumStatusResponse.CONFLICT;

			Map<String, Object> errorMap = new HashMap<>();
			errorMap.put("messageResponse",
					new MessageResponse(errorStatusResponse.getCode(), errorStatusResponse.getMessage()));

			return ResponseEntity.status(errorStatusResponse.getCode()).body(errorMap);
		}

	}

	@GetMapping("/{id}")
	public ResponseEntity<Map<String, Object>> getUserById(@PathVariable long id) {
		try {
			Map<String, Object> responseMap = decoratedUserService.findByIdResponse(id);

			return ResponseEntity.ok(responseMap);

		} catch (Exception e) {
			EnumStatusResponse errorStatusResponse = EnumStatusResponse.CONFLICT;

			Map<String, Object> errorMap = new HashMap<>();
			errorMap.put("messageResponse",
					new MessageResponse(errorStatusResponse.getCode(), errorStatusResponse.getMessage()));

			return ResponseEntity.status(errorStatusResponse.getCode()).body(errorMap);
		}

	}

	@PostMapping
	public ResponseEntity<Map<String, Object>> save(@Validated @RequestBody User user) {
		
		logger.info("Recibimos al usuario: " + user.toString());

		try {
			ResponseEntity<User> response = userRestTemplate.getForEntity(restUrl, User.class);
			Map<String, Object> responseMap = decoratedUserService.saveResponse(response.getBody());

			return ResponseEntity.ok(responseMap);

		} catch (ResourceException e) {
			EnumStatusResponse errorStatusResponse = EnumStatusResponse.CONFLICT;

			Map<String, Object> errorMap = new HashMap<>();
			errorMap.put("messageResponse",
					new MessageResponse(errorStatusResponse.getCode(), errorStatusResponse.getMessage()));

			return ResponseEntity.status(errorStatusResponse.getCode()).body(errorMap);
		} catch (HttpClientErrorException e) {
			EnumStatusResponse errorStatusResponse = EnumStatusResponse.BAD_REQUEST_CLIENT;

			Map<String, Object> errorMap = new HashMap<>();
			errorMap.put("messageResponse",
					new MessageResponse(errorStatusResponse.getCode(), errorStatusResponse.getMessage()));

			return ResponseEntity.status(errorStatusResponse.getCode()).body(errorMap);
		}
	}

	@PutMapping
	public ResponseEntity<Map<String, Object>> update(@Validated @RequestBody User user) {
		try {
			Map<String, Object> responseMap = decoratedUserService.updateResponse(user);

			return ResponseEntity.ok(responseMap);

		} catch (ResourceException e) {
			EnumStatusResponse errorStatusResponse = EnumStatusResponse.CONFLICT;

			Map<String, Object> errorMap = new HashMap<>();
			errorMap.put("messageResponse",
					new MessageResponse(errorStatusResponse.getCode(), errorStatusResponse.getMessage()));

			return ResponseEntity.status(errorStatusResponse.getCode()).body(errorMap);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Object>> delete(@PathVariable long id) {
		try {
			
			Map<String, Object> responseMap = decoratedUserService.deleteResponse(id);
			
			return ResponseEntity.ok(responseMap);
			
		} catch (ResourceException e) {
			
			EnumStatusResponse errorStatusResponse = EnumStatusResponse.CONFLICT;

			Map<String, Object> errorMap = new HashMap<>();
			errorMap.put("messageResponse",
					new MessageResponse(errorStatusResponse.getCode(), errorStatusResponse.getMessage()));

			return ResponseEntity.status(errorStatusResponse.getCode()).body(errorMap);
		}
	}

}
