import java.io.IOException;
import java.net.*;

public class Cliente implements Runnable{

    MulticastSocket ms;

    public Cliente (MulticastSocket ms){
        this.ms = ms;
    }

    @Override
    public void run(){

        while (true){

            DatagramPacket data = new DatagramPacket(new byte[1024],1024);

            try{

                ms.receive(data);
                System.out.println(new String(data.getData()));

            }catch (IOException e){
                e.printStackTrace();
            }

        }

    }

}