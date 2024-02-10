package edu.rafael.catalogoprodutos.services.exceptions;

public class EntitiesNotFoundException extends RuntimeException{
    public EntitiesNotFoundException(String msg){
        super(msg);
    }
}
