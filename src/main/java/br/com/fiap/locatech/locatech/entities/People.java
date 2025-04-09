package br.com.fiap.locatech.locatech.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class People {
    private Long id;
    private String name;
    private String cpf;
    private String email;
    private String phone;
   
}
