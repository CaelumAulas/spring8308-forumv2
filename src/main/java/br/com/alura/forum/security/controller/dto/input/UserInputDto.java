package br.com.alura.forum.security.controller.dto.input;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

public class UserInputDto {

	private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public Authentication buildAuthentication() {
		return new UsernamePasswordAuthenticationToken(this.email, this.password); 
	}

}
