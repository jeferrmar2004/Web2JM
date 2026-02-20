package org.fifa.quinielajsf.data;

import java.util.Date;

public class Partido implements java.io.Serializable {

    private String rival1;
    private String rival2;
    private Date fechaHora;

    public Partido() {
        this.rival1 = "";
        this.rival2 = "";
        this.fechaHora = new Date();
    }

    public Partido(String rival1, String rival2, Date fechaHora) {
        this.rival1 = rival1;
        this.rival2 = rival2;
        this.fechaHora = fechaHora;
    }

    // Getters y Setters
    public String getRival1() {
        return rival1;
    }

    public void setRival1(String rival1) {
        this.rival1 = rival1;
    }

    public String getRival2() {
        return rival2;
    }

    public void setRival2(String rival2) {
        this.rival2 = rival2;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

}