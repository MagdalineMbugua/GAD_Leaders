package com.magda.gadleaders.models;

 public class Leader {
    private String name;
    private String hours;
    private String score;
    private String country;

    public Leader() {
    }

    public Leader(String name, String hours, String score, String country) {
        this.name = name;
        this.hours = hours;
        this.score = score;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
