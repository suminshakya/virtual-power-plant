package com.proshore.vpps.service.impl;

import com.proshore.vpps.dto.BatteryDto;
import com.proshore.vpps.dto.BatterySummaryDto;
import com.proshore.vpps.mapper.battery.BatteryMapper;
import com.proshore.vpps.model.Battery;
import com.proshore.vpps.repository.BatteryRepository;
import com.proshore.vpps.service.BatteryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BatteryServiceImpl implements BatteryService {

    private final BatteryRepository batteryRepository;
    private final BatteryMapper batteryMapper;

    @Override
    public List<BatteryDto> add(List<BatteryDto> batteries) {

        List<Battery> mappedBatteries = batteryMapper.toEntities(batteries);
        batteryRepository.saveAll(mappedBatteries);
        return batteries;
    }

    @Override
    public BatterySummaryDto findAll(int from, int to) {

        List<Battery> batteries = batteryRepository.findByPostCodeBetween(from, to);
        if(batteries.isEmpty()){
            return new BatterySummaryDto(0, 0,  0.0, Collections.EMPTY_LIST );
        }

        List<BatteryDto> sortedBatteries =  batteryMapper
                                                .toDtos(batteries).stream()
                                                .sorted(Comparator.comparing(batteryDto -> batteryDto.getName()))
                                                .collect(Collectors.toList());

        Double avgCapacity = sortedBatteries.stream().mapToInt(battery -> battery.getCapacity())
                .average().getAsDouble();

        Integer totalCapacity = sortedBatteries.stream().mapToInt(battery -> battery.getCapacity()).sum();
        return new BatterySummaryDto(sortedBatteries.size(), totalCapacity,avgCapacity, sortedBatteries );

    }
}
