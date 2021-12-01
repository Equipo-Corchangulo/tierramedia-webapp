package com.tierramedia;

public class Promocion {
    private Atracciones[] listaAtracciones;
    private double costoFinal;
    private int tiempototal;

    public Promocion(Atracciones[] listaAtracciones) {
        this.listaAtracciones = listaAtracciones;
    }

    public Promocion(){

    }

    public Atracciones[] getListaAtracciones() {
        return listaAtracciones;
    }

    public void setListaAtracciones(Atracciones[] listaAtracciones) {
        this.listaAtracciones = listaAtracciones;
    }

    public double getCostoFinal() {
        return costoFinal;
    }

    public void setCostoFinal(double costoFinal) {
        this.costoFinal = costoFinal;
    }

    public int getTiempototal() {
        return tiempototal;
    }

    public void setTiempototal(int tiempototal) {
        this.tiempototal = tiempototal;
    }
}
