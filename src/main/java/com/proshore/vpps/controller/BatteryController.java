package com.proshore.vpps.controller;

import com.proshore.vpps.dto.BatteryDto;
import com.proshore.vpps.dto.BatterySummaryDto;
import com.proshore.vpps.dto.MessageResponse;
import com.proshore.vpps.service.BatteryService;
import com.proshore.vpps.utils.RequiredConstant.FeatureAPIConstant;
import com.proshore.vpps.utils.RequiredConstant.URLConstant;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.proshore.vpps.utils.RequiredConstant.*;

@RestController
@RequestMapping(URLConstant.API_VERSION + FeatureAPIConstant.BATTERY_API)
@AllArgsConstructor
@Validated
public class BatteryController {

    private final BatteryService batteryService;

    @PostMapping
    public ResponseEntity<MessageResponse> add(@RequestBody @Valid List<BatteryDto> batteries) {
        batteryService.add(batteries);
        return ResponseEntity.ok(new MessageResponse(SuccessfulMessage.BATTERIES_CREATED, HttpStatus.CREATED));
    }

    @GetMapping
    public ResponseEntity<BatterySummaryDto> findAll(@RequestParam  int from, @RequestParam int to) {
        return ResponseEntity.ok(batteryService.findAll(from, to));
    }
}
