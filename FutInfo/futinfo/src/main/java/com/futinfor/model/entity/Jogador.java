package com.futinfor.model.entity;

import java.time.LocalDate;

public class Jogador {

    private Long id;
    private String nome;
    private String posicao;
    private int numeroCamisa;
    private LocalDate dataNascimento;
    private Times time;

    public Jogador() {}

    public Jogador(Long id, String nome, String posicao,
                   int numeroCamisa, LocalDate dataNascimento, Times time) {
        this.id = id;
        this.nome = nome;
        this.posicao = posicao;
        this.numeroCamisa = numeroCamisa;
        this.dataNascimento = dataNascimento;
        this.time = time;
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

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public int getNumeroCamisa() {
        return numeroCamisa;
    }

    public void setNumeroCamisa(int numeroCamisa) {
        this.numeroCamisa = numeroCamisa;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Times getTime() {
        return time;
    }

    public void setTime(Times time) {
        this.time = time;
    }

    

}

