package com.proshore.vpps.dto;

import com.proshore.vpps.annotation.ValidateNumber;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static com.proshore.vpps.utils.RequiredConstant.*;

@Data
public class BatteryDto {

    @Pattern(regexp= RegularExpression.ALPHABET_SPACE_ONLY,message=Message.NAME_FORMAT)
    @NotNull(message = Message.NAME_REQUIRED)
    @NotBlank(message = Message.BLANKED_NAME)
    private String name;

    @NotNull(message = Message.POSTCODE_REQUIRED)
    @ValidateNumber(message = Message.POSTCODE_FORMAT)
    private Integer postCode;

    @ValidateNumber(message = Message.CAPACITY_FORMAT)
    @NotNull(message = Message.CAPACITY_REQUIRED)
    private Integer capacity;
}

