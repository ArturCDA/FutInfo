package com.futinfor.model.entity;

import java.time.LocalDateTime;

public class Partida {

    private Long id;
    private Times timeCasa;
    private Times timeVisitante;
    private int golsCasa;
    private int golsVisitante;
    private LocalDateTime dataHora;
    private String estadio;

    public Partida() {}

    public Partida(Long id, Times timeCasa, Times timeVisitante,
                   int golsCasa, int golsVisitante,
                   LocalDateTime dataHora, String estadio) {
        this.id = id;
        this.timeCasa = timeCasa;
        this.timeVisitante = timeVisitante;
        this.golsCasa = golsCasa;
        this.golsVisitante = golsVisitante;
        this.dataHora = dataHora;
        this.estadio = estadio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Times getTimeCasa() {
        return timeCasa;
    }

    public void setTimeCasa(Times timeCasa) {
        this.timeCasa = timeCasa;
    }

    public Times getTimeVisitante() {
        return timeVisitante;
    }

    public void setTimeVisitante(Times timeVisitante) {
        this.timeVisitante = timeVisitante;
    }

    public int getGolsCasa() {
        return golsCasa;
    }

    public void setGolsCasa(int golsCasa) {
        this.golsCasa = golsCasa;
    }

    public int getGolsVisitante() {
        return golsVisitante;
    }

    public void setGolsVisitante(int golsVisitante) {
        this.golsVisitante = golsVisitante;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getEstadio() {
        return estadio;
    }

    public void setEstadio(String estadio) {
        this.estadio = estadio;
    }

    
}
