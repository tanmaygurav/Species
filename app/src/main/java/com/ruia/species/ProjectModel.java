package com.ruia.species;

public class ProjectModel {
    private String project_name;
    private int project_image;
    private int project_logo;

    public ProjectModel(String project_name, int project_image, int project_logo) {
        this.project_name = project_name;
        this.project_image = project_image;
        this.project_logo = project_logo;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public int getProject_image() {
        return project_image;
    }

    public void setProject_image(int project_image) {
        this.project_image = project_image;
    }

    public int getProject_logo() {
        return project_logo;
    }

    public void setProject_logo(int project_logo) {
        this.project_logo = project_logo;
    }
}
