package com.proshore.vpps.service;

import com.proshore.vpps.dto.BatteryDto;
import com.proshore.vpps.dto.BatterySummaryDto;
import com.proshore.vpps.mapper.battery.BatteryMapper;
import com.proshore.vpps.model.Battery;
import com.proshore.vpps.repository.BatteryRepository;
import com.proshore.vpps.service.impl.BatteryServiceImpl;
import com.proshore.vpps.utils.DataFeeder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class BatteryServiceTest {

    private BatteryDto batteryDto;
    private Battery battery;

    @InjectMocks
    private BatteryServiceImpl batteryService;
    @Mock
    private BatteryRepository batteryRepository;
    @Mock
    private BatteryMapper batteryMapper;


    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        batteryDto = DataFeeder.getBatteryDto();
        battery = DataFeeder.getBattery();
    }


    @Test
    void shouldAddBattery(){
        Mockito.when(batteryMapper.toEntity(batteryDto)).thenReturn(battery);
        Mockito.when(batteryRepository.save(battery)).thenReturn(battery);

        List<BatteryDto> returnedData = batteryService.add(Arrays.asList(batteryDto));
        BatteryDto singleData = returnedData.get(0);
        Assertions.assertEquals(batteryDto.getName(), singleData.getName());
        Assertions.assertEquals(batteryDto.getCapacity(), singleData.getCapacity());
        Assertions.assertEquals(batteryDto.getPostCode(), singleData.getPostCode());

    }

    @Test
    void shouldFindAllBatteriesWithSummary(){
        Battery secondbattery = DataFeeder.getBattery();
        secondbattery.setCapacity(2000);

        BatteryDto secondbatteryDto = DataFeeder.getBatteryDto();
        secondbatteryDto.setCapacity(2000);

        Mockito.when(batteryRepository.findByPostCodeBetween(1000, 2000)).thenReturn(Arrays.asList(battery, secondbattery));
        Mockito.when(batteryMapper.toDtos(Arrays.asList(battery, secondbattery))).thenReturn(Arrays.asList(secondbatteryDto,batteryDto));

        BatterySummaryDto batterySummaryDto = batteryService.findAll(1000, 2000);

        Integer totalCapacity = Arrays.asList(battery, secondbattery).stream().mapToInt(battery -> battery.getCapacity()).sum();

        Double avgCapacity = Arrays.asList(battery, secondbattery).stream().mapToInt(battery -> battery.getCapacity())
                .average().getAsDouble();

        Assertions.assertEquals( totalCapacity, batterySummaryDto.getTotalCapacity());
        Assertions.assertEquals( avgCapacity, batterySummaryDto.getAvgCapacity());
        Assertions.assertEquals( 2, batterySummaryDto.getTotalBatteries());


    }

    @Test
    void shouldReturnEmptyWhenFindingAllBatteries(){

        Mockito.when(batteryRepository.findByPostCodeBetween(1000, 2000)).thenReturn(Collections.EMPTY_LIST);
        BatterySummaryDto batterySummaryDto = batteryService.findAll(1000, 2000);

        Assertions.assertEquals( 0, batterySummaryDto.getTotalCapacity());
        Assertions.assertEquals( 0, batterySummaryDto.getAvgCapacity());
        Assertions.assertEquals( 0, batterySummaryDto.getTotalBatteries());
    }


}
