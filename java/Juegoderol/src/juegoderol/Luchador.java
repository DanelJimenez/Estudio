/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juegoderol;

/**
 *
 * @author djmat
 */

import java.util.Random;

/**

 */

public class Luchador {

    // PRIVATE - Atributos
    private String nombre;
    private int HP;
    private int ataque;
    private int fuerza;
    private int agilidad;
    private int inteligencia;

    // RANDOM - Dados
    private final Random random = new Random();
    
    
  


    // EFECTOS - Estados
    private int quemaduraDanio = 0;
    private int quemaduraRondas = 0;
    private int venenoDanio = 0;
    private int venenoRondas = 0;

    // CONSTRUCTOR - Inicialización
    public Luchador(String nombre, int HP, int ataque, int fuerza, int agilidad, int inteligencia) {

        this.nombre = nombre;
        this.HP = validarHP(HP);
        this.ataque = validar(ataque);
        this.fuerza = validar(fuerza);
        this.agilidad = validar(agilidad);
        this.inteligencia = validar(inteligencia);
    }

    // IF - Validación
    private int validar(int valor) {

        if (valor < 1) return 1;
        if (valor > 100) return 100;

        return valor;
    }

    // IF - Vida
    private int validarHP(int valor) {

        if (valor < 1) {
            return 1;
        }

        if (valor > 100) {
            return 100;
        }

        return valor;
    }

    // GET - Consultar
    public String getNombre() { return nombre; }
    public int getHP() { return HP; }
    public int getAtaque() { return ataque; }
    public int getFuerza() { return fuerza; }
    public int getAgilidad() { return agilidad; }
    public int getinteligencia() { return inteligencia; }

    // SET - Modificar
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setHP(int HP) { this.HP = validarHP(HP); }
    public void setAtaque(int ataque) { this.ataque = validar(ataque); }
    public void setFuerza(int fuerza) { this.fuerza = validar(fuerza); }
    public void setAgilidad(int agilidad) { this.agilidad = validar(agilidad); }
    public void setinteligencia(int inteligencia) { this.inteligencia = validar(inteligencia); }

    // RETURN - Atributo
    public int getAtributos() {
        return 0;
    }

    // RANDOM - Tirada
    public int tirarDado(int caras) {
        return random.nextInt(caras) + 1;
    }

    // DADOS - Atributos
    protected void asignarAtributosConDados() {

        int dado1 = tirarDado(100);
        int dado2 = tirarDado(100);
        int dado3 = tirarDado(100);

        // MATH - Valores
        int mayor = Math.max(dado1, Math.max(dado2, dado3));
        int menor = Math.min(dado1, Math.min(dado2, dado3));
        int intermedio = dado1 + dado2 + dado3 - mayor - menor;

        // SET - Asignación
        setinteligencia(mayor);
        setAgilidad(intermedio);
        setFuerza(menor);

        // PRINT - Resultados
        System.out.println("Dados de " + nombre + ": "
                + dado1 + ", " + dado2 + ", " + dado3);

        System.out.println("Inteligencia: " + inteligencia
                + " | Agilidad: " + agilidad
                + " | Fuerza: " + fuerza);
    }

    // ATAQUE - D20
    public int atacar() {

        int dado = tirarDado(20);
        int bonoAtaque = ataque / 10;
        int bonoPrincipal = getAtributos() / 10;

        // CÁLCULO - Ataque
        int total = dado + bonoAtaque + bonoPrincipal;

        System.out.println(nombre + " ataca dado D20: "
                + dado + " | Total: " + total);

        return total;
    }

    // DEFENSA - D20

    // DEFENSA - D20
    public int defenderse() {
        // RANDOM - Dado
        int dado = tirarDado(20);
        int bonoArmadura = 0;


        // CÁLCULO - Defensa
        int defensa = dado + getAgilidad() / 10 + bonoArmadura;

        // PRINT - Resultados
        System.out.println(getNombre() + " se defiende.");
        System.out.println("Dado D20: " + dado);
        System.out.println("Bono de armadura: " + bonoArmadura);
        System.out.println("Defensa total: " + defensa);

        return defensa;
    }


    // DAÑO - Vida
    public void recibirDanio(int danio) {

        this.HP -= danio;

        // IF - Límite
        if (this.HP < 0) {
            this.HP = 0;
        }

        System.out.println(nombre + " pierde " + danio
                + " de vida. Vida restante: " + HP);
    }

    // QUEMADURA - Aplicación
    public void aplicarQuemadura(int danio, int rondas) {

        this.quemaduraDanio = danio;
        this.quemaduraRondas = rondas;

        System.out.println(nombre + " queda quemado: "
                + danio + " de daño por " + rondas + " rondas.");
    }

    // IF - Quemadura
    public void procesarQuemadura() {

        if (quemaduraRondas > 0) {

            System.out.println("La quemadura afecta a "
                    + nombre + " (rondas restantes: "
                    + quemaduraRondas + ")");

            // DAÑO - Quemadura
            recibirDanio(quemaduraDanio);

            // DECREMENTO - Rondas
            quemaduraRondas--;
        }
    }

    // VENENO - Aplicación
    public void aplicarVeneno(int danio, int turnos) {

        this.venenoDanio = danio;
        this.venenoRondas = turnos;

        System.out.println(nombre + " queda envenenado: "
                + danio + " de daño por " + turnos + " turnos.");
    }

    // IF - Veneno
    public void procesarVeneno() {

        if (venenoRondas > 0) {

            System.out.println("El veneno afecta a "
                    + nombre + " (turnos restantes: "
                    + venenoRondas + ")");

            // DAÑO - Veneno
            recibirDanio(venenoDanio);

            // DECREMENTO - Turnos
            venenoRondas--;
        }
    }

    // BOOLEAN - Vida
    public boolean estaVivo() {
        return HP > 0;
    }

    // DAÑO - Normal
    public int calcularDanioNormal() {

        // DADO - D6
        int dado = tirarDado(6);

        // ATRIBUTO - Principal
        int atributoPrincipal = getAtributos();

        // CÁLCULO - Daño
        int danio = dado + atributoPrincipal / 10;

        // PRINT - Resultados
        System.out.println("Dado D6: " + dado);
        System.out.println("Daño normal: " + danio);

        return danio;
    }
    
    
}




