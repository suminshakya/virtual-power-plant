package com.proshore.vpps.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class BatterySummaryDto {

    private int totalBatteries;
    private int totalCapacity;
    private Double avgCapacity;
    private List<BatteryDto> batteries;
}
