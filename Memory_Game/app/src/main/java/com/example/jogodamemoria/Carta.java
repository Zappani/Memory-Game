package com.example.jogodamemoria;

import android.widget.ImageButton;

public class Carta {
    private int idImagem;
    private boolean estaVirada;
    private boolean par;
    private ImageButton imageButton;

    public Carta(int idImagem) {
        this.idImagem = idImagem;
    }

    public int getIdImagem() {
        return idImagem;
    }
    public boolean estaVirada() {
        return estaVirada;
    }
    public void setestaVirada(boolean virada) {
        this.estaVirada = virada;
    }
    public boolean par() {
        return par;
    }
    public void setPar(boolean encontrada) {
        this.par = encontrada;
    }
    public ImageButton getImageButton() {
        return imageButton;
    }
    public void setImageButton(ImageButton button) {
        this.imageButton = button;
    }
}
