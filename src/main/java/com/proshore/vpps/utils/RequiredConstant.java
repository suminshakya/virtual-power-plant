package com.proshore.vpps.utils;

public interface RequiredConstant {

    interface URLConstant {
        String API_VERSION = "/api/v1";
    }

    interface FeatureAPIConstant {
        String BATTERY_API = "/batteries";
    }

    interface Message {
        String NAME_REQUIRED = "Name is Required!!!";
        String BLANKED_NAME = "Name must not be empty!!!";
        String NAME_FORMAT = "Name should consist only characters";
        String CAPACITY_FORMAT = "Capacity should not be negative";
        String POSTCODE_FORMAT = "Post Code should not be negative";
        String POSTCODE_REQUIRED = "Post Code is Required!!!";
        String CAPACITY_REQUIRED = "Capacity is Required!!!";
    }

    interface RegularExpression {
        String ALPHABET_SPACE_ONLY = "[a-zA-Z ]*$";
        String POSITIVE_VALUE = "^[1-9]+[0-9]*$";

    }

}
