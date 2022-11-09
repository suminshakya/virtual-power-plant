package com.proshore.vpps.mapper;

import com.proshore.vpps.dto.BatteryDto;
import com.proshore.vpps.mapper.battery.BatteryMapper;
import com.proshore.vpps.model.Battery;
import com.proshore.vpps.utils.DataFeeder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BatterMapperTest {

    private BatteryDto batteryDto;
    private Battery battery;

    @InjectMocks
    private BatteryMapper batteryMapper;


    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        batteryDto = DataFeeder.getBatteryDto();
        battery = DataFeeder.getBattery();
    }

    @Test
    void mapToEntity(){
        Battery returnedValue = batteryMapper.toEntity(batteryDto);

        Assertions.assertEquals(batteryDto.getName(), returnedValue.getName());
        Assertions.assertEquals(batteryDto.getCapacity(), returnedValue.getCapacity());
        Assertions.assertEquals(batteryDto.getPostCode(), returnedValue.getPostCode());

    }

    @Test
    void mapToDTO(){
        BatteryDto returnedValue = batteryMapper.toDTO(battery);

        Assertions.assertEquals(battery.getName(), returnedValue.getName());
        Assertions.assertEquals(battery.getCapacity(), returnedValue.getCapacity());
        Assertions.assertEquals(battery.getPostCode(), returnedValue.getPostCode());

    }
}
