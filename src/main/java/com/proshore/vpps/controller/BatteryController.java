package com.proshore.vpps.controller;

import com.proshore.vpps.dto.BatteryDto;
import com.proshore.vpps.dto.BatterySummaryDto;
import com.proshore.vpps.service.BatteryService;
import com.proshore.vpps.utils.RequiredConstant.FeatureAPIConstant;
import com.proshore.vpps.utils.RequiredConstant.URLConstant;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(URLConstant.API_VERSION + FeatureAPIConstant.BATTERY_API)
@AllArgsConstructor
@Validated
public class BatteryController {

    private final BatteryService batteryService;

    @PostMapping
    public ResponseEntity<List<BatteryDto>> add(@RequestBody @Valid List<BatteryDto> batteries) {
        return ResponseEntity.ok(batteryService.add(batteries));
    }

    @GetMapping
    public ResponseEntity<BatterySummaryDto> findAll(@RequestParam  int from, @RequestParam int to) {
        return ResponseEntity.ok(batteryService.findAll(from, to));
    }
}
