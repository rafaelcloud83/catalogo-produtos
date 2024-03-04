package edu.rafael.catalogoprodutos.dto;

import jakarta.validation.constraints.NotBlank;

public class UserInsertDto extends UserDto{
    @NotBlank(message = "Campo obrigatório")
    private String password;

    UserInsertDto(){
        super();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
