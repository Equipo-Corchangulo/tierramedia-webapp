package com.tierramedia;

public class Perfilusuario {
    private double presupuesto;
    private int tiempo;
    private String atraccionPreferida;

    public Perfilusuario(double presupuesto, int tiempo, String atraccionPreferida) {
        this.presupuesto = presupuesto;
        this.tiempo = tiempo;
        this.atraccionPreferida = atraccionPreferida;
    }

    public Perfilusuario() {
    }

    public double getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(double presupuesto) {
        this.presupuesto = presupuesto;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public String getAtraccionPreferida() {
        return atraccionPreferida;
    }

    public void setAtraccionPreferida(String atraccionPreferida) {
        this.atraccionPreferida = atraccionPreferida;
    }

    @Override
    public String toString() {
        return "Perfilusuario{" +
                "presupuesto=" + presupuesto +
                ", tiempo=" + tiempo +
                ", atraccionPreferida='" + atraccionPreferida + '\'' +
                '}';
    }
}
