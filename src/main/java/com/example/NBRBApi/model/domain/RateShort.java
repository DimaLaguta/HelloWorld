package com.example.NBRBApi.model.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Date;

@Entity(name = "rate_date")
public class RateShort {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;
    @JsonProperty("Cur_ID")
    public int curID;
    @JsonProperty("Date")
    public LocalDate date;
    @JsonProperty("Cur_OfficialRate")
    public double curOfficialRate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCurID() {
        return curID;
    }

    public void setCurID(int curID) {
        this.curID = curID;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getCurOfficialRate() {
        return curOfficialRate;
    }

    public void setCurOfficialRate(double curOfficialRate) {
        this.curOfficialRate = curOfficialRate;
    }
}
