package com.ruia.species;

public class SpecimenModel {
    private String common_name,scientific_name;
    private int specimen_image;

    public SpecimenModel(String common_name, String scientific_name, int specimen_image) {
        this.common_name = common_name;
        this.scientific_name = scientific_name;
        this.specimen_image = specimen_image;
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

    public int getSpecimen_image() {
        return specimen_image;
    }

    public void setSpecimen_image(int specimen_image) {
        this.specimen_image = specimen_image;
    }
}
