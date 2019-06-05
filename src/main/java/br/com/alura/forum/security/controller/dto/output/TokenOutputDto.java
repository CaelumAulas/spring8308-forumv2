package br.com.alura.forum.security.controller.dto.output;

public class TokenOutputDto {
	private String tokenType;
    private String token;

    public TokenOutputDto(String tokenType, String token) {
        this.tokenType = tokenType;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public String getTokenType() {
        return tokenType;
    }

}
