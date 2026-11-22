/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juegoderol;

import java.util.Scanner;

/**
 *
 * @author djmat
 */



public class Main {

  public static void main(String[] args) {
      // Inicio
      try (Scanner entrada = new Scanner(System.in)) {
          // Inicio
          System.out.println("================================");
          System.out.println("       ARENA DE LUCHADORES");
          System.out.println("================================");
          
          // Personajes
          Guerrero guerrero = new Guerrero("Capitán América", 100, 60, 1, 1, 1);
          Mago mago = new Mago("Piru", 100, 55, 1, 1, 1);
          Arquero arquero = new Arquero("Hawkeye", 100, 60, 1, 1, 1);
          
          Luchador[] luchadores = {guerrero, mago, arquero};
          
          // Estadísticas
          System.out.println("\n--- LUCHADORES ---");
          for (int i = 0; i < luchadores.length; i++) {
              Luchador luchador = luchadores[i];
              System.out.println("\n" + (i + 1) + ". " + luchador.getNombre());
              System.out.println("Vida: " + luchador.getHP());
              System.out.println("Ataque: " + luchador.getAtaque());
              System.out.println("Fuerza: " + luchador.getFuerza());
              System.out.println("Agilidad: " + luchador.getAgilidad());
              System.out.println("Inteligencia: " + luchador.getinteligencia());
          }
          
          // Selección
          System.out.println("\n--- SELECCIÓN ---");
          int opcion1 = leerOpcion(entrada, "Primer luchador (1-3): ", 1, 3);
          int opcion2;
          
          do {
              opcion2 = leerOpcion(entrada, "Segundo luchador (1-3): ", 1, 3);
              if (opcion1 == opcion2) {
                  System.out.println("Selecciona otro luchador.");
              }
          } while (opcion1 == opcion2);
          
          Luchador jugador1 = luchadores[opcion1 - 1];
          Luchador jugador2 = luchadores[opcion2 - 1];
          
          // Combate
          System.out.println("\n================================");
          System.out.println("         INICIA EL COMBATE");
          System.out.println("================================");
          System.out.println(jugador1.getNombre() + " VS " + jugador2.getNombre());
          
          int turno = 0;
          
          while (jugador1.estaVivo() && jugador2.estaVivo()) {
              
              // Turnos
              Luchador atacante;
              Luchador defensor;
              
              if (turno % 2 == 0) {
                  atacante = jugador1;
                  defensor = jugador2;
              } else {
                  atacante = jugador2;
                  defensor = jugador1;
              }
              
              System.out.println("\n--------------------------------");
              System.out.println("TURNO DE " + atacante.getNombre());
              System.out.println("--------------------------------");
              
              // Enfriamiento
              switch (atacante) {
                  case Guerrero guerrero1 -> guerrero1.iniciarTurno();
                  case Mago mago1 -> mago1.iniciarTurno();
                  case Arquero arquero1 -> arquero1.iniciarTurno();
                  default -> {
                  }
              }
              
              // Veneno
              atacante.procesarVeneno();
              
              if (!atacante.estaVivo()) {
                  System.out.println(atacante.getNombre() + " fue derrotado.");
                  break;
              }
              
              // Vida
              System.out.println("\n" + atacante.getNombre()
                      + " HP: " + atacante.getHP());
              System.out.println(defensor.getNombre()
                      + " HP: " + defensor.getHP());
              
              // Menú
              boolean accionRealizada = false;
              
              while (!accionRealizada) {
                  System.out.println("\n1. Ataque normal");
                  System.out.println("2. Habilidad especial");
                  
                  int opcion = leerOpcion(entrada, "Acción: ", 1, 2);
                  
                  if (opcion == 1) {
                      
                      // Ataque
                      int ataqueTotal = atacante.atacar();
                      
                      // Defensa
                      int defensaTotal = defensor.defenderse();
                      
                      // Impacto
                      if (ataqueTotal > defensaTotal) {
                          
                          // Daño
                          int danio = atacante.calcularDanioNormal();
                          defensor.recibirDanio(danio);
                          
                      } else {
                          System.out.println("El ataque ha fallado.");
                      }
                      
                      accionRealizada = true;
                      
                  } else {
                      
                      // Poder
                      if (atacante instanceof Guerrero personaje) {
                          
                          if (personaje.habilidadDisponible()) {
                              personaje.golpeConFuria(defensor);
                              accionRealizada = true;
                          }
                          
                      } else if (atacante instanceof Mago personaje) {
                          
                          if (personaje.habilidadDisponible()) {
                              personaje.bolaDeFuego(defensor);
                              accionRealizada = true;
                          }
                          
                      } else if (atacante instanceof Arquero personaje) {
                          
                          if (personaje.habilidadDisponible()) {
                              personaje.flechaEnvenenada(defensor);
                              accionRealizada = true;
                          }
                      }
                      
                      if (!accionRealizada) {
                          System.out.println("Habilidad en enfriamiento.");
                          System.out.println("Selecciona otra acción.");
                      }
                  }
              }
              
              turno++;
          }
          
          // Ganador
          System.out.println("\n================================");
          System.out.println("          FIN DEL COMBATE");
          System.out.println("================================");
          
          if (jugador1.estaVivo()) {
              System.out.println("GANADOR: " + jugador1.getNombre());
          } else if (jugador2.estaVivo()) {
              System.out.println("GANADOR: " + jugador2.getNombre());
          } else {
              System.out.println("EMPATE");
          } }
  }

  // Validación
  public static int leerOpcion(Scanner entrada, String mensaje,
                               int minimo, int maximo) {
    int opcion;

    while (true) {
      System.out.print(mensaje);

      try {
        opcion = Integer.parseInt(entrada.nextLine());

        if (opcion >= minimo && opcion <= maximo) {
          return opcion;
        }

        System.out.println("Opción fuera del rango.");

      } catch (NumberFormatException error) {
        System.out.println("Ingresa un número válido.");
      }
    }
  }
}
