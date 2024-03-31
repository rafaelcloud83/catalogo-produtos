package edu.rafael.catalogoprodutos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class NewPasswordDto {
    @NotBlank(message = "Campo obrigatório")
    private String token;
    @NotBlank(message = "Campo obrigatório")
    @Size(min = 6, message = "Deve ter no mínimo 6 caracteres")
    private String password;

    public NewPasswordDto() {
    }

    public NewPasswordDto(String token, String password) {
        this.token = token;
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
