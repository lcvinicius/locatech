package br.com.fiap.locatech.locatech.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.fiap.locatech.locatech.dtos.RentRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rent {

    private Long id;

    private Long peopleId;

    private Long vehicleId;

    private String vehicleModel;

    private String peopleCpf;

    private String peopleName;

    private LocalDate startDate;

    private LocalDate endDate;

    private BigDecimal totalValue;

    public Rent (RentRequestDTO rentDTO, BigDecimal value){
        this.peopleId = rentDTO.peopleId();
        this.vehicleId = rentDTO.vehicleId();
        this.startDate = rentDTO.startDate();
        this.endDate =rentDTO.endDate();
        this.totalValue = value; 
    }


}
