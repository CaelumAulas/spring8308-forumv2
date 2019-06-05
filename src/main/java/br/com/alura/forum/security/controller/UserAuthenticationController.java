package br.com.alura.forum.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.forum.security.controller.dto.output.TokenOutputDto;
import br.com.alura.forum.security.controller.jwt.TokenManager;
import br.com.alura.forum.security.controller.jwt.UserInputDto;

@RestController
public class UserAuthenticationController {
	@Autowired
	private AuthenticationManager authManager;
	@Autowired
	private TokenManager tokenManager;
	
	
	@PostMapping(value="/api/auth", consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TokenOutputDto> authenticate(@RequestBody UserInputDto userDto) {
		Authentication notAuthenticated = userDto.buildAuthentication();
		try {
			Authentication authenticated = 
					authManager.authenticate(notAuthenticated);

			String token = tokenManager.generateToken(authenticated);
			
			 TokenOutputDto tokenDto = new TokenOutputDto("Bearer", token);
			 
			 return ResponseEntity.ok(tokenDto);
			
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
	}
}
