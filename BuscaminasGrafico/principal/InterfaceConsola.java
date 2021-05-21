package principal;

import java.util.Scanner;
import buscaminas.BuscaMinas;

public class InterfaceConsola {

    Scanner sc = new Scanner(System.in);
    BuscaMinas partida;
    boolean gameOver = false;
    int y;
    int x;

    public InterfaceConsola() {

        this.partida = new BuscaMinas();
        System.out.println("Escribe el numero de filas y columnas del tablero");
        partida.setFilas(sc.nextInt());
        partida.setColumnas(sc.nextInt());
        partida.generarTablero();
        partida.generarPlantilla();

        System.out.println("Introduce numero de minas");
        partida.generarMinas(sc.nextInt());

        boolean[][] camino = new boolean[partida.getFilas()][partida.getColumnas()];

        for (int i = 0; i < camino.length; i++) {
            for (int j = 0; j < camino[i].length; j++) {

                camino[i][j] = false;

            }
        }
        while (gameOver == false) {
            System.out.println("Introduce coordenadas a decubrir");

            x = sc.nextInt();
            y = sc.nextInt();

            if (partida.getTablero()[x][y] == '*') {

                gameOver = true;
                System.out.println("GAME OVER");
                break;

            }

            partida.descubrir(x, y, camino);

            for (int i = 0; i < partida.getFilas(); i++) {
                for (int j = 0; j < partida.getColumnas(); j++) {

                    System.out.print(partida.getPlantilla()[i][j]);

                }

                System.out.println("");

            }
        }
    }

    public static void main(String[] args) {

        new InterfaceConsola();

    }
}
