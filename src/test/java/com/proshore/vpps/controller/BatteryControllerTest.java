package com.proshore.vpps.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proshore.vpps.custom_exception.ErrorMessage;
import com.proshore.vpps.dto.BatteryDto;
import com.proshore.vpps.dto.BatterySummaryDto;
import com.proshore.vpps.service.BatteryService;
import com.proshore.vpps.utils.DataFeeder;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static com.proshore.vpps.utils.RequiredConstant.*;

@WebMvcTest(BatteryController.class)
public class BatteryControllerTest {

    @MockBean
    private BatteryService batteryService;

    @Autowired
    private MockMvc mvc;

    private BatteryDto batteryDto;
    private List<BatteryDto> batteryDtos;
    private static ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        batteryDto = DataFeeder.getBatteryDto();
        batteryDtos = Arrays.asList(batteryDto);
    }

    @Test
    void shouldAddBattery() throws Exception {
        BDDMockito.given(batteryService.add(batteryDtos))
                .willReturn(Arrays.asList(batteryDto));

        ResultActions response =  mvc.perform(
                post(URLConstant.API_VERSION.concat(FeatureAPIConstant.BATTERY_API)).contentType(MediaType.APPLICATION_JSON).content(
                        objectMapper.writeValueAsString(Arrays.asList(batteryDto))
                ));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is(SuccessfulMessage.BATTERIES_CREATED)));

    }


    @Test
    void shouldGetAllBatteries() throws Exception {

        Double avgCapacity = batteryDtos.stream().mapToInt(battery -> battery.getCapacity())
                .average().getAsDouble();

        Integer totalCapacity = batteryDtos.stream().mapToInt(battery -> battery.getCapacity()).sum();

        BDDMockito.given(batteryService.findAll(1000, 2000))
                .willReturn(new BatterySummaryDto(batteryDtos.size(), totalCapacity, avgCapacity,batteryDtos ));

        ResultActions response = mvc.perform(get(URLConstant.API_VERSION.concat(FeatureAPIConstant.BATTERY_API))
                                        .param("from", String.valueOf(1000))
                                        .param("to", String.valueOf(2000)));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.totalBatteries", is(batteryDtos.size())))
                .andExpect(jsonPath("$.totalCapacity", is(totalCapacity)))
                .andExpect(jsonPath("$.avgCapacity", is(avgCapacity)));

    }

    @Test
    void shouldThrowExceptionWhenCapacityOrPostcodeIsLessThanZero() throws Exception {

        batteryDto.setCapacity(-10);
        List<BatteryDto> batteryDtos = Arrays.asList(batteryDto);
        BDDMockito.given(batteryService.add(batteryDtos))
                .willReturn(Arrays.asList(batteryDto));

        ResultActions response =  mvc.perform(
                post(URLConstant.API_VERSION.concat(FeatureAPIConstant.BATTERY_API)).contentType(MediaType.APPLICATION_JSON).content(
                        objectMapper.writeValueAsString(batteryDtos)
                ));



        response.andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(result -> Assert.assertTrue(result.getResolvedException() instanceof ConstraintViolationException))
                .andExpect(r ->{
                    ErrorMessage errorMessage = objectMapper.readValue(r.getResponse().getContentAsString(), ErrorMessage.class);
                    Assertions.assertTrue(errorMessage.getErrors().contains(ValidationMessage.CAPACITY_FORMAT));
                });

    }


    @Test
    void shouldThrowExceptionWhenAnyFieldIsNull() throws Exception {

        batteryDto.setName(null);
        List<BatteryDto> batteryDtos = Arrays.asList(batteryDto);
        BDDMockito.given(batteryService.add(batteryDtos))
                .willReturn(Arrays.asList(batteryDto));

        ResultActions response =  mvc.perform(
                post(URLConstant.API_VERSION.concat(FeatureAPIConstant.BATTERY_API)).contentType(MediaType.APPLICATION_JSON).content(
                        objectMapper.writeValueAsString(batteryDtos)
                ));



        response.andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(result -> Assert.assertTrue(result.getResolvedException() instanceof ConstraintViolationException))
                .andExpect(r ->{
                    ErrorMessage errorMessage = objectMapper.readValue(r.getResponse().getContentAsString(), ErrorMessage.class);
                    Assertions.assertTrue(errorMessage.getErrors().contains(ValidationMessage.NAME_REQUIRED));
                });

    }

    @Test
    void shouldThrowExceptionWhenNameIsNotAlphabet() throws Exception {

        batteryDto.setName("1234test");
        List<BatteryDto> batteryDtos = Arrays.asList(batteryDto);
        BDDMockito.given(batteryService.add(batteryDtos))
                .willReturn(Arrays.asList(batteryDto));

        ResultActions response =  mvc.perform(
                post(URLConstant.API_VERSION.concat(FeatureAPIConstant.BATTERY_API)).contentType(MediaType.APPLICATION_JSON).content(
                        objectMapper.writeValueAsString(batteryDtos)
                ));


        response.andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(result -> Assert.assertTrue(result.getResolvedException() instanceof ConstraintViolationException))
                .andExpect(r ->{
                    ErrorMessage errorMessage = objectMapper.readValue(r.getResponse().getContentAsString(), ErrorMessage.class);
                    Assertions.assertTrue( errorMessage.getErrors().contains(ValidationMessage.NAME_FORMAT));
                });

    }

}
