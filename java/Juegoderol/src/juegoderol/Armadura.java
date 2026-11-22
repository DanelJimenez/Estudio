/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juegoderol;

/**
 *
 * @author djmat
 */


// CLASE - Padre
public class Armadura {

    // PRIVATE - Atributos
    private final String nombre;
    private final int bonoDefensa;
    private final int bonoInteligencia;

    // CONSTRUCTOR - Inicialización
    public Armadura(String nombre, int bonoDefensa, int bonoInteligencia) {
        this.nombre = nombre;
        this.bonoDefensa = bonoDefensa;
        this.bonoInteligencia = bonoInteligencia;
    }

    // GET - Nombre
    public String getNombre() {
        return nombre;
    }

    // GET - Defensa
    public int getBonoDefensa() {
        return bonoDefensa;
    }

    // GET - Inteligencia
    public int getBonoInteligencia() {
        return bonoInteligencia;
    }
}


