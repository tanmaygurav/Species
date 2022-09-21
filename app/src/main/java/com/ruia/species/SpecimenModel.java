package com.ruia.species;

import android.widget.TextView;

import java.util.Date;

public class SpecimenModel {
    private String common_name,scientific_name, cupboardNumber,formaldehydeChangeTimer,specimen_image;
    private int formaldehydeChange;
    private Date lastChanged,expectedChange;

    public SpecimenModel(String common_name, String cupboardNumber, String formaldehydeChangeTimer, String specimen_image, int formaldehydeChange) {
        this.common_name = common_name;
        this.cupboardNumber = cupboardNumber;
        this.formaldehydeChangeTimer = formaldehydeChangeTimer;
        this.specimen_image = specimen_image;
        this.formaldehydeChange = formaldehydeChange;
    }

    public SpecimenModel(String common_name, String scientific_name, String specimen_image) {
        this.common_name = common_name;
        this.scientific_name = scientific_name;
        this.specimen_image = specimen_image;
    }

    public SpecimenModel(String common_name, String scientific_name) {
        this.common_name = common_name;
        this.scientific_name = scientific_name;
    }

    public String getFormaldehydeChangeTimer() {
        return formaldehydeChangeTimer;
    }

    public void setFormaldehydeChangeTimer(String formaldehydeChangeTimer) {
        this.formaldehydeChangeTimer = formaldehydeChangeTimer;
    }

    public Date getLastChanged() {
        return lastChanged;
    }

    public void setLastChanged(Date lastChanged) {
        this.lastChanged = lastChanged;
    }

    public Date getExpectedChange() {
        return expectedChange;
    }

    public void setExpectedChange(Date expectedChange) {
        this.expectedChange = expectedChange;
    }

    public String getCupboardNumber() {
        return cupboardNumber;
    }

    public void setCupboardNumber(String cupboardNumber) {
        this.cupboardNumber = cupboardNumber;
    }

    public String getCommon_name() {
        return common_name;
    }

    public void setCommon_name(String common_name) {
        this.common_name = common_name;
    }

    public String getScientific_name() {
        return scientific_name;
    }

    public void setScientific_name(String scientific_name) {
        this.scientific_name = scientific_name;
    }

    public String getSpecimen_image() {
        return specimen_image;
    }

    public void setSpecimen_image(String specimen_image) {
        this.specimen_image = specimen_image;
    }

    public int getFormaldehydeChange() {
        return formaldehydeChange;
    }

    public void setFormaldehydeChange(int formaldehydeChange) {
        this.formaldehydeChange = formaldehydeChange;
    }
}
