package buscaminas;

import java.util.Random;

public class BuscaMinas {

    private int filas = 0;
    private int columnas = 0;
    private char[][] tablero;
    private char[][] plantilla;
    private int contador = 1;
    boolean gana = false;
    int bombas = 0;


    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public char[][] getTablero() {
        return tablero;
    }

    public char[][] getPlantilla() {
        return plantilla;
    }

    public void setFilas(int filas) {
        this.filas = filas;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }

    public void generarTablero() {
/*Crea el tablero de juego con la medidas indicadas por el usuario*/
        this.tablero = new char[this.filas][this.columnas];

        for (int i = 0; i < this.filas; i++) {

            for (int j = 0; j < this.columnas; j++) {

                tablero[i][j] = '-';

            }

        }

    }

    public void generarPlantilla() {
/* Genera la plantilla que tapa las casillas del juego*/
        this.plantilla = new char[this.filas][this.columnas];

        for (int i = 0; i < this.filas; i++) {
            for (int j = 0; j < this.columnas; j++) {

                plantilla[i][j] = 'X';

            }
        }
    }

    public void generarMinas(int minas) {
/*Genera minas de forma aleatoria acorde al numero de minas indicado*/

        bombas = minas;

        Random r = new Random();
        int x;
        int y;
        while (minas > 0) {

            mina:
            for (int i = 0; i < this.tablero.length; i++) {
                for (int j = 0; j < this.tablero[i].length; j++) {

                    x = r.nextInt(filas);
                    y = r.nextInt(columnas);
                    
                    if(this.tablero[i][j] == '*'){
                        
                        continue;
                        
                    }

                    if (x == i && y == j) {

                        this.tablero[i][j] = '*';
                        minas--;
                        break mina;

                    }
                }
            }
        }
        
        generarNumeros();
        
    }

    public boolean victoria(){

        int x = 0;

        for (int i = 0; i < plantilla.length; i++) {
            for (int j = 0; j < plantilla.length; j++) {
                
                if(getPlantilla()[i][j] == 'X'){

                    x++;

                }
            }
        }

        if(bombas == x){

            gana = true;

        }

        return gana;
    }

    public void descubrir(int x, int y, boolean[][] camino) {
/*Descubre las minas de forma recursiva*/
        if (x < 0 || x > tablero.length - 1 || y < 0 || y > tablero[x].length - 1) {

        } else if (camino[x][y] == true) {

        } else if (tablero[x][y] == '*') {

        } else if (tablero[x][y] == '-' && plantilla[x][y] == 'X') {

            camino[x][y] = true;
            plantilla[x][y] = tablero[x][y];

            descubrir(x - 1, y - 1, camino);
            descubrir(x - 1, y, camino);
            descubrir(x - 1, y + 1, camino);
            descubrir(x, y - 1, camino);
            descubrir(x, y + 1, camino);
            descubrir(x + 1, y - 1, camino);
            descubrir(x + 1, y, camino);
            descubrir(x + 1, y + 1, camino);

        } else {

            plantilla[x][y] = tablero[x][y];
            camino[x][y] = true;

        }

    }

    private void generarNumeros() {
/*Genera numeros alrededor de las minas indicando la cantidad de ellas que hay*/
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {

                if (tablero[i][j] == '*') {

                    for (int f = Math.max(0, i - 1); f < Math.min(tablero.length, i + 2); f++) {
                        for (int c = Math.max(0, j - 1); c < Math.min(tablero[i].length, j + 2); c++) {

                            if (tablero[f][c] == '*') {

                                continue;

                            }

                            if (tablero[f][c] != '*' && tablero[f][c] != '-') {

                                contador = contador + (int) (tablero[f][c]) - 48;
                                tablero[f][c] = ("" + contador).charAt(0);

                            } else {

                                tablero[f][c] = ("" + contador).charAt(0);

                            }
                            contador = 1;
                        }
                    }
                }
            }
        }
    }

}
