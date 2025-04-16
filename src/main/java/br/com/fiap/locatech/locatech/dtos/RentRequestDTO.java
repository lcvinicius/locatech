package br.com.fiap.locatech.locatech.dtos;

import java.time.LocalDate;

public record RentRequestDTO(
    Long peopleId,
    Long vehicleId,
    
    LocalDate startDate,
    LocalDate endDate
) {
}
