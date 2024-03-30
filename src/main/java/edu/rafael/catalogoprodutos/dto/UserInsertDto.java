package edu.rafael.catalogoprodutos.dto;

import edu.rafael.catalogoprodutos.services.validation.UserInsertValid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@UserInsertValid
public class UserInsertDto extends UserDto{
    @NotBlank(message = "Campo obrigatório")
    @Size(min = 6, message = "Deve ter no mínimo 6 caracteres")
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
