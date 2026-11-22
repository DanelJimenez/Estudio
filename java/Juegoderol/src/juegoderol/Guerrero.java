/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juegoderol;

/**
 *
 * @author djmat
 */

public final class Guerrero extends Luchador {

    // PRIVATE - Enfriamiento
    private int enfriamiento = 0;

    // CONSTRUCTOR - Inicialización
    public Guerrero(String nombre, int HP, int ataque,
                    int fuerza, int agilidad, int inteligencia) {

        // SUPER - Herencia
        super(nombre, HP, ataque, fuerza, agilidad, inteligencia);

        // MÉTODO - Dados
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
        setFuerza(mayor);
        setAgilidad(intermedio);
        setinteligencia(menor);

        // PRINT - Resultados
        System.out.println("Dados de " + getNombre()
                + ": " + dado1 + ", " + dado2 + ", " + dado3);

        System.out.println("Fuerza: " + getFuerza()
                + " | Agilidad: " + getAgilidad()
                + " | Inteligencia: " + getinteligencia());
    }

    // OVERRIDE - Fuerza
    @Override
    public int getAtributos() {
        return getFuerza();
    }

    // MÉTODO - Turnos
    public void iniciarTurno() {

        // IF - Enfriamiento
        if (enfriamiento > 0) {
            enfriamiento--;
        }
    }

    // HABILIDAD - Furia
    public void golpeConFuria(Luchador enemigo) {

        // IF - Validación
        if (enemigo == null || !estaVivo() || !enemigo.estaVivo()) {
            System.out.println("No se puede realizar el ataque.");
            return;
        }

        // IF - Enfriamiento
        if (enfriamiento > 0) {
            System.out.println("GOLPE CON FURIA no disponible.");
            System.out.println("Enfriamiento: " + enfriamiento);
            return;
        }

        // PRINT - Poder
        System.out.println(getNombre() + " utiliza GOLPE CON FURIA.");

        // MÉTODO - Ataque
        int ataqueTotal = atacar();

        // MÉTODO - Defensa
        int defensaTotal = enemigo.defenderse();

        // ASIGNACIÓN - Enfriamiento
        enfriamiento = 3;

        // IF - Impacto
        if (ataqueTotal > defensaTotal) {

            // RANDOM - D12
            int dado = tirarDado(12);

            // CÁLCULO - Daño
            int danio = dado + getFuerza() / 5;

            // PRINT - Daño
            System.out.println("Dado D12: " + dado);
            System.out.println("Daño físico: " + danio);

            // MÉTODO - Vida
            enemigo.recibirDanio(danio);

        } else {

            // ELSE - Fallo
            System.out.println("GOLPE CON FURIA ha fallado.");
        }
    }

    // BOOLEAN - Estado
    public boolean habilidadDisponible() {
        return enfriamiento == 0;
    }
}
