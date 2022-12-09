package com.proshore.vpps.dto;

import com.proshore.vpps.annotation.ValidateNumber;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static com.proshore.vpps.utils.RequiredConstant.*;

@Data
public class BatteryDto {

    @Pattern(regexp= RegularExpression.ALPHABET_SPACE_ONLY,message= ValidationMessage.NAME_FORMAT)
    @NotNull(message = ValidationMessage.NAME_REQUIRED)
    @NotBlank(message = ValidationMessage.BLANKED_NAME)
    private String name;

    @NotNull(message = ValidationMessage.POSTCODE_REQUIRED)
    @ValidateNumber(message = ValidationMessage.POSTCODE_FORMAT)
    private Integer postCode;

    @ValidateNumber(message = ValidationMessage.CAPACITY_FORMAT)
    @NotNull(message = ValidationMessage.CAPACITY_REQUIRED)
    private Integer capacity;
}

