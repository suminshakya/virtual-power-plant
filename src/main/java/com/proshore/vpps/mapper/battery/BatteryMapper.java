package com.proshore.vpps.mapper.battery;

import com.proshore.vpps.dto.BatteryDto;
import com.proshore.vpps.mapper.BaseMapper;
import com.proshore.vpps.model.Battery;
import org.springframework.stereotype.Service;

@Service
public class BatteryMapper implements BaseMapper<Battery, BatteryDto> {

    @Override
    public Battery toEntity(BatteryDto batteryDto) {
        Battery battery = new Battery();
        battery.setCapacity(batteryDto.getCapacity());
        battery.setName(batteryDto.getName());
        battery.setPostCode(batteryDto.getPostCode());

        return battery;
    }

    @Override
    public BatteryDto toDTO(Battery battery) {
        BatteryDto dto = new BatteryDto();
        dto.setCapacity(battery.getCapacity());
        dto.setName(battery.getName());
        dto.setPostCode(battery.getPostCode());

        return dto;

    }
}
