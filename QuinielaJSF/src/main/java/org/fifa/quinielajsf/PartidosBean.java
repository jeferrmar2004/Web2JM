package org.fifa.quinielajsf;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import org.fifa.quinielajsf.data.Partido;

import java.util.List;

@Named("PartidosBean")
@SessionScoped
public class PartidosBean implements java.io.Serializable {
    private Partido partido;
    private List<Partido> partidos;

    public PartidosBean(){
        this.partido = new Partido();
        this.partidos = new java.util.ArrayList<>();
    }

    public void registrarPartido() {
        //todo: hacer todas las validaciones que sean necearias
        this.partidos.add(this.partido);
        this.partido = new Partido();
    }

    public Partido getPartido() {
        return partido;
    }

    public List<Partido> getPartidos() {
        return partidos;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    public void setPartidos(List<Partido> partidos) {
        this.partidos = partidos;
    }
}
