package com.kodlamaio.rentalservice.business.dto.responses;

import com.kodlamaio.commonpackage.utils.enums.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetCarResponse
{
    private int modelYear;
    private String plate;
    private double dailyPrice;
    private String modelName;
    private String brandName;
}