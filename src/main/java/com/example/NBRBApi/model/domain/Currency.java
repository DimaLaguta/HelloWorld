package com.example.NBRBApi.model.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Currency {

//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
    @Id
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

    public int getCurID() {
        return curID;
    }

    public void setCurID(int curID) {
        this.curID = curID;
    }

    public String getCurAbbreviation() {
        return curAbbreviation;
    }

    public void setCurAbbreviation(String curAbbreviation) {
        this.curAbbreviation = curAbbreviation;
    }

    public String getCurName() {
        return curName;
    }

    public void setCurName(String curName) {
        this.curName = curName;
    }

    public String getCurNameEng() {
        return curNameEng;
    }

    public void setCurNameEng(String curNameEng) {
        this.curNameEng = curNameEng;
    }
}
