
import java.net.InetAddress;
import java.net.MulticastSocket;


public class Chat {
    public static void main(String[] args) throws Exception {
        
        MulticastSocket ms = new MulticastSocket(55555);
        ms.joinGroup(InetAddress.getByName("234.0.0.1"));

        Thread client = new Thread(new Cliente(ms));
        client.start();
        Thread server = new Thread(new Servidor(ms));
        server.start();

        server.join();
        System.out.println("Recepcion de datos terminada");
        client.join();
        System.out.println("Emision de datos terminada");

    }
}