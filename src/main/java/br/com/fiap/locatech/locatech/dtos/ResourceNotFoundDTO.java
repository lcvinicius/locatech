package br.com.fiap.locatech.locatech.dtos;

public record ResourceNotFoundDTO(String message, int status) { 
    public ResourceNotFoundDTO(String message) {
        this(message, 404);
    }

}
