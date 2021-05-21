package principal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.util.Scanner;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;



import buscaminas.BuscaMinas;

public class InterfaceGrafica extends JFrame{


    Scanner sc = new Scanner(System.in);
    BuscaMinas partida;
    boolean gameOver = false;
    JFrame ventana;
    JPanel principal;
    JPanel mapa;
    JButton jugar;
    JTextField minas;
    JTextField labelfilas;
    JTextField labelcolumas;
    int filas;
    int columnas;
    int bombas;


    public InterfaceGrafica(){

        partida = new BuscaMinas();

        //Ventana inicial del juego
        ventana =new JFrame("Buscaminas");
        ventana.setSize(300,350);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Panel de fondo de pantalla
        principal = new JPanel();
        principal.setSize(ventana.getWidth(),ventana.getHeight());
        principal.setLocation(0,0);
        principal.setBackground(Color.blue);
        principal.setLayout(null);
        principal.setVisible(true); 

        //Label de filas
        labelfilas = new JTextField("Numero de filas");
        labelfilas.setSize(100,20);
        labelfilas.setLocation(20,70);
        labelfilas.setVisible(true);

        //Label de columnas
        labelcolumas= new JTextField("Numero de columas");
        labelcolumas.setSize(100,20);
        labelcolumas.setLocation(160,70);
        labelcolumas.setVisible(true);

        //Label de minas
        minas = new JTextField("Numero de minas");
        minas.setSize(100,20);
        minas.setLocation(90,120);
        minas.setVisible(true);

        //Boton jugar
        jugar = new JButton("JUGAR");
        jugar.setSize(100,30);
        jugar.setLocation(90,160);
        jugar.setVisible(true);

        //Tablero de juego
        mapa = new JPanel();
        mapa.setSize(ventana.getWidth(),ventana.getHeight());
        mapa.setLocation(0,0);

        principal.add(jugar);
        principal.add(minas);
        principal.add(labelcolumas);
        principal.add(labelfilas);
        ventana.add(principal);
        ventana.setVisible(true);

        //Evento de boton para jugar
        jugar.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {

                filas = Integer.parseInt(labelfilas.getText());
                columnas = Integer.parseInt(labelcolumas.getText());
                bombas = Integer.parseInt(minas.getText());
                
                partida.setFilas(filas);
                partida.setColumnas(columnas);

                partida.generarTablero();
                partida.generarPlantilla();
               
                System.out.println(filas+" "+columnas);

                mapa.setLayout(new GridLayout(filas,columnas));
                
                partida.generarMinas(bombas);
        
                crearBotones();

                mapa.setVisible(true);
                ventana.remove(principal);
                ventana.add(mapa);
                SwingUtilities.updateComponentTreeUI(ventana);

            }

        });

    }

    public void crearBotones(){

        JButton[][] botones = new JButton[filas][columnas];

        for(int fila=0; fila < filas; fila++){
            for(int col=0; col < columnas; col++){

                JButton boton = new JButton();
                boton.setText(Character.toString(partida.getPlantilla()[fila][col]));
                
                boton.addActionListener(new ActionListener(){

                    @Override
                    public void actionPerformed(ActionEvent ae){

                        JButton jb = (JButton) ae.getSource();
                        int f = 0;
                        int c = 0;

                        boolean[][] camino = new boolean[partida.getFilas()][partida.getColumnas()];
                        for (int i = 0; i < camino.length; i++) {
                            for (int j = 0; j < camino[i].length; j++) {

                                camino[i][j] = false;

                            }
                        }

                        exit:
                        for (int i = 0; i < botones.length; i++) {
                            for (int j = 0; j < botones.length; j++) {
                                
                                if(botones[i][j] == jb){

                                    f = i;
                                    c = j;

                                    break exit;

                                }

                            }
                            
                        }

                        partida.descubrir(f, c, camino);

                        if (partida.getTablero()[f][c] == '*'){

                            JOptionPane.showMessageDialog(null, "Game over");

                        }else if(partida.victoria()){

                            JOptionPane.showMessageDialog(null, "Has Ganado!!");

                        }else if(partida.getPlantilla()[f][c] != '*'){

                            //Borro todos los botones antiguos

                                for (int i = 0; i < filas; i++) {
                                    for (int j = 0; j < columnas; j++) {
                        
                                        mapa.remove(botones[i][j]);
                                        
                                    }
                                    
                                }

                            //Lleno el panel con la nueva informacion

                            crearBotones();

                        }else if(partida.victoria()){

                            JOptionPane.showMessageDialog(null, "Has Ganado!!");

                        }

                    } 

                });


                botones [fila][col] = boton;
                mapa.add(boton);
                mapa.updateUI();


            }

        }

    } 

    

    

    public static void main(String[] args) {
        
        new InterfaceGrafica();

    }
    
}
