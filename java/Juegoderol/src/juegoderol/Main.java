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

  // MAIN - Inicio
  public static void main(String[] args) {
      // PRINT - Inicio
      try (Scanner entrada = new Scanner(System.in)) {
          // PRINT - Inicio
 
          System.out.println("  ARENA DE LUCHADORES");
 
          // OBJETOS - Personajes
          Guerrero guerrero = new Guerrero("Capitán América", 100, 60, 1, 1, 1);
          Mago mago = new Mago("Piru", 100, 55, 1, 1, 1);
          Arquero arquero = new Arquero("Hawkeye", 100, 60, 1, 1, 1);
          // ARRAY - Luchadores
          Luchador[] luchadores = {guerrero, mago, arquero};
          // MÉTODO - Armaduras
          guerrero.equiparArmadura(new CotaDeMalla());
          mago.equiparArmadura(new ArmaduraDeTela());
          arquero.equiparArmadura(new ArmaduraDeCuero());
          // FOR - Estadísticas
          System.out.println("\n--- LUCHADORES ---");
          for (int i = 0; i < luchadores.length; i++) {
              Luchador luchador = luchadores[i];
              
              // PRINT - Atributos
              System.out.println("\n" + (i + 1) + ". " + luchador.getNombre());
              System.out.println("Vida: " + luchador.getHP());
              System.out.println("Ataque: " + luchador.getAtaque());
              System.out.println("Fuerza: " + luchador.getFuerza());
              System.out.println("Agilidad: " + luchador.getAgilidad());
              System.out.println("Inteligencia: " + luchador.getinteligencia());
              
              // IF - Armadura
              if (luchador.getArmadura() != null) {
                  System.out.println("Armadura: " + luchador.getArmadura().getNombre());
                  System.out.println("Bono defensa: "
                          + luchador.getArmadura().getBonoDefensa());
              }
          }     // SELECCIÓN - Personajes
          System.out.println("SELECCIÓN");
          int opcion1 = leerOpcion(entrada, "Primer luchador (1-3): ", 1, 3);
          int opcion2;
          // DO-WHILE - Validación
          do {
              opcion2 = leerOpcion(entrada, "Segundo luchador (1-3): ", 1, 3);
              if (opcion1 == opcion2) {
                  System.out.println("Selecciona otro luchador.");
              }
          } while (opcion1 == opcion2);
          // OBJETOS - Seleccionados
          Luchador jugador1 = luchadores[opcion1 - 1];
          Luchador jugador2 = luchadores[opcion2 - 1];
          // PRINT - Combate
          
          System.out.println(" INICIA EL COMBATE");
         
          System.out.println(jugador1.getNombre() + " VS " + jugador2.getNombre());
          int turno = 0;
          // WHILE - Combate
          while (jugador1.estaVivo() && jugador2.estaVivo()) {
              Luchador atacante;
              Luchador defensor;
              
              // IF-ELSE - Turnos
              if (turno % 2 == 0) {
                  atacante = jugador1;
                  defensor = jugador2;
              } else {
                  atacante = jugador2;
                  defensor = jugador1;
              }
              
              // PRINT - Turno

              System.out.println("TURNO DE " + atacante.getNombre());
         
              
              // INSTANCEOF - Enfriamiento
              switch (atacante) {
                  case Guerrero guerrero1 -> guerrero1.iniciarTurno();
                  case Mago mago1 -> mago1.iniciarTurno();
                  case Arquero arquero1 -> arquero1.iniciarTurno();
                  default -> {
                  }
              }
              
              // MÉTODO - Veneno
              atacante.procesarVeneno();
              
              // IF - Eliminación
              if (!atacante.estaVivo()) {
                  System.out.println(atacante.getNombre() + " fue derrotado.");
                  break;
              }
              
              // PRINT - Vida
              System.out.println("\n" + atacante.getNombre()
                      + " HP: " + atacante.getHP());
              System.out.println(defensor.getNombre()
                      + " HP: " + defensor.getHP());
              
              boolean accionRealizada = false;
              
              // WHILE - Menú
              while (!accionRealizada) {
                  System.out.println("\n1. Ataque normal");
                  System.out.println("2. Habilidad especial");
                  
                  int opcion = leerOpcion(entrada, "Acción: ", 1, 2);
                  
                  // IF - Ataque
                  if (opcion == 1) {
                      int ataqueTotal = atacante.atacar();
                      int defensaTotal = defensor.defenderse();
                      
                      // IF - Impacto
                      if (ataqueTotal > defensaTotal) {
                          int danio = atacante.calcularDanioNormal();
                          defensor.recibirDanio(danio);
                      } else {
                          System.out.println("El ataque ha fallado.");
                      }
                      
                      accionRealizada = true;
                      
                  } else {
                      
                      // INSTANCEOF - Habilidades
                      switch (atacante) {
                          case Guerrero personaje -> {
                              
                              // IF - Furia
                              if (personaje.habilidadDisponible()) {
                                  personaje.golpeConFuria(defensor);
                                  accionRealizada = true;
                              }
                              
                          }
                          case Mago personaje -> {
                              
                              // IF - Fuego
                              if (personaje.habilidadDisponible()) {
                                  personaje.bolaDeFuego(defensor);
                                  accionRealizada = true;
                              }
                              
                          }
                          case Arquero personaje -> {
                              
                              // IF - Veneno
                              if (personaje.habilidadDisponible()) {
                                  personaje.flechaEnvenenada(defensor);
                                  accionRealizada = true;
                              }
                          }
                          default -> {
                          }
                      }
                      
                      // IF - Enfriamiento
                      if (!accionRealizada) {
                          System.out.println("Habilidad en enfriamiento.");
                          System.out.println("Selecciona otra acción.");
                      }
                  }
              }
              
              // INCREMENTO - Turnos
              turno++;
          }     // PRINT - Final
    
          System.out.println("          FIN DEL COMBATE");
    
          // IF-ELSE - Ganador
          if (jugador1.estaVivo()) {
              System.out.println("GANADOR: " + jugador1.getNombre());
          } else if (jugador2.estaVivo()) {
              System.out.println("GANADOR: " + jugador2.getNombre());
          } else {
              System.out.println("EMPATE");
          }
          // SCANNER - Cierre
      }
  }

  // MÉTODO - Validación
  public static int leerOpcion(Scanner entrada, String mensaje,
                               int minimo, int maximo) {

    // WHILE - Lectura
    while (true) {
      System.out.print(mensaje);

      // TRY-CATCH - Errores
      try {
        int opcion = Integer.parseInt(entrada.nextLine());

        // IF - Rango
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