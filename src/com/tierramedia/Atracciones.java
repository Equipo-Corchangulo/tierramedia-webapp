package com.tierramedia;

public class Atracciones {
    private String tipo;
    private String nopmbre;
    private double precio;
    private int horas;

    public Atracciones() {
    }

    public Atracciones(String tipo, String nopmbre, double precio, int horas, int cupo) {
        this.tipo = tipo;
        this.nopmbre = nopmbre;
        this.precio = precio;
        this.horas = horas;
        this.cupo = cupo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNopmbre() {
        return nopmbre;
    }

    public void setNopmbre(String nopmbre) {
        this.nopmbre = nopmbre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public int getCupo() {
        return cupo;
    }

    public void setCupo(int cupo) {
        this.cupo = cupo;
    }

    private int cupo;
}
