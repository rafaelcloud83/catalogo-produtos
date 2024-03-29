package edu.rafael.catalogoprodutos.dto;

import edu.rafael.catalogoprodutos.entities.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CategoryDto {
    private Long id;
    @NotBlank(message = "Campo obrigatório")
    @Size(min = 3, max = 50, message = "Deve ter entre 3 e 50 caracteres")
    private String name;

    public CategoryDto() {
    }

    public CategoryDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CategoryDto(Category category){
        this.id = category.getId();
        this.name = category.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
