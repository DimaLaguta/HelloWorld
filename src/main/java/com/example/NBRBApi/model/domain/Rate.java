package com.example.NBRBApi.model.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class Rate {

    @Id
    @JsonProperty("Cur_ID")
    public int id;
    @JsonProperty("Date")
    public LocalDate date;
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

    public int getCurID() {
        return id;
    }

    public void setCurID(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCurAbbreviation() {
        return curAbbreviation;
    }

    public void setCurAbbreviation(String curAbbreviation) {
        this.curAbbreviation = curAbbreviation;
    }

    public int getCurScale() {
        return curScale;
    }

    public void setCurScale(int curScale) {
        this.curScale = curScale;
    }

    public String getCurName() {
        return curName;
    }

    public void setCurName(String curName) {
        this.curName = curName;
    }

    public double getCurOfficialRate() {
        return curOfficialRate;
    }

    public void setCurOfficialRate(double curOfficialRate) {
        this.curOfficialRate = curOfficialRate;
    }

}

