package com.proshore.vpps.service;

import com.proshore.vpps.dto.BatteryDto;
import com.proshore.vpps.dto.BatterySummaryDto;

import java.util.List;

public interface BatteryService {

    List<BatteryDto> add(List<BatteryDto> batteries);
    BatterySummaryDto findAll(int from, int to);
}
