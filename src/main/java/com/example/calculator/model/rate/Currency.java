package com.example.calculator.model.rate;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Currency {
    @JsonProperty("Cur_ID")
    public int curID;
    @JsonProperty("Cur_Abbreviation")
    public String curAbbreviation;
    @JsonProperty("Cur_Name")
    public String curName;
    @JsonProperty("Cur_Name_Eng")
    public String curNameEng;

    public Currency() {
    }
}
