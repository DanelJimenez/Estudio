/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juegoderol;

/**
 *
 * @author djmat
 */

public final class Arquero extends Luchador {

    // PRIVATE - Enfriamiento
    private int enfriamiento = 0;

    // CONSTRUCTOR - Inicialización
    public Arquero(String nombre, int HP, int ataque, int fuerza, int agilidad, int inteligencia) {
    super(nombre, HP, ataque, fuerza, agilidad, inteligencia);
      asignarAtributosConDados();
    }

    // OVERRIDE - Dados
    @Override
    protected void asignarAtributosConDados() {

        // RANDOM - Tiradas
        int dado1 = tirarDado(100);
        int dado2 = tirarDado(100);
        int dado3 = tirarDado(100);

        // MATH - Valores
        int mayor = Math.max(dado1, Math.max(dado2, dado3));
        int menor = Math.min(dado1, Math.min(dado2, dado3));
        int intermedio = dado1 + dado2 + dado3 - mayor - menor;

        // SET - Atributos
        setAgilidad(mayor);
        setinteligencia(intermedio);
        setFuerza(menor);

        // PRINT - Resultados
        System.out.println("Dados de " + getNombre()
                + ": " + dado1 + ", " + dado2 + ", " + dado3);

        System.out.println("Agilidad: " + getAgilidad()
                + " | Inteligencia: " + getinteligencia()
                + " | Fuerza: " + getFuerza());
    }

    // OVERRIDE - Agilidad
    @Override
    public int getAtributos() {
        return getAgilidad();
    }

    // MÉTODO - Turnos
    public void iniciarTurno() {

        // IF - Enfriamiento
        if (enfriamiento > 0) {
            enfriamiento--;
        }
    }

    // HABILIDAD - Veneno
    public void flechaEnvenenada(Luchador enemigo) {

        // IF - Validación
        if (enemigo == null || !estaVivo() || !enemigo.estaVivo()) {
            System.out.println("No se puede realizar el ataque.");
            return;
        }

        // IF - Enfriamiento
        if (enfriamiento > 0) {
            System.out.println("FLECHA ENVENENADA no disponible.");
            System.out.println("Enfriamiento: " + enfriamiento);
            return;
        }

        // PRINT - Poder
        System.out.println(getNombre() + " utiliza FLECHA ENVENENADA.");

        // MÉTODO - Ataque
        int ataqueTotal = atacar();

        // MÉTODO - Defensa
        int defensaTotal = enemigo.defenderse();

        // ASIGNACIÓN - Enfriamiento
        enfriamiento = 3;

        // IF - Impacto
        if (ataqueTotal > defensaTotal) {

            // RANDOM - D8
            int dado = tirarDado(8);

            // CÁLCULO - Daño
            int danio = dado + getAgilidad() / 5;

            // PRINT - Daño
            System.out.println("Dado D8: " + dado);
            System.out.println("Daño de flecha: " + danio);

            // MÉTODO - Vida
            enemigo.recibirDanio(danio);

            // IF - Veneno
            if (enemigo.estaVivo()) {
                enemigo.aplicarVeneno(3, 2);
            }

        } else {

            // ELSE - Fallo
            System.out.println("FLECHA ENVENENADA ha fallado.");
        }
    }

    // BOOLEAN - Estado
    public boolean habilidadDisponible() {
        return enfriamiento == 0;
    }
    
    
}
