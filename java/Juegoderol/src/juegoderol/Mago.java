/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juegoderol;

/**
 *
 * @author djmat
 */

public class Mago extends Luchador {

    // PRIVATE - Enfriamiento
    private int enfriamiento = 0;

    // CONSTRUCTOR - Inicialización
    public Mago(String nombre, int HP, int ataque,
                int fuerza, int agilidad, int inteligencia) {

        // SUPER - Herencia
        super(nombre, HP, ataque, fuerza, agilidad, inteligencia);

        // MÉTODO - Dados
        asignarAtributosConDados();
    }

    // OVERRIDE - Inteligencia
    @Override
    public int getAtributos() {
        return getinteligencia();
    }

    // MÉTODO - Turnos
    public void iniciarTurno() {

        // IF - Enfriamiento
        if (enfriamiento > 0) {
            enfriamiento--;
        }
    }

    // HABILIDAD - Fuego
    public void bolaDeFuego(Luchador enemigo) {

        // IF - Validación
        if (enemigo == null || !estaVivo() || !enemigo.estaVivo()) {
            System.out.println("No se puede realizar el ataque.");
            return;
        }

        // IF - Enfriamiento
        if (enfriamiento > 0) {
            System.out.println("BOLA DE FUEGO no disponible.");
            System.out.println("Enfriamiento: " + enfriamiento);
            return;
        }

        // PRINT - Poder
        System.out.println(getNombre() + " utiliza BOLA DE FUEGO.");

        // MÉTODO - Ataque
        int ataqueTotal = atacar();

        // MÉTODO - Defensa
        int defensaTotal = enemigo.defenderse();

        // ASIGNACIÓN - Enfriamiento
        enfriamiento = 3;

        // IF - Impacto
        if (ataqueTotal > defensaTotal) {

            // RANDOM - D10
            int dado = tirarDado(10);

            // CÁLCULO - Daño
            int danio = dado + getinteligencia() / 5;

            // PRINT - Daño
            System.out.println("Dado D10: " + dado);
            System.out.println("Daño mágico: " + danio);

            // MÉTODO - Vida
            enemigo.recibirDanio(danio);

        } else {

            // ELSE - Fallo
            System.out.println("BOLA DE FUEGO ha fallado.");
        }
    }

    // BOOLEAN - Estado
    public boolean habilidadDisponible() {
        return enfriamiento == 0;
    }
}
