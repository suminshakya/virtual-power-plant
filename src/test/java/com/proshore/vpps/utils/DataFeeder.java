package com.proshore.vpps.utils;

import com.proshore.vpps.dto.BatteryDto;
import com.proshore.vpps.model.Battery;

public class DataFeeder {

    public static Battery getBattery(){
        Battery battery = new Battery();
        battery.setName("test");
        battery.setCapacity(1000);
        battery.setPostCode(2345);

        return battery;
    }

    public static BatteryDto getBatteryDto(){
        BatteryDto battery = new BatteryDto();
        battery.setName("test");
        battery.setCapacity(1000);
        battery.setPostCode(2345);

        return battery;
    }
}
