/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package juegoderol;

import java.util.Scanner;

/**
 *
 * @author djmat
 */

public class Juegoderol {

  public static void main(String[] args) {
    Scanner entrada = new Scanner(System.in);

    System.out.println("ARENA DE LUCHADORES");

    // personajes
    Guerrero guerrero = new Guerrero("Capitán América", 100, 60, 1, 1, 1);
    Mago mago = new Mago("Piru", 100, 55, 1, 1, 1);
    Arquero arquero = new Arquero("Hawkeye", 100, 60, 1, 1, 1);
    Luchador[] luchadores = {guerrero, mago, arquero};

    // armaduras
    Armadura malla = new Armadura("Cota de malla", 4);
    Armadura cuero = new Armadura("Armadura de cuero", 2);
    Armadura tela = new Armadura("Armadura de tela", 1);

    // asignar
    guerrero.setArmadura(malla);
    mago.setArmadura(tela);
    arquero.setArmadura(cuero);

    // estadísticas
    System.out.println("\n--- LUCHADORES ---");
    for (int i = 0; i < luchadores.length; i++) {
      Luchador l = luchadores[i];
      System.out.println("\n" + (i + 1) + ". " + l.getNombre());
      System.out.println("Vida: " + l.getHP());
      System.out.println("Ataque: " + l.getAtaque());
      System.out.println("Fuerza: " + l.getFuerza());
      System.out.println("Agilidad: " + l.getAgilidad());
      System.out.println("Inteligencia: " + l.getinteligencia());
      if (l.getArmadura() != null) {
        System.out.println("Armadura: " + l.getArmadura().getNombre()
                + " (+" + l.getArmadura().getBono() + ")");
      }
    }

    // selección
    System.out.println("\nSELECCIÓN");
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

    System.out.println("\nINICIA EL COMBATE");
    System.out.println(jugador1.getNombre() + " VS " + jugador2.getNombre());

    // combate
    int turno = 0;
    while (jugador1.estaVivo() && jugador2.estaVivo()) {
      Luchador atacante;
      Luchador defensor;

      if (turno % 2 == 0) {
        atacante = jugador1;
        defensor = jugador2;
      } else {
        atacante = jugador2;
        defensor = jugador1;
      }

      System.out.println("\nTURNO DE " + atacante.getNombre());

      iniciarTurno(atacante);
      atacante.procesarVeneno();

      if (!atacante.estaVivo()) {
        System.out.println(atacante.getNombre() + " fue derrotado.");
        break;
      }

      System.out.println(atacante.getNombre() + " HP: " + atacante.getHP());
      System.out.println(defensor.getNombre() + " HP: " + defensor.getHP());

      // acción
      boolean accionRealizada = false;
      while (!accionRealizada) {
        System.out.println("\n1. Ataque normal");
        System.out.println("2. Habilidad especial");
        int opcion = leerOpcion(entrada, "Acción: ", 1, 2);

        if (opcion == 1) {
          int ataqueTotal = atacante.atacar();
          int defensaTotal = defensor.defenderse();
          if (ataqueTotal > defensaTotal) {
            defensor.recibirDanio(atacante.calcularDanioNormal());
          } else {
            System.out.println("El ataque ha fallado.");
          }
          accionRealizada = true;
        } else {
          accionRealizada = usarHabilidad(atacante, defensor);
          if (!accionRealizada) {
            System.out.println("Habilidad en enfriamiento.");
            System.out.println("Selecciona otra acción.");
          }
        }
      }

      turno++;
    }

    // final
    System.out.println("\nFIN DEL COMBATE");
    if (jugador1.estaVivo()) {
      System.out.println("GANADOR: " + jugador1.getNombre());
    } else if (jugador2.estaVivo()) {
      System.out.println("GANADOR: " + jugador2.getNombre());
    } else {
      System.out.println("EMPATE");
    }

    entrada.close();
  }

  // enfriamiento
  private static void iniciarTurno(Luchador atacante) {
    if (atacante instanceof Guerrero guerrero) {
          guerrero.iniciarTurno();
    } else if (atacante instanceof Mago) {
      ((Mago) atacante).iniciarTurno();
    } else if (atacante instanceof Arquero arquero) {
        arquero.iniciarTurno();
    }
  }

  // habilidad
  private static boolean usarHabilidad(Luchador atacante, Luchador defensor) {
      switch (atacante) {
          case Guerrero g -> {
              if (g.habilidadDisponible()) {
                  g.golpeConFuria(defensor);
                  return true;
              }
          }     case Mago m -> {
              if (m.habilidadDisponible()) {
                  m.bolaDeFuego(defensor);
                  return true;
              }
          }     case Arquero a -> {
              if (a.habilidadDisponible()) {
                  a.flechaEnvenenada(defensor);
                  return true;
              }
          }     default -> {
          }
      }
    return false;
  }

  // lectura
  private static int leerOpcion(Scanner entrada, String mensaje, int min, int max) {
    while (true) {
      System.out.print(mensaje);
      if (entrada.hasNextInt()) {
        int opcion = entrada.nextInt();
        if (opcion >= min && opcion <= max) {
          return opcion;
        }
        System.out.println("Opción fuera del rango.");
      } else {
        System.out.println("Entrada no válida.");
        entrada.next();
      }
    }
  }

}