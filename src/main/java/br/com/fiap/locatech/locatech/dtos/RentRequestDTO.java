package br.com.fiap.locatech.locatech.dtos;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public record RentRequestDTO(
   
@NotNull(message = "People ID is required")
    Long peopleId,
    
    @NotNull(message = "Vehicle ID is required")
    Long vehicleId,
    
    @NotNull(message = "Start date is required")
    LocalDate startDate,
    
    @NotNull(message = "End date is required")
    LocalDate endDate
) {
}

