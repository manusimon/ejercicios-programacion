
import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Servidor implements Runnable{

    Scanner sc;
    String nombre;
    MulticastSocket ms;

    public Servidor(MulticastSocket ms){

        this.ms = ms;
        sc = new Scanner(System.in);

        System.out.println("Nombre de usuario: ");
        nombre = sc.nextLine();


        try {
            
            String mensaje = nombre + " se ha unido al chat";
            byte[] buf = mensaje.getBytes();

            DatagramPacket data = new DatagramPacket(buf, buf.length, InetAddress.getByName("234.0.0.1"),55555);
            ms.send(data);

        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }

    }

    @Override
    public void run(){

        while(true){

            try {
                
                String msg = nombre + ": "+ sc.nextLine();
                byte[] buf = msg.getBytes();

                DatagramPacket data = new DatagramPacket(buf, buf.length, InetAddress.getByName("234.0.0.1"),55555);
                ms.send(data);

                Thread.sleep(1000);


            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e){
                e.printStackTrace();
            } catch (InterruptedException e){
                e.printStackTrace();
            }

        }

    }

}

