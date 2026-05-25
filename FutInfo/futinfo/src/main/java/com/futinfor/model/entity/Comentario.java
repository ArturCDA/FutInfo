package com.futinfor.model.entity;

import java.time.LocalDateTime;

public class Comentario {

    private Long id;
    private String texto;
    private LocalDateTime dataComentario;
    private Usuario usuario;
    private Noticia noticia;

    public Comentario() {}

    public Comentario(Long id, String texto,
                      LocalDateTime dataComentario,
                      Usuario usuario, Noticia noticia) {
        this.id = id;
        this.texto = texto;
        this.dataComentario = dataComentario;
        this.usuario = usuario;
        this.noticia = noticia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public LocalDateTime getDataComentario() {
        return dataComentario;
    }

    public void setDataComentario(LocalDateTime dataComentario) {
        this.dataComentario = dataComentario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Noticia getNoticia() {
        return noticia;
    }

    public void setNoticia(Noticia noticia) {
        this.noticia = noticia;
    }

    
}

