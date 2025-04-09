package br.com.fiap.locatech.locatech.entities;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {
    private Long id;
    private String brand;
    private String model;
    private String plate;
    private String color;
    private int yearManufacture;
    private BigDecimal dailyValue;


}
