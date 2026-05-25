package com.futinfor.model.entity;

public class Autor {

    private Long id;
    private String nome;
    private String biografia;
    private String email;

    public Autor() {}

    public Autor(Long id, String nome, String biografia, String email) {
        this.id = id;
        this.nome = nome;
        this.biografia = biografia;
        this.email = email;
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

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
}

