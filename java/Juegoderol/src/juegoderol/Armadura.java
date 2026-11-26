/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juegoderol;

/**
 *
 * @author djmat
 */

public class Armadura {

    private final String nombre;
    private final int bono;
    private final int bonoInteligencia;

    public Armadura(String nombre, int bono) {
        this.nombre = nombre;
        this.bono = bono;
        this.bonoInteligencia = 0;
    }

    public Armadura(String nombre, int bono, int bonoInteligencia) {
        this.nombre = nombre;
        this.bono = bono;
        this.bonoInteligencia = bonoInteligencia;
    }

    public String getNombre() {
        return nombre;
    }

    public int getBono() {
        return bono;
    }

    public int getBonoInteligencia() {
        return bonoInteligencia;
    }
}