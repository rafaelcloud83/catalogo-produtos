package edu.rafael.catalogoprodutos.dto;

import edu.rafael.catalogoprodutos.services.validation.UserInsertValid;
import jakarta.validation.constraints.NotBlank;

@UserInsertValid
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
