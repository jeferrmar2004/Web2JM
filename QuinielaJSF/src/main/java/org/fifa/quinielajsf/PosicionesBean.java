package org.fifa.quinielajsf;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named("PosicionesBean")
@RequestScoped
public class PosicionesBean {
    private String nombre;

    public PosicionesBean(){
        this.nombre = "Quiniela Fifa";
    }

    public String getNombre() {
        return this.nombre;
    }
}
