package com.example.NBRBApi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;


public class Rate {
    @JsonProperty("Cur_ID")
    public int curID;
    @JsonProperty("Date")
    public Date date;
    @JsonProperty("Cur_Abbreviation")
    public String curAbbreviation;
    @JsonProperty("Cur_Scale")
    public int curScale;
    @JsonProperty("Cur_Name")
    public String curName;
    @JsonProperty("Cur_OfficialRate")
    public double curOfficialRate;

    public Rate() {
    }


}

