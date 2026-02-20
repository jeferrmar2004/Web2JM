package org.fifa.quinielajsf;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named("ResultadosBean")
@RequestScoped
public class ResultadosBean {
    private String nombre;

    public ResultadosBean(){
        this.nombre = "Quiniela Fifa";
    }

    public String getNombre() {
        return this.nombre;
    }
}
