package com.futinfor.util;

import com.futinfor.model.entity.Usuario;

public class Sessao {

    private static Usuario usuarioLogado;

    public static void setUsuario(Usuario usuario) {
        usuarioLogado = usuario;
    }

    public static Usuario getUsuario() {
        return usuarioLogado;
    }

    public static void logout() {
        usuarioLogado = null;
    }

    public static boolean isAdmin() {
        return usuarioLogado != null &&
               usuarioLogado.getTipo().equals("ADMIN");
    }

    public static boolean isJornalista() {
        return usuarioLogado != null &&
               usuarioLogado.getTipo().equals("JORNALISTA");
    }

    public static boolean isLeitor() {
        return usuarioLogado != null &&
               usuarioLogado.getTipo().equals("LEITOR");
    }
}