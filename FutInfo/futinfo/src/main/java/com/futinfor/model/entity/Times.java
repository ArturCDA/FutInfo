package com.futinfor.model.entity;

public class Times {

    private Long id;
    private String nome;
    private String pais;
    private String estadio;
    private String tecnico;

    public Times() {}

    public Times(Long id, String nome, String pais, String estadio, String tecnico) {
        this.id = id;
        this.nome = nome;
        this.pais = pais;
        this.estadio = estadio;
        this.tecnico = tecnico;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getEstadio() {
        return estadio;
    }

    public void setEstadio(String estadio) {
        this.estadio = estadio;
    }

    public String getTecnico() {
        return tecnico;
    }

    public void setTecnico(String tecnico) {
        this.tecnico = tecnico;
    }

    
}

