package com.example.parcialrepetidoej1.models;

public class Doctor {
    private String id;
    private String name;
    private String speciality;
    private String occupation;
    private String observation;

    public Doctor() {
    }

    public Doctor(String id, String name, String speciality, String occupation, String observation) {
        this.id = id;
        this.name = name;
        this.speciality = speciality;
        this.occupation = occupation;
        this.observation = observation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }
}
