package com.example.parcialrepetidoej1.models;

public class Paciente {
    private String id;
    private String name;
    private String direction;
    private String phone;
    private String observaciones;

    public Paciente() {
    }

    public Paciente(String id, String name, String direction, String phone, String observaciones) {
        this.id = id;
        this.name = name;
        this.direction = direction;
        this.phone = phone;
        this.observaciones = observaciones;
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

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
